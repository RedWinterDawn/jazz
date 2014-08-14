import static com.jayway.awaitility.Awaitility.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.jive.myco.jazz.api.context.ContextExecutor;
import com.jive.myco.jazz.api.context.JazzContextManager;

/**
 * @author Brandon Pedersen &lt;bpedersen@getjive.com&gt;
 */
public class ContextExecutorTest
{
  private ExecutorService delegate;

  private ContextExecutor executor;

  @Before
  public void setup() throws Exception
  {
    delegate = Executors.newSingleThreadExecutor();
    executor = new ContextExecutor(delegate);
  }

  @After
  public void tearDown() throws Exception
  {
    delegate.shutdownNow();
    delegate.awaitTermination(100, TimeUnit.MILLISECONDS);
  }

  @Test
  public void testCurrentContextCarriedThrough() throws Exception
  {
    JazzContextManager.put("carried", "through");

    final AtomicReference<String> inThreadValue = new AtomicReference<>();

    executor.execute(() -> inThreadValue.set(JazzContextManager.get("carried")));

    final String value = await().untilAtomic(inThreadValue, notNullValue());

    assertEquals("through", value);
  }

  @Test
  public void testAdditionalContext() throws Exception
  {
    JazzContextManager.put("carried", "through");

    final AtomicReference<Map<String, String>> inThreadMap = new AtomicReference<>();

    executor.execute(
        () ->
        {
          inThreadMap.set(JazzContextManager.toMap());
        },
        ImmutableMap.<String, String> builder()
            .put("additional", "value")
            .build());

    final Map<String, String> context = await().untilAtomic(inThreadMap, notNullValue());

    assertEquals("through", context.get("carried"));
    assertEquals("value", context.get("additional"));

    assertEquals("through", JazzContextManager.get("carried"));
    assertNull(JazzContextManager.get("additional"));
  }
}

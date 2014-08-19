import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Maps;
import com.jive.myco.jazz.api.context.ContextWrappedCallable;
import com.jive.myco.jazz.api.context.ContextWrappedRunnable;
import com.jive.myco.jazz.api.context.ContextWrappedTask;
import com.jive.myco.jazz.api.context.JazzContextManager;

@RunWith(MockitoJUnitRunner.class)
public class JazzContextManagerTest
{
  @Before
  public void setup() throws Exception
  {
    final HashMap<String, String> localContext = Maps.newHashMap();
    localContext.put("key", "value");
    JazzContextManager.initialize(localContext);
  }

  @After
  public void teardown()
  {
    JazzContextManager.clear();
  }

  @Test
  public void testDifferentContextsOnDifferentThreads() throws Exception
  {
    JazzContextManager.put("carried", "through");

    final AtomicReference<String> inThreadValue = new AtomicReference<>();

    final Thread t = new Thread(() -> inThreadValue.set(JazzContextManager.get("carried")));
    t.start();
    t.join();

    assertNull(inThreadValue.get());
  }

  @Test
  public void testLocalContextIsTheSame() throws Exception
  {
    final AtomicReference<String> inThreadValue = new AtomicReference<>();

    final Thread t = new Thread(() -> inThreadValue.set(JazzContextManager.get("key")));
    t.start();
    t.join();

    assertEquals("value", JazzContextManager.get("key"));
    assertEquals("value", inThreadValue.get());
  }

  @Test
  public void testContextId() throws Exception
  {
    JazzContextManager.clear();

    final String contextId =
        JazzContextManager.get(JazzContextManager.JAZZ_CONTEXT_ID_KEY);

    assertNotNull(contextId);
  }

  @Test
  public void testContextCleared() throws Exception
  {
    JazzContextManager.put("carried", "through");

    // Don't wrap it, we just want to test the wrappers and context manager here.
    final ExecutorService executor = Executors.newSingleThreadExecutor();

    try
    {
      final ContextWrappedRunnable runnable = new ContextWrappedRunnable(
          () ->
          {
            assertNull(JazzContextManager.get("foo"));
            assertNotNull(JazzContextManager.get("carried"));
            JazzContextManager.put("foo", "bar");
          },
          JazzContextManager.toMap());

      final ContextWrappedCallable<Void> callable = new ContextWrappedCallable<>(
          () ->
          {
            assertNull(JazzContextManager.get("foo"));
            assertNotNull(JazzContextManager.get("carried"));
            JazzContextManager.put("foo", "bar");
            return null;
          },
          JazzContextManager.toMap());

      final ContextWrappedTask task = new ContextWrappedTask(
          () ->
          {
            assertNull(JazzContextManager.get("foo"));
            assertNotNull(JazzContextManager.get("carried"));
            JazzContextManager.put("foo", "bar");
          },
          JazzContextManager.toMap());

      executor.submit(runnable).get();
      executor.submit(runnable).get();

      executor.submit(callable);
      executor.submit(callable);

      executor.submit(task);
      executor.submit(task);
    }
    finally
    {
      executor.shutdownNow();
    }
  }
}

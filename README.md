# Jazz API

The API for components of the Jazz framework.

* **[BOM](./bom/README.md)**
* **[Core](./core)**
* **[Health](./health/README.md)**
* **[Metrics](./metrics/README.md)**
* **[REST](./rest/README.md)**
* **[Web](./web/README.md)**
* **[Change Log](#changes)**

## <a name="changes"></a>Change Log

### 0.2.10
* Upgrading from 0.2.9
  * No changes required.
* Change Log
  * NS - Made Git related build meta optional in runtime environment

### 0.2.9
* Upgrading from 0.2.8
  * No changes required.
* Change Log
  * US5129 - Added context and rules engine

### 0.2.8
* Upgrading from 0.2.7
  * No changes required.
* Change Log
  * US5205 - Relaxed context rule expression Regex

### 0.2.7
* Upgrading from 0.2.6
  * No changes required.
* Change Log
  * US5129 - Upgraded to ISP 0.12 and Myco Commons 0.1.1
  * US5129 - Added context and rules engine

### 0.2.6
* Upgrading from 0.2.5
  * No changes required.
* Change Log
  * NS - Upgraded to ISP 0.11

### 0.2.5
* Upgrading from 0.2.4
  * No changes required.
* Change Log
  * NS - Upgraded to ISP 0.10

### 0.2.4
* Upgrading from 0.2.3
  * No changes required.
* Change Log
  * NS - Upgraded to ISP 0.9

### 0.2.3
* Upgrading from 0.2.2
  * No changes required.
* Change Log
  * NS - Upgraded to ISP 0.8

### 0.2.2
* Upgrading from 0.2.1
  * No changes required.
* Change Log
  * NS - Upgraded to Myco Commons 0.1.0

### 0.2.1
* Upgrading from 0.2.0
  * No changes required.
* Change Log
  * NS - Updated dependencies

### 0.2.0
* Upgrading from 0.1.3
  * A number of breaking changes in the API.  See `JazzRuntimeEnvironment` as a replacement for
    `JazzRuntime`.  The `JazzInjector` has been removed.  Applications should not extend one of the
    abstract `JazzRuntimeLauncher` implementations in the runtime module to provide lifecycle
    support fot their application.
* Change Log
  * US4842, US4843 - Updated to Myco Parent 26
  * US4842, US4843, US5033 - Updated to Jotter 2.0.7
  * US5033 - Updated manager APIs to implement `ListenableLifecycled`

### 0.1.3
* Upgrading from 0.1.2
  * None
* Change Log
  * US4536 - Upgraded to Myco Commons 0.0.8 and Myco Parent 24

### 0.1.2
* Upgrading from 0.1.1
  * None
* Change Log
  * US3598 - Added health check API
  * US2261 - Added service registry API
  * NS - Upgraded to latest parent

### 0.1.1
* Upgrading from 0.1.0
  * None
* Change Log
  * US4171 - Added Web Manager and REST Manager APIs

### 0.1.0
* Upgrading from nothing
  * None
* Change Log
  * US3323 - Added metrics and runtime to the API
  * US3281 - Added skeleton for project
  * US3323 - Added core API modules
  * US3323 - Added metrics API modules


# Getir Case Study
This project is a Getir case study application, built entirely with Jetpack Compose and structured around Clean Architecture principles within a multi-module setup. It showcases modern Android development best practices and scalable design patterns.

It also includes built-in support for multi-language localization with resources prepared for Turkish (TR), English (EN), French (FR), Italian (IT), and Spanish (ES) — ready to activate when needed.

## Tech stack
- Minimum SDK level 24+
- Architecture: MVVM + Clean Architecture + Single Activity
- UI Framework: [Jetpack Compose](https://developer.android.com/compose) (100%)
- Navigation: [Nav3](https://developer.android.com/guide/navigation/navigation-3)
- State Management: [StateFlow & SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- Animations: [Lottie](https://github.com/airbnb/lottie-android)
- Networking: [Retrofit](https://square.github.io/retrofit/)
- Persistence: [Room](https://developer.android.com/training/data-storage/room)
- Dependency Injection: [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- Asynchronous Programming: [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- Data Streams: [Flow](https://developer.android.com/kotlin/flow)
- Image Loading: [Coil](https://coil-kt.github.io/coil)
- Pagination: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- Static Analysis: [Detekt](https://detekt.dev)
- Memory Leak Detection: [LeakCanary](https://square.github.io/leakcanary)
- Testing: JUnit5, MockK, Turbine
- Multilanguage Support: TR, EN, FR, IT ,ES
- [Progress-Centric Notification](https://developer.android.com/about/versions/16/features/progress-centric-notifications)
- [ShortCut](https://developer.android.com/develop/ui/views/launch/shortcuts)

## Architecture
This project follows a modular Clean Architecture structure, separating responsibilities across layers and modules to ensure scalability, reusability, and testability.

### buildSrc: 	
```bash
Manages app configurations such as applicationId, versionCode, versionName, and product flavors.
Handles product flavors like dev and product, enabling different base URLs, app names, and package IDs per flavor.
```

### contract:
```bash
Defines shared contracts such as baseUrl and dispatcher annotations.
```

### core:
#### core:di
```bash
Contains shared Dependency Injection setup like CoroutineDispatcher and NavigationManager bindings.
```

#### core:domain
```bash
Contains shared models and use cases used across multiple modules.
```

#### core:presentation
```bash
Contains common Compose UI utilities, preview wrappers, and extension functions reused across feature modules.
```

### database:
```bash
Encapsulates Room database setup, DAOs, and local persistence logic.
```

### language:
```bash
Manages multi-language support and string resource access.
A screen can access localized resources by implementing this module’s interface.
```

### navigation:
```bash
Contains a shared NavigationManager abstraction.
Modules can perform navigation by implementing and registering this navigation layer.
```

### network:
```bash
Sets up Retrofit, OkHttp, and base networking configurations with Hilt.
```

### uikit: 
```bash
Provides reusable UI components, such as buttons, color palettes, fonts, icons, and themes based on design system tokens.
```

### feature modules:
```bash
Each feature (e.g., cart, product) is separated into 4 submodules:
```

#### features:contract
```bash
Defines navigation destinations (as route contracts) to allow other modules to navigate into it cleanly.
```

### features:data
```bash
Handles data sources and repositories (Retrofit, Room).
```

### features:domain
```bash
Contains business logic and use cases.
```

### feature:presentation
```bash
UI layer built with Jetpack Compose.
```

## Screenshots
<p float="left">
  <img src="https://github.com/user-attachments/assets/1755bcd6-48b0-430c-a319-e485c2a97ab5" width="23%" />
  <img src="https://github.com/user-attachments/assets/3e3fb12d-9eff-4a1d-820b-40ca0aa5946d" width="23%" />
  <img src="https://github.com/user-attachments/assets/99430e18-dddb-4ac7-a8f6-2f1beb998f2e" width="23%" />
  <img src="https://github.com/user-attachments/assets/2f29e37d-1d13-45a9-920f-832c8d22c589" width="23%" />
</p>

<p float="left">
  <img src="https://github.com/user-attachments/assets/08256cb8-e358-43e4-bb2e-35f028e63763" width="23%" />
  <img src="https://github.com/user-attachments/assets/4f50e138-8ce3-4c97-9627-1affee6b83c5" width="23%" />
  <img src="https://github.com/user-attachments/assets/c3e7d8bd-9e93-4559-a34e-83f4e765102b" width="23%" />
</p>

## Demo
https://github.com/user-attachments/assets/ba5bd616-06c1-4d5f-b06d-93799f313ac7

## Additional Features 
### Progress-Centric Notification
https://github.com/user-attachments/assets/5951dda3-64e8-4970-9bfe-fe664314465d

### Shortcut 
![Screenshot_20250728_141940_One UI Home](https://github.com/user-attachments/assets/09e64b07-0692-4e58-a00b-55504df9a7a6)
![Screenshot_20250728_141936_One UI Home](https://github.com/user-attachments/assets/81aacef0-e6c2-400d-9c0b-6407c24c63ad)

## Decisions Made and Trade-offs

  - **Jetpack Compose** was chosen instead of XML for its modern declarative UI model, seamless state management, and first-class Kotlin support.  
  *Trade-off:* While Compose accelerates UI development and improves code readability, it still requires careful recomposition handling and performance profiling in dynamic list-heavy or animation-rich screens.

- **Hilt** was chosen over Koin because it is officially supported by Google, works well with Jetpack libraries like ViewModel and Navigation, and catches errors at compile time.  
  *Trade-off:* Hilt needs more setup and follows strict component rules. Unlike Koin, it’s not as flexible at runtime, which can make it harder to use in complex or dynamic app structures.

- **Retrofit** was used for network communication thanks to its mature ecosystem, ease of integration with OkHttp and Gson, and widespread community support.  
  *Trade-off:* Retrofit cannot be used in Kotlin Multiplatform projects since it’s tied to the JVM. This requires setting up a separate network client like Ktor for other platforms.

- **Modular Architecture** was adopted to enforce separation of concerns, enable parallel development, improve build performance via isolated modules, and enhance reusability.  
  *Trade-off:* This structure increases initial project complexity and requires more planning for dependency management, especially in deeply nested or feature-heavy applications.

- **Room** was selected for structured local data persistence with strong compile-time validation, support for SQL queries, and seamless DAO architecture.  
  *Trade-off:* Although Room is robust for relational data, it can be verbose for simple key-value storage use cases where Jetpack DataStore might be more lightweight and suitable.

## Run Locally

Clone the project

```bash
  https://github.com/Keremturker/Getir-Case-Study.git
```
And run the project.

## Run Detekt (Static Code Analysis)

To analyze the codebase using Detekt, run:

```bash
  ./gradlew detekt --continue
```

## Run All Unit Tests

To run only the unit tests of presentation modules:

```bash
./gradlew :features:product:presentation:testDebugUnitTest :features:cart:presentation:testDebugUnitTest
```

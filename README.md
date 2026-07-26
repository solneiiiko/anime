# Anime & Manga <img src="https://github.com/user-attachments/assets/6a8baf30-5d86-4390-a38b-ce6cc8eb032b" width="42" />

## ⭐ Engineering Highlights

- Multi-module architecture
- Clean Architecture & MVI
- Optimized application startup with lightweight dependency initialization
- Dependency Injection with Dagger 2
- Centralized runtime configuration for caching and paging using Paging 3 & RemoteMediator 
- Offline-first data layer with Room
- Persistent application configuration using DataStore Preferences
- Build variants with environment-specific cache policies (1 minute for Internal, 1 day for Production)
- Separate Internal and Production configurations
- Configurable application behavior without code changes
- Material 3 with dynamic theming & contrast mode
- Adaptive NavigationSuiteScaffold (Navigation Bar / Navigation Rail) using Navigation 3
- Build flavors
- GitHub Actions CI/CD


## ✨ Features

- Browse anime catalog
- Infinite scrolling
- Fast loading with offline cache
- Light and Dark themes
- Adaptive navigation for different screen sizes
- Material 3 UI

## 🔑 API Key

This project requires a MyAnimeList API key to get data.

You can obtain your API key here:
- [API key help](https://help.myanimelist.net/hc/en-us/articles/900003108823-API)
  
and add your API key to `local.properties` as `MY_ANIME_LIST_CLIENT_ID=your_api_key` or gradle params as `-PMY_ANIME_LIST_CLIENT_ID=your_api_key` 
  
*The key is stored locally and is not included in the repository.*

## 🛠 Tech Stack

| Category | Technologies |
|----------|--------------|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | MVI, Clean Architecture, Multi-module |
| Dependency Injection | Dagger 2 |
| Asynchronous | Kotlin Coroutines, Flow |
| Networking | Retrofit, Kotlin Serialization |
| Local Storage | Room, DataStore Preferences |
| Pagination | Paging 3 |
| Navigation | Navigation 3 |
| Build | Gradle, Version Catalog |
| CI/CD | GitHub Actions |


## 🏗 Architecture

The application follows **Clean Architecture** with a **multi-module** structure and **MVI** presentation layer.

### Why Dagger 2?

The project is intentionally split into feature and core modules to demonstrate explicit module boundaries, independent feature dependencies, and scalable navigation between features.

**Dagger 2** is used instead of **Hilt** to keep dependency graphs explicit and avoid coupling feature modules to the application module.


Solid arrows represent direct Gradle module dependencies.  
Dashed arrows represent dependencies wired through Dagger 2 in the application composition root.

```mermaid
flowchart TB

    APP[":app<br/>Application & composition root"]

    subgraph FEATURES["Feature modules"]
        CATALOG[":feature:catalog"]
        ANIME_DETAILS[":feature:details:anime"]
        MANGA_DETAILS[":feature:details:manga"]
        FAVOURITES[":feature:favourites"]
        SETTINGS[":feature:settings"]
    end

    subgraph ANIME["Anime modules"]
        DOMAIN_API[":entity:anime:domain:api"]
        DOMAIN_IMPL[":entity:anime:domain:impl"]
        DATA_API[":entity:anime:data:api"]
        DATA_IMPL[":entity:anime:data:impl"]
    end

    subgraph SHARED["Shared infrastructure"]
        DATABASE[":common:database<br/>Room"]
        NETWORK[":common:network<br/>Retrofit & OkHttp"]
        THEME[":common:theme"]
    end

    subgraph CORE["Core modules"]
        CORE_DI[":core:di"]
        MVI[":core:mvi"]
        UI[":core:ui"]
        CORE_NETWORK[":core:network"]
    end

    APP --> CATALOG
    APP --> ANIME_DETAILS
    APP --> MANGA_DETAILS
    APP --> FAVOURITES
    APP --> SETTINGS

    APP --> DOMAIN_IMPL
    APP --> DATA_IMPL
    APP --> DATABASE
    APP --> NETWORK
    APP --> THEME

    CATALOG --> DOMAIN_API
    CATALOG --> MVI
    CATALOG --> UI
    CATALOG --> CORE_DI

    ANIME_DETAILS --> DOMAIN_API
    ANIME_DETAILS --> MVI
    ANIME_DETAILS --> UI
    ANIME_DETAILS --> CORE_DI

    DOMAIN_IMPL --> DOMAIN_API

    DATA_IMPL --> DATA_API
    DATA_IMPL --> DOMAIN_API
    DATA_IMPL --> CORE_NETWORK

    DATABASE --> DATA_API
    NETWORK --> CORE_NETWORK

    APP -. "wires with Dagger" .-> CATALOG
    APP -. "wires with Dagger" .-> ANIME_DETAILS
    APP -. "binds implementations" .-> DOMAIN_IMPL
    APP -. "binds implementations" .-> DATA_IMPL
```


## 🎥 App Demo

🚧 **Work in Progress**

The current focus of this project is architecture, scalability, and production-ready engineering practices. The UI will continue to evolve alongside new features.


- Placeholders are shown while images are loading
- Demo of the current state of AnimeDetailsScreen

| Catalog | Details (current state)                                                                                 |
|---------|---------------------------------------------------------------------------------------------------------|
| <img width="180" height="400" alt="anime_demo_1" src="docs/gifs/anime_demo_catalog_images_loading_slow_internet.gif" /> | <img width="180" height="400" alt="anime_demo_1" src="docs/gifs/anime_demo_anime_details_screen.gif" /> |



| Theme | Catalog | Details                                                          | Settings |
|---------|---------|------------------------------------------------------------------|--------|
| Light | <img src="docs/screenshots/catalog_light.png" width="220"> | <img src="docs/screenshots/anime_details_light.png" width="220"> | ^_^__/ |
| Dark | <img src="docs/screenshots/catalog_dark.png" width="220"> | <img src="docs/screenshots/anime_details_dark.png" width="220"> | ^_^__/ |


## 🗺 Roadmap

### Application

- [x] Material 3 UI
- [x] Anime catalog (first version)
- [x] Anime catalog (production version)
- [ ] **Anime catalog filters**
- [ ] Manga catalog (production version)
- [ ] Manga catalog filters
- [ ] **Details screen**
- [ ] Favorites
- [ ] Search by title
- [ ] Settings
- [ ] Accessibility improvements (TalkBack)
- [ ] Localization

### Engineering

- [x] Multi-module architecture
- [x] Clean Architecture & MVI
- [x] Adaptive Navigation (Navigation 3)
- [x] Offline-first data layer
- [x] Paging 3 & RemoteMediator
- [x] DataStore Preferences
- [x] GitHub Actions CI
- [ ] GitHub Actions CI improvements
- [ ] Screenshot tests
- [ ] Unit tests
- [ ] UI tests
- [ ] Firebase Remote Config
- [ ] Analytics 
- [ ] Tablet layout improvements

## 📄 License

This project is licensed under the terms described in the [LICENSE](LICENSE) file.

## 🙏 Acknowledgements

Special thanks to:

- **MyAnimeList API** for providing stable data and beautiful images.
- **Jikan API** for the excellent public API.
- **Copilot** & **ChatGPT** for creating the project's artwork.

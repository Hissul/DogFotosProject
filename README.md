# DogFotosProject 🐶
> Приложение для просмотра и сохранения случайных фото собак

---

## ✨ Особенности
- Загрузка случайных фото собак с [Dog API](https://dog.ceo/dog-api/)
- **Бесконечная прокрутка** в `MainFragment`
- Полноэкранный просмотр фото
- Добавление фото в избранное
- Локальное хранение через **Room**
- MVVM архитектура + чистая архитектура (слои Data/Domain/Presentation)
- DI через **Koin**
- UI-слой разделён: основной экран, избранное, детали фото

---

## 🛠 Технологии
- **Язык**: Kotlin
- **Архитектура**: MVVM (чистая архитектура)
- **Библиотеки**:
  - Retrofit + Gson
  - Room Database
  - Glide
  - Koin
  - Coroutines + Flow
  - Material Components  
- **Минимальный API**: 33 (Android 13)  
- **Текущий AGP/Kotlin**: по `libs.versions.toml`

---

## 📋 Системные требования
- Android Studio Flamingo или новее
- Gradle 8+
- Устройство/эмулятор с доступом в интернет
- `AndroidManifest.xml` содержит разрешение:
  ```xml
  <uses-permission android:name="android.permission.INTERNET" />

---

## ⚡️ Быстрый старт
git clone https://github.com/Hissul/DogFotosProject.git

cd DogFotosProject

git checkout develop

./gradlew clean build


Открой проект в Android Studio

Построй и запусти на эмуляторе/устройстве

---

## 🔧 Конфигурация
Не требует API-ключей или особых настроек

Просто подключи интернет, и приложение сразу работает

---

## 🧩 Структура проекта

```text
com.example.dogfotosproject
├── data
│   ├── api           # Retrofit-интерфейс
│   ├── db            # Room DAO и Entity
│   └── repository    # Репозитории реализации
|
├── domain
│   └── usecase       # UseCases, бизнес-логика
|
└── presentation
    ├── login         # Логин / регистрация
    ├── main          # Основной экран с фото
    ├── fullFoto      # Детальный экран фото
    ├── favorite      # Экран избранного
    └── ...           # DI-модули, навигация и т.д.
```

---

## 🧭 Навигация между экранами
LoginFragment → MainFragment (вход)

RegistrationFragment → LoginFragment

MainFragment:

фото → переход→ FullPhotoFragment

кнопка избранное → FavoriteFragment

FavoriteFragment:

фото → FullPhotoFragment

кнопка «Дом» → MainFragment, «Выйти» → LoginFragment

---

## 📚 Лицензия
MIT © Hissul

Ты свободен использовать, модифицировать и распространять проект!

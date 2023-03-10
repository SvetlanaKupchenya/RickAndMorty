# Курсовая работа
Техническое задание для курсового проекта «Рик и Морти»

Описание требований к проекту
1.	Общие сведения.
Курсовой проект представляет собой мобильное приложение «Рик и Морти» и является дополнением к одноименному сериалу. В состав проекта входит документ с описанием технического задания и истории задач, входящих в релиз, а также исходный код проекта и APK-файл.
2.	Цели и назначение создания автоматизированной системы.
Цель мобильного приложения – предоставить пользователям удобный интерфейс для загрузки, просмотра и поиска сведений обо всех персонажах, локациях и эпизодах сериала «Рик и Морти» с перекрестными ссылками.
3.	Характеристика объектов автоматизации.
Объектом автоматизации в данном проекте является процесс загрузки с официального сайта, просмотра и поиска информации о персонажах, локациях и эпизодах сериала «Рик и Морти».
4.	Требования к автоматизированной системе.
Мобильное приложение создано с использованием программного обеспечения Android Studio. Исходный код мобильного приложения написан на языке Kotlin. Информация, доступная пользователю в приложении, загружается с официального Интернет-ресурса https://rickandmortyapi.com/documentation/#introduction
5.	Состав и содержание работ по созданию автоматизированной системы.
Этапы создания мобильного приложения указаны в разделе «История задач, входящих в релиз».
6.	Порядок разработки автоматизированной системы.
Разработка мобильного приложения основана на следующей схеме:
•	Главный activity-компонент MainActivity.kt со Splash-заставкой. При открытии приложения запускается Splash-экран, который отображается как фон для системного окна. На нем содержится изображение и текст, символизирующий приложение.

После загрузки приложения происходит смена Splash-темы и отображается главный layout-компонент activity_main.xml со следующей структурой:
---------------------------
[MainActivity]



---------------------------
[BottomNavigationView]
|Characters|Locations|Episodes|
---------------------------

•	Фрагмент со списком персонажей (Characters) в 2 столбца. Этот фрагмент отображается первым при запуске приложения и при нажатии на кнопку «Characters» в BottomNavigationView. Предусматривается возможность поиска и фильтрации по данной вкладке. Фильтрация выполняется в соответствии с описанием запроса для вкладки: https://rickandmortyapi.com/documentation/#filter-characters. Каждый элемент списка содержит: Название персонажа (name), Вид (species), статус (status), пол (gender) и картинку (image). Данный список поддерживает пагинацию.

•	Фрагмент с подробным описанием выбранного персонажа. Этот фрагмент отображается при нажатии на выбранного персонажа из списка. Источник:
https://rickandmortyapi.com/documentation/#get-a-single-character
При нажатии на эпизод отображаются детали выбранного эпизода. При нажатии на локацию (location) или же место происхождения (origin) открываются детали выбранной локации.

•	Фрагмент со списком локаций (Locations) в 2 столбца. Этот фрагмент отображается при нажатии на кнопку «Locations» в BottomNavigationView. Предусматривается возможность поиска и фильтрации по данной вкладке. Фильтрация выполняется в соответствии с описанием запроса для вкладки: https://rickandmortyapi.com/documentation/#filter-locations. Каждый элемент списка содержит: Название локации (name), тип (type) и измерение (dimension). Данный список поддерживает пагинацию.

•	Фрагмент с подробным описанием выбранной локации. Этот фрагмент отображается при нажатии на выбранной локации из списка. Источник:
https://rickandmortyapi.com/documentation/#get-a-single-location
Список персонажей отображается  в 2 столбца, как на вкладке с персонажами, с помощью запроса
https://rickandmortyapi.com/documentation/#get-multiple-characters
При нажатии на персонажа открываются детали персонажа.

•	Фрагмент со списком эпизодов (Episodes) в 2 столбца. Этот фрагмент отображается при нажатии на кнопку «Episodes» в BottomNavigationView. Предусматривается возможность поиска и фильтрации по данной вкладке. Фильтрация выполняется в соответствии с описанием запроса для вкладки: https://rickandmortyapi.com/documentation/#filter-episodes. Каждый элемент списка содержит: Название эпизода (name), номер эпизода (episode) и дату релиза (air_date). Данный список поддерживает пагинацию.

•	Фрагмент с подробным описанием выбранного эпизода. Этот фрагмент отображается при нажатии на выбранного эпизода из списка. Источник:
https://rickandmortyapi.com/documentation/#get-a-single-episode
Список персонажей отображается  в 2 столбца, как на вкладке с персонажами, с помощью запроса
https://rickandmortyapi.com/documentation/#get-multiple-characters
При нажатии на персонажа открываются детали персонажа.

Исходный код проекта на GitHub:
https://github.com/SvetlanaKupchenya/RickAndMorty

APK в формате релиза на GitHub:
https://github.com/SvetlanaKupchenya/RickAndMorty/tree/main/app/build/outputs/apk/debug

История задач, входящих в релиз
1. Выпуск технического задания на проект.
2. Создание приложения на основе "Bottom Navigation Activity" с тремя фрагментами в меню навигации: Characters, Locations, Episodes в папках characters, episodes, locations.
3. Заполнение layout для трех основных фрагментов, списки на основе RecyclerView (на начальном этапе грузилась только первая страница запроса). Парсинг JSON-файлов с помощью Retrofit и стандартных вызовов Call, Callback
4. Добавление дополнительных фрагментов с деталями персонажа, локации, эпизода в граф навигации (characterdetails, episodedetails, locationdetails). Перемещение между фрагментами – с помощью addToBackStack по кнопке со стрелкой "Назад".
5. Добавление полей фильтрации на вкладки Characters, Locations, Episodes по различным параметрам с помощью запросов
https://rickandmortyapi.com/documentation/#filter-characters
https://rickandmortyapi.com/documentation/#filter-locations
https://rickandmortyapi.com/documentation/#filter-episodes
Фильтрация осуществляется безопасным способом в блоке try-catch, при отсутствии элементов по заданным параметрам ловится исключение, приложение продолжает работать, в консоль выводится сообщение о том, что элементы не найдены, список сохраняется в исходном состоянии.
6. Рефакторинг вкладок Characters, Locations, Episodes: замена вызовов Call, Callback на корутины (suspend fun load), внедрение пагинации (PagingAdapter для RecyclerView) и кеширования. Реализация ViewMolel на этих трех вкладках. С данного этапа списки на всех трех вкладках отображают все страницы запроса, а не только первую.
7. Рефакторинг вкладок episodedetails и locationdetails. С данного этапа список персонажей отображается в 2 столбца так, как на вкладке с персонажами, с помощью вызовов Call, Callback и запроса
https://rickandmortyapi.com/documentation/#get-multiple-characters
8. Создание простейшего Unit-теста для функции areContentsTheSame из RecycleView-адаптера, поддерживающего пагинацию:
https://github.com/SvetlanaKupchenya/RickAndMorty/blob/main/app/src/test/java/com/example/rickandmorty/ContentsTheSameUtilTest.kt
https://github.com/SvetlanaKupchenya/RickAndMorty/blob/main/app/src/main/java/com/example/rickandmorty/ContentsTheSameUtil.kt
9. Проверка всех функций приложения на работоспособность и обработку некорректных нажатий, исправление дефектов.

История задач, которые НЕ удалось реализовать в проекте:
1. Рефакторинг приложения с применением Dagger и Hilt: откат, так как не удалось корректно сочетать и отладить библиотеки, classpath и репозиторий. Приложение падало с ошибкой "can't create example of MainActivity".
2. Не удалось соблюсти правила "Каждый класс/объект/интрефейс - в отдельном файле" и "Повторяющийся код - в отдельные функции".

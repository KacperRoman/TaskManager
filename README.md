# TaskManager

Aplikacja webowa do zarządzania zadaniami zbudowana w oparciu o Spring Boot.

## Opis projektu

TaskManager to system umożliwiający użytkownikom tworzenie, edytowanie, usuwanie i śledzenie swoich zadań. Aplikacja oferuje bezpieczny dostęp poprzez system autentykacji i autoryzacji oraz przyjazny interfejs użytkownika.

## Funkcjonalności

- **Zarządzanie zadaniami**: tworzenie, edytowanie, usuwanie i wyświetlanie zadań
- **Autentykacja użytkowników**: bezpieczne logowanie i różne role użytkowników
- **REST API**: pełne REST API do operacji na zadaniach i użytkownikach
- **Interfejs webowy**: responsywny interfejs HTML/CSS z Thymeleaf
- **Logowanie**: automatyczne logowanie operacji poprzez AOP
- **Baza danych**: in-memory baza danych H2

## Technologia

### Backend
- **Java 25**
- **Spring Boot 4.0.6**
- **Spring Data JPA** - mapowanie obiektowo-relacyjne
- **Spring Security** - autentykacja i autoryzacja
- **Spring Data REST** - automatyczne REST API
- **Spring AOP** - logowanie i aspekty
- **H2 Database** - wbudowana baza danych
- **Lombok** - redukacja boilerplate'u kodu
- **JUnit 5** - testowanie

### Frontend
- **Thymeleaf** - template engine
- **HTML5/CSS3**
- **Spring Security Extras** - integracja z Thymeleaf

## Struktura projektu

```
src/main/java/com/github/kacperroman/taskmanager/
├── TaskManagerApplication.java      # Główna klasa aplikacji
├── aspect/
│   └── LoggingAspect.java          # Aspekt do logowania operacji
├── config/
│   └── SecurityConfig.java         # Konfiguracja bezpieczeństwa
├── controller/
│   ├── LoginController.java        # Obsługa logowania
│   ├── TaskController.java         # REST API dla zadań
│   ├── TaskUIController.java       # UI dla zadań
│   └── UserController.java         # REST API dla użytkowników
├── init/
│   └── DataLoader.java             # Inicjalizacja danych
├── model/
│   ├── Task.java                   # Model zadania
│   ├── User.java                   # Model użytkownika
│   └── Role.java                   # Model roli
├── repository/
│   ├── TaskRepository.java         # Repozytorium zadań
│   └── UserRepository.java         # Repozytorium użytkowników
└── service/
    ├── TaskService.java            # Serwis zadań
    └── UserService.java            # Serwis użytkowników

src/main/resources/
├── application.properties           # Konfiguracja aplikacji
├── templates/
│   ├── login.html                  # Strona logowania
│   └── tasks.html                  # Strona zadań
└── static/
    └── styles.css                  # Style CSS
```

## Instalacja

### Wymagania
- Java 25 lub wyższa
- Maven 3.6+
- Git

### Kroki instalacji

1. Klonowanie repozytorium:
```bash
git clone https://github.com/kacperroman/TaskManager.git
cd TaskManager
```

2. Kompilacja projektu:
```bash
./mvnw clean install
```

3. Uruchomienie aplikacji:
```bash
./mvnw spring-boot:run
```

Aplikacja będzie dostępna pod adresem: `http://localhost:8080`

## Użytkowanie

### Domyślne dane logowania

Aplikacja jest inicjalizowana z domyślnymi użytkownikami (zobacz `DataLoader.java`).

### Interfejs użytkownika

1. Przejdź na stronę główną: `http://localhost:8080`
2. Zaloguj się za pomocą swoich danych
3. Zarządzaj swoimi zadaniami na głównym panelu

### REST API

Aplikacja udostępnia REST API dostępne pod ścieżką `/api/`:

**Przykłady:**
- `GET /api/tasks` - pobranie wszystkich zadań
- `POST /api/tasks` - utworzenie nowego zadania
- `GET /api/tasks/{id}` - pobranie konkretnego zadania
- `PUT /api/tasks/{id}` - aktualizacja zadania
- `DELETE /api/tasks/{id}` - usunięcie zadania

## Testowanie

Uruchomienie testów:
```bash
./mvnw test
```

Testy obejmują:
- `TaskControllerTest.java` - testy kontrolera zadań
- `TaskServiceTest.java` - testy serwisu zadań
- `TaskManagerApplicationTests.java` - testy integracyjne

## Konfiguracja

Plik `application.properties` zawiera konfigurację aplikacji:

```properties
spring.application.name=TaskManager
```

## Baza danych

Aplikacja używa bazy danych H2, która jest in-memory bazą danych i resetuje się przy każdym uruchomieniu. Dane są ładowane z klasy `DataLoader`.

Konsola H2: `http://localhost:8080/h2-console`

## Bezpieczeństwo

Aplikacja wykorzystuje Spring Security z następującymi funkcjami:

- Autentykacja na bazie użytkownika i hasła
- Rola oparta na autoryzacji
- CSRF protection
- XSS protection poprzez Thymeleaf



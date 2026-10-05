# Repo Inventory

## Source packages

```
src/main/java/com/sem4/bookauthor
├── BookauthorApplication.java
├── ServletInitializer.java          — WAR bootstrap
├── config/DataSeeder.java
├── controller/                      — Home, Book, Author, Publisher
├── service/                         — Book, Author, AuthorProfile, Publisher
├── repository/                      — one JpaRepository per entity
├── entity/                          — Book, Author, AuthorProfile, Publisher
├── dto/                             — *DTO, *SummaryDTO (id, name), *Form
├── mapper/                          — Book, Author, Publisher
└── exception/                       — ResourceNotFound, Business, WebAdvice

src/test/java/com/sem4/bookauthor
└── BookauthorApplicationTests.java  — context load only
```

## Templates and static files

- `templates/index.html`, `templates/error.html`, `templates/fragments/layout.html`
- `templates/books|authors|publishers/` each have `list.html`, `form.html`, `detail.html`
- `static/css/style.css`, `static/favicon.svg`

## Build

- Maven WAR, Spring Boot 4.1.1, Java 17
- WebMVC, Thymeleaf, Data JPA, Validation, H2 console, H2, MySQL driver (unused), Lombok, DevTools
- Tomcat is `provided` for external deployment

## Runtime config

- H2 mem `bookauthor`, `ddl-auto=update`, `open-in-view=false`, port 8080
- H2 console at `/h2-console`
- Test profile uses a separate in-memory database

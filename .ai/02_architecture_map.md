# Architecture Map

## Style

One Spring Boot web application. Server-rendered HTML. No separate frontend build.

```
Browser
  -> Controller (routes, forms, redirects)
    -> Service (rules, transactions)
      -> Mapper (entity <-> DTO / form)
      -> Repository (Spring Data JPA)
        -> Entity
          -> H2
  <- Thymeleaf templates + static CSS
```

`open-in-view` is off. Templates receive DTOs, not entities.

## Domain

- `Publisher` 1—N `Book`. The book owns `publisher_id`.
- `Book` N—N `Author` through `book_author`. The book owns the link.
- `Author` 1—1 `AuthorProfile`. The profile owns `author_id`. Biography is optional.

## Packages

| Package | Role |
| --- | --- |
| `controller` | `HomeController`, `BookController`, `AuthorController`, `PublisherController` |
| `service` | Save, search, delete, and relationship rules |
| `repository` | JPA queries and entity graphs for reads |
| `entity` | Persistence model |
| `dto` | View models and form objects (`*Form`) |
| `mapper` | Entity to DTO and form |
| `exception` | Not-found and business errors; `WebAdvice` for binding and the 404 page |
| `config` | `DataSeeder` when all three tables are empty |

## Request path

1. GET renders a list, detail, or empty form.
2. POST binds a `*Form`, validates it, and either redisplays the form or redirects.
3. Services trim input, load related rows, update the owning side of each association, then save.
4. Flash messages `success` and `error` show after redirect.

## Routes

- `/` dashboard
- `/books`, `/authors`, `/publishers` with `/new`, `/{id}`, `/{id}/edit`, and `POST /{id}/delete`
- `/h2-console` for the embedded database console

## Decisions

- Forms use `*Form` objects. Detail and list pages use DTOs so circular entity graphs never reach the view.
- Deletes are POST forms, not GET links.
- Publisher deletion is refused while `countByPublisherId` is greater than zero.
- Author deletion detaches join rows first, then removes the profile and the author.
- Thymeleaf layout is a fragment in `templates/fragments/layout.html`. Active navigation uses `currentPath` from `WebAdvice`.

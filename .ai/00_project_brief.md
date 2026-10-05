# Project Brief

## Purpose

`bookauthor` is a small library catalog. A user manages books, authors, and publishers, including the links between them, through server-rendered pages.

## Users

- One catalog editor. There is no login and no role split.

## In scope

- List, search by name, create, view, update, and delete publishers, authors, and books.
- A book belongs to one publisher and may have many authors.
- An author has one optional profile (biography).
- Deleting an author keeps the books and removes that author from them.
- A publisher that still has books cannot be deleted.
- Sample catalog data loads when the database is empty.
- Missing records show a not-found page. Invalid forms stay on the form with field errors.

## Out of scope

- Authentication, accounts, and permissions.
- REST API, file upload, pagination, and publishing workflow.
- Persistent storage across restarts. The default database is in-memory H2.

## Stack

- Java 17, Spring Boot 4.1.1, Spring MVC, Thymeleaf, Bean Validation, Spring Data JPA.
- H2 in memory on port 8080. MySQL driver is on the classpath but unused.
- Packaged as a WAR so it can also deploy to an external servlet container.

## Done when

- The home page shows counts and recent books.
- Each catalog section supports the flows above without breaking the other two.
- Relationship rules are enforced in the service layer, not only in the browser.

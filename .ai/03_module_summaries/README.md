# Module Summaries

## config

- `DataSeeder` loads three publishers, three authors with bios, and four books only when all three tables are empty.
- Must not seed again once any of those tables has rows.

## controller

- Not yet implemented
- Will bind forms, checks `BindingResult`, calls a service, then renders or redirects with a flash message.
- `HomeController` will show counts and at most five books.
- Must not call repositories or apply relationship rules.

## service

- Transaction boundary. Reads are `readOnly`; writes are `@Transactional`.
- Trims input, loads related rows, and updates the owning side of each association.
- **PublisherService**: Implemented with CRUD operations, refuses delete when books remain.
- **AuthorService**: Implemented with CRUD operations, unlinks books, then removes the profile and the author.
- **BookService**: Implemented with CRUD operations, replaces the book's author list and requires a real publisher.
- Returns DTOs and forms, not entities.

## repository

- Spring Data JPA. Book and author reads use `@EntityGraph` so the service can map associations with `open-in-view` off.
- Book queries include publisher counts and books-by-author.
- Must not validate business rules.
- **PublisherRepository**: Implemented, extends JpaRepository<Publisher, Long>
- **BookRepository**: Implemented, extends JpaRepository<Book, Long>
- **AuthorRepository**: Implemented, extends JpaRepository<Author, Long>
- **AuthorProfileRepository**: Implemented, extends JpaRepository<AuthorProfile, Long>

## entity

- `Book` owns `publisher_id` and the `book_author` join table.
- `AuthorProfile` owns `author_id`. `Author.authorProfile` is the inverse side and cascades all operations.
- Lombok getters and setters. No Bean Validation on entities.
- **Publisher**: Implemented with id, name, OneToMany to Book
- **Book**: Implemented with id, name, description, ManyToOne to Publisher, ManyToMany to Author
- **Author**: Implemented with id, name, email, ManyToMany to Book, OneToOne to AuthorProfile
- **AuthorProfile**: Implemented with id, bio, OneToOne to Author

## dto

- `*DTO` is the detail and list model. `AuthorDTO` carries books and profile. `BookDTO` carries publisher and authors.
- `*SummaryDTO` is only `id` and `name`, used for the other side of a link.
- `*Form` carries Bean Validation for create and edit.
- **PublisherDTO**: Implemented with id, name
- **BookDTO**: Implemented with id, name, description, PublisherDTO publisher, List<AuthorSummaryDTO> authors
- **AuthorDTO**: Implemented with id, name, email, List<BookSummaryDTO> books, AuthorProfileDTO authorProfile
- **AuthorProfileDTO**: Implemented with id, bio
- **AuthorSummaryDTO**: Implemented with id, name (for use in BookDTO)
- **BookSummaryDTO**: Implemented with id, name (for use in AuthorDTO)

## mapper

- Not yet implemented
- Will be static copies between entity, DTO, summary, and form.
- Must not query the database.

## exception

- Not yet implemented
- `ResourceNotFoundException` becomes the 404 page.
- `BusinessException` is caught by the publisher delete action and shown as a flash error.
- `WebAdvice` adds `currentPath` and allows an empty form id to bind as null.
# Use Cases

Actor for UC-01 through UC-14 is the catalog editor. There is no login. Requirement ids match `docs/BA/requirements.md`.

## UC-01 View dashboard (BR-01)

1. Open `/`.
2. The page shows three counts and at most five latest books.

## UC-02 List publishers (BR-02)

1. Open `/publishers` or submit a name keyword.
2. Blank keyword returns every publisher, newest id first, with a book count.

## UC-03 Save publisher (BR-03)

1. Open create or edit.
2. Submit the name.
3. Valid input redirects to the list. Invalid input stays on the form.

## UC-04 View publisher (BR-04)

1. Open `/publishers/{id}`.
2. The page lists that publisher's books. Unknown id shows the not-found page.

## UC-05 Delete publisher (BR-05)

1. Submit the delete form.
2. Zero books: the publisher is removed.
3. One or more books: the list returns with an error and the publisher stays.

## UC-06 List authors (BR-06)

1. Open `/authors` or search by name.
2. Each row shows email, number of books, and whether a biography exists.

## UC-07 Save author (BR-07, BR-08)

1. Submit name, email, and optional biography.
2. A blank biography deletes the profile. A filled one creates or updates the single profile.
3. Invalid email or a blank name stays on the form.

## UC-08 View author (BR-09)

1. Open `/authors/{id}`.
2. The page shows email, biography, and linked books.

## UC-09 Delete author (BR-10)

1. Submit delete.
2. The author is removed from each book, the profile is removed, then the author is removed.
3. The books remain.

## UC-10 List books (BR-11)

1. Open `/books` or search by title.
2. Each row shows the publisher and the author names.

## UC-11 Save book (BR-12, BR-13)

1. Submit title, optional description, one publisher, and any authors.
2. The selected authors replace the previous set.
3. A missing publisher or author id shows the not-found page. A missing publisher choice stays on the form.

## UC-12 View book (BR-14)

1. Open `/books/{id}`.
2. Publisher and author names link to their detail pages.

## UC-13 Delete book (BR-15)

1. Submit delete.
2. The book and its author links are removed. Publishers and authors stay.

## UC-14 Not found (BR-16)

1. Open a missing id.
2. `error.html` renders with status 404 and the service message.

## UC-15 Seed catalog (BR-17)

- Actor: the application at startup.
- If publishers, authors, and books are all empty, seed NXB Trẻ, NXB Kim Đồng, Nhã Nam, three authors, and four books.
- If any table already has rows, skip seeding.

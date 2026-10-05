# Requirements

## Actor

Catalog editor. Opens the site, with no sign-in.

## Functional requirements

| ID | Requirement |
| --- | --- |
| BR-01 | The home page shows how many books, authors, and publishers exist, plus up to five latest books. |
| BR-02 | The editor can list publishers and search them by name. |
| BR-03 | The editor can create and update a publisher name. |
| BR-04 | The editor can open a publisher and see the books it published. |
| BR-05 | The editor can delete a publisher only when it has no books. Otherwise the list explains why. |
| BR-06 | The editor can list authors and search them by name. The list shows email, book count, and whether a biography exists. |
| BR-07 | The editor can create and update an author's name, email, and optional biography. |
| BR-08 | An empty biography removes the author profile. A filled biography creates or updates that one profile. |
| BR-09 | The editor can open an author and see email, biography, and linked books. |
| BR-10 | Deleting an author removes the profile and unlinks the author from books. The books remain. |
| BR-11 | The editor can list books and search them by name. Each row shows publisher and authors. |
| BR-12 | The editor can create and update a book name, description, one publisher, and any number of authors. |
| BR-13 | A book requires a publisher. Authors are optional. Description is optional. |
| BR-14 | The editor can open a book and follow links to its publisher and authors. |
| BR-15 | Deleting a book removes that book and its author links only. |
| BR-16 | An unknown id shows a not-found page with the reason. |
| BR-17 | When the database has no publishers, authors, or books, the app loads a small sample catalog. |

## Validation

- Publisher name, author name, book name: required, at most 255 characters.
- Author email: required, email format, at most 255 characters.
- Biography and book description: optional, at most 2000 characters.
- Publisher on a book: required.
- Failed validation redisplays the same form with the field message.

## Business rules

- One book has one publisher.
- One book has zero or more authors. One author has zero or more books.
- One author has at most one profile.
- The publisher cannot be removed while any book still points at it.

## Non-functional

- Pages are Vietnamese, server-rendered, and usable on a narrow screen.
- The app runs locally at `http://127.0.0.1:8080`.
- Data lives in memory and is recreated, including the sample catalog, after a restart.

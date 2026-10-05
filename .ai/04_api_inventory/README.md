# Page Inventory

Server-rendered pages. There is no JSON API. Search uses `GET ?q=`.

## Home

| Method | Path | Result |
| --- | --- | --- |
| GET | `/` | Counts and up to five latest books |

## Publishers

| Method | Path | Input | Success | Failure |
| --- | --- | --- | --- | --- |
| GET | `/publishers` | optional `q` | List plus book counts | Empty list message |
| GET | `/publishers/new` | | Empty form | |
| GET | `/publishers/{id}` | | Detail and its books | 404 |
| GET | `/publishers/{id}/edit` | | Filled form | 404 |
| POST | `/publishers` | `id`, `name` | Redirect to the list | Redisplay field errors |
| POST | `/publishers/{id}/delete` | | Redirect, success flash | Redirect, error flash if books remain |

`name` is required, at most 255 characters.

## Authors

| Method | Path | Input | Success | Failure |
| --- | --- | --- | --- | --- |
| GET | `/authors` | optional `q` | Name, email, book count, profile flag | Empty list message |
| GET | `/authors/new` | | Empty form | |
| GET | `/authors/{id}` | | Email, bio, books | 404 |
| GET | `/authors/{id}/edit` | | Filled form | 404 |
| POST | `/authors` | `id`, `name`, `email`, `bio` | Redirect. Blank bio removes the profile | Redisplay field errors |
| POST | `/authors/{id}/delete` | | Author and profile gone; books remain | 404 |

`name` and `email` are required. `bio` is optional, at most 2000 characters on the form.

## Books

| Method | Path | Input | Success | Failure |
| --- | --- | --- | --- | --- |
| GET | `/books` | optional `q` | Name, publisher, authors | Empty list message |
| GET | `/books/new` | | Form plus publisher and author choices | |
| GET | `/books/{id}` | | Detail with links | 404 |
| GET | `/books/{id}/edit` | | Filled form and choices | 404 |
| POST | `/books` | `id`, `name`, `description`, `publisherId`, `authorIds` | Redirect. Authors are replaced by the selection | Field errors, or 404 if a chosen id is missing |
| POST | `/books/{id}/delete` | | Book and join rows gone | 404 |

`publisherId` is required. `authorIds` may be empty. `description` is optional.

## Other

- `GET /h2-console` opens the H2 UI in the same process.
- An unknown catalog id renders `error.html` with status 404.

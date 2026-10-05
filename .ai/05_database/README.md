# Database

## Settings

- URL: `jdbc:h2:mem:bookauthor;DB_CLOSE_DELAY=-1`
- User `sa`, empty password
- `ddl-auto=update`. Schema comes from the entities. Data disappears on restart.
- Console: `/h2-console`

String columns have no explicit `length`, so Hibernate maps them to `varchar(255)`. Form rules allow 2000 characters for biography and description; those values can fail at insert time.

## Tables

### publisher

| Column | Nullable | Notes |
| --- | --- | --- |
| id | no | identity |
| name | no | |

### authors

| Column | Nullable | Notes |
| --- | --- | --- |
| id | no | identity |
| name | no | |
| email | no | not unique |

### author_profile

| Column | Nullable | Notes |
| --- | --- | --- |
| id | no | identity |
| bio | no | |
| author_id | no | FK to `authors.id`, owned by the profile |

### books

| Column | Nullable | Notes |
| --- | --- | --- |
| id | no | identity |
| name | no | |
| description | yes | |
| publisher_id | no | FK to `publisher.id`, owned by the book |

### book_author

| Column | Notes |
| --- | --- |
| book_id | FK, owned by `Book.authors` |
| author_id | FK |

## Delete behavior

- Publisher delete is blocked in the service while any book points at it. There is no database `ON DELETE` rule.
- Deleting a book removes its `book_author` rows because the book owns that table.
- Deleting an author first removes those join rows, then deletes the profile, then the author.
- A blank biography deletes the profile row. A filled biography inserts or updates it.

## Load graphs

- Book reads fetch `publisher` and `authors`.
- Author reads fetch `authorProfile` and `books`.
- Publisher lists do not fetch the book collection; counts come from a grouped query.

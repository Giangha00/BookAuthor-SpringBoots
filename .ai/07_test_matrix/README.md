# Test Matrix

## Automated

| Case                                                  | Status                                                                                  |
| ----------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `BookauthorApplicationTests.contextLoads`             | Automated. Context starts and the seeder runs against the test database.                |
| `EntityGraphIntegrationTest.testBookEntityGraph`      | Automated. Verifies BookRepository eagerly loads publisher and authors without N+1.     |
| `EntityGraphIntegrationTest.testAuthorEntityGraph`    | Automated. Verifies AuthorRepository eagerly loads authorProfile and books without N+1. |
| `EntityGraphIntegrationTest.testPublisherEntityGraph` | Automated. Verifies PublisherRepository eagerly loads books without N+1.                |

No controller or service test exists.

## Manual

| ID   | Case                                           | Expected                                                  |
| ---- | ---------------------------------------------- | --------------------------------------------------------- |
| T-01 | Open `/` on an empty database                  | Counts are 4 books, 3 authors, 3 publishers               |
| T-02 | Search each list with a partial name           | Case-insensitive match; blank query shows all             |
| T-03 | Save a publisher with a blank name             | Form error, nothing stored                                |
| T-04 | Save a publisher, then edit the name           | List shows the new name                                   |
| T-05 | Delete a publisher that still has books        | Error flash, row remains                                  |
| T-06 | Delete a publisher with no books               | Row is gone                                               |
| T-07 | Save an author with a bad email                | Form error                                                |
| T-08 | Save an author with a biography, then clear it | Profile disappears from the detail page                   |
| T-09 | Delete an author who is on a book              | Book remains and no longer lists that author              |
| T-10 | Save a book with no publisher                  | Form error                                                |
| T-11 | Save a book with one publisher and two authors | Detail shows both links                                   |
| T-12 | Edit the book and leave one author             | The other author link is gone                             |
| T-13 | Delete the book                                | Publisher and authors remain                              |
| T-14 | Open `/books/9999`                             | 404 page                                                  |
| T-15 | Restart the app                                | Memory database is empty, then the sample catalog returns |

## Not covered

- Column length versus the 2000-character form limit on biography and description.
- Whitespace-only input after trim.
- Two editors changing the same row.
- A grouped publisher count after mixed deletes.

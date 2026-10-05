# MVP Gap Analysis

## Satisfied

BR-01 through BR-17 are implemented: catalog CRUD, search, profile sync, publisher delete guard, author unlink, not-found page, and startup seed. Evidence is the controller and service classes named in `.ai/02_architecture_map.md`.

## Gaps that are real

- No accounts or permissions. Anyone who can open the site can change the catalog.
- Data is in-memory H2. A restart drops it and the seeder fills the sample set again.
- `mysql-connector-j` is on the classpath and is not configured.
- Lists load every matching row. There is no page size.
- The only automated test loads the Spring context.
- Entities have no `@Version`, so two saves of the same row last-write-wins.
- POST forms have no CSRF token because Spring Security is not a dependency.
- Biography and description forms allow 2000 characters while the columns are `varchar(255)`.
- Messages are hardcoded Vietnamese strings, not message bundles.
- Deletes are permanent. There is no history of who changed a row.

## Not a gap

Authentication, file upload, and a REST API are outside `.ai/00_project_brief.md`. They are not missing MVP behavior.

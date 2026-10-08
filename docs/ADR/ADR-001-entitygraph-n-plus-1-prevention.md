# ADR 001: Ngăn chặn vấn đề N+1 Query bằng JPA EntityGraph

## Trạng thái

Đã chấp thuận (Accepted)

## Bối cảnh

Trong hệ thống quản lý Sách - Tác giả - Nhà xuất bản:

- Quan hệ giữa `Book` và `Publisher` là `@ManyToOne` (mặc định trong JPA là Eager fetch nếu không chỉ định rõ ràng).
- Quan hệ giữa `Book` và `Author` là `@ManyToMany` (Lazy fetch).
- Quan hệ giữa `Author` và `AuthorProfile` là `@OneToOne` (Lazy fetch).
- Quan hệ giữa `Publisher` và `Book` là `@OneToMany` (Lazy fetch).

Khi truy vấn danh sách dữ liệu và chuyển đổi sang DTO (như `BookDTO` yêu cầu `publisher` và danh sách tóm tắt `authors`, `AuthorDTO` yêu cầu `authorProfile` và `books`), việc gọi các getter trên các quan hệ Lazy sẽ kích hoạt thêm N câu lệnh SELECT riêng lẻ cho từng phần tử trong danh sách, gây ra vấn đề **N+1 Query**, làm chậm hệ thống và tốn tài nguyên kết nối cơ sở dữ liệu.

## Quyết định Kiến trúc

1. **Chuyển toàn bộ quan hệ sang LAZY:**
   - Cập nhật `@ManyToOne(fetch = FetchType.LAZY)` trong [Book.java](file:///Users/macbook/T2502E_SEM4/bookauthor/src/main/java/com/sem4/bookauthor/entity/Book.java) đối với `Publisher`.
2. **Khai báo `@NamedEntityGraph` trên các Entity:**
   - `Book`: NamedEntityGraph `Book.full` với attribute nodes `publisher`, `authors`.
   - `Author`: NamedEntityGraph `Author.full` với attribute nodes `authorProfile`, `books`.
   - `Publisher`: NamedEntityGraph `Publisher.withBooks` với attribute nodes `books`.
   - `AuthorProfile`: NamedEntityGraph `AuthorProfile.withAuthor` với attribute nodes `author`.
3. **Áp dụng `@EntityGraph` trong Spring Data JPA Repositories:**
   - [BookRepository.java](file:///Users/macbook/T2502E_SEM4/bookauthor/src/main/java/com/sem4/bookauthor/repository/BookRepository.java): Bổ sung `@EntityGraph(attributePaths = {"publisher", "authors"})` cho `findById`, `findAll()`, `findAllByOrderByIdDesc()`, `findByNameContainingIgnoreCaseOrderByIdDesc()`, `findByAuthorsId()`, và `findByPublisherIdOrderByIdDesc()`.
   - [AuthorRepository.java](file:///Users/macbook/T2502E_SEM4/bookauthor/src/main/java/com/sem4/bookauthor/repository/AuthorRepository.java): Bổ sung `@EntityGraph(attributePaths = {"authorProfile", "books"})` cho `findById`, `findAll()`, `findAllByOrderByIdDesc()`, `findByNameContainingIgnoreCaseOrderByIdDesc()`.
   - [PublisherRepository.java](file:///Users/macbook/T2502E_SEM4/bookauthor/src/main/java/com/sem4/bookauthor/repository/PublisherRepository.java): Bổ sung `@EntityGraph(attributePaths = "books")` cho `findById`, `findAll()`, `findAllByOrderByIdDesc()`, `findByNameContainingIgnoreCaseOrderByIdDesc()`.
   - [AuthorProfileRepository.java](file:///Users/macbook/T2502E_SEM4/bookauthor/src/main/java/com/sem4/bookauthor/repository/AuthorProfileRepository.java): Bổ sung `@EntityGraph(attributePaths = "author")` cho `findById`, `findAll()`.
4. **Viết kiểm thử tự động:**
   - Thêm [EntityGraphIntegrationTest.java](file:///Users/macbook/T2502E_SEM4/bookauthor/src/test/java/com/sem4/bookauthor/repository/EntityGraphIntegrationTest.java) xác minh các quan hệ được tải trọn vẹn trong một câu truy vấn JOIN duy nhất.

## Hệ quả

- **Tích cực:** Loại bỏ hoàn toàn các câu lệnh SELECT lẻ tẻ (N+1), giảm thiểu độ trễ mạng và số lượng round-trip tới database. Tránh lỗi `LazyInitializationException` khi `spring.jpa.open-in-view=false`.
- **Cân nhắc:** Cần chú ý khi mở rộng thêm quan hệ nhiều-nhiều hoặc nhiều collection trong cùng một EntityGraph để tránh `MultipleBagFetchException`.

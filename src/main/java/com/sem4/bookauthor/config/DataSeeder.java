package com.sem4.bookauthor.config;

import com.sem4.bookauthor.dto.AuthorForm;
import com.sem4.bookauthor.dto.BookForm;
import com.sem4.bookauthor.dto.PublisherForm;
import com.sem4.bookauthor.service.AuthorService;
import com.sem4.bookauthor.service.BookService;
import com.sem4.bookauthor.service.PublisherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final BookService bookService;

    public DataSeeder(PublisherService publisherService, AuthorService authorService, BookService bookService) {
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        if (publisherService.count() > 0 || authorService.count() > 0 || bookService.count() > 0) {
            return;
        }

        Long tre = savePublisher("NXB Trẻ");
        Long kimDong = savePublisher("NXB Kim Đồng");
        Long nhaNam = savePublisher("Nhã Nam");

        Long nguyenNhatAnh = saveAuthor(
                "Nguyễn Nhật Ánh",
                "nhat.anh@example.com",
                "Nhà văn thiếu nhi với nhiều tác phẩm về tuổi học trò miền Nam."
        );
        Long toHoai = saveAuthor(
                "Tô Hoài",
                "to.hoai@example.com",
                "Tác giả Dế Mèn phiêu lưu ký và nhiều truyện ký về Hà Nội."
        );
        Long namCao = saveAuthor(
                "Nam Cao",
                "nam.cao@example.com",
                "Nhà văn hiện thực, tác giả Chí Phèo và Lão Hạc."
        );

        saveBook("Cho tôi xin một vé đi tuổi thơ", "Hồi ức tuổi thơ qua những trò chơi cũ.", tre, List.of(nguyenNhatAnh));
        saveBook("Tôi thấy hoa vàng trên cỏ xanh", "Câu chuyện tuổi thơ ở một làng quê.", tre, List.of(nguyenNhatAnh));
        saveBook("Dế Mèn phiêu lưu ký", "Hành trình trưởng thành của chú dế mèn.", kimDong, List.of(toHoai));
        saveBook("Chí Phèo", "Số phận của người nông dân bị đẩy ra ngoài lề xã hội.", nhaNam, List.of(namCao, toHoai));
    }

    private Long savePublisher(String name) {
        PublisherForm form = new PublisherForm();
        form.setName(name);
        return publisherService.save(form).getId();
    }

    private Long saveAuthor(String name, String email, String bio) {
        AuthorForm form = new AuthorForm();
        form.setName(name);
        form.setEmail(email);
        form.setBio(bio);
        return authorService.save(form).getId();
    }

    private void saveBook(String name, String description, Long publisherId, List<Long> authorIds) {
        BookForm form = new BookForm();
        form.setName(name);
        form.setDescription(description);
        form.setPublisherId(publisherId);
        form.setAuthorIds(authorIds);
        bookService.save(form);
    }
}

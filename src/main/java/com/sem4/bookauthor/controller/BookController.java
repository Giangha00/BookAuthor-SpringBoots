package com.sem4.bookauthor.controller;

import com.sem4.bookauthor.dto.BookForm;
import com.sem4.bookauthor.service.AuthorService;
import com.sem4.bookauthor.service.BookService;
import com.sem4.bookauthor.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("pageTitle", "Sách");
        model.addAttribute("keyword", q == null ? "" : q);
        model.addAttribute("books", bookService.search(q));
        return "books/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("pageTitle", "Thêm sách");
        model.addAttribute("bookForm", new BookForm());
        addOptions(model);
        return "books/form";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Chi tiết sách");
        model.addAttribute("book", bookService.findById(id));
        return "books/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Sửa sách");
        model.addAttribute("bookForm", bookService.findFormById(id));
        addOptions(model);
        return "books/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute BookForm bookForm,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", bookForm.getId() == null ? "Thêm sách" : "Sửa sách");
            addOptions(model);
            return "books/form";
        }
        bookService.save(bookForm);
        redirectAttributes.addFlashAttribute("success", "Đã lưu sách.");
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Đã xóa sách.");
        return "redirect:/books";
    }

    private void addOptions(Model model) {
        model.addAttribute("publishers", publisherService.search(null));
        model.addAttribute("authors", authorService.search(null));
    }
}

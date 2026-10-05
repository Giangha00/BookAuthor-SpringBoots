package com.sem4.bookauthor.controller;

import com.sem4.bookauthor.service.AuthorService;
import com.sem4.bookauthor.service.BookService;
import com.sem4.bookauthor.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public HomeController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Thư viện");
        model.addAttribute("bookCount", bookService.count());
        model.addAttribute("authorCount", authorService.count());
        model.addAttribute("publisherCount", publisherService.count());
        model.addAttribute("books", bookService.search(null).stream().limit(5).toList());
        return "index";
    }
}

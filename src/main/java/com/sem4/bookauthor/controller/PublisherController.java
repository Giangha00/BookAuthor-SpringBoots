package com.sem4.bookauthor.controller;

import com.sem4.bookauthor.dto.PublisherForm;
import com.sem4.bookauthor.exception.BusinessException;
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
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;
    private final BookService bookService;

    public PublisherController(PublisherService publisherService, BookService bookService) {
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("pageTitle", "Nhà xuất bản");
        model.addAttribute("keyword", q == null ? "" : q);
        model.addAttribute("publishers", publisherService.search(q));
        model.addAttribute("bookCounts", bookService.countByPublisher());
        return "publishers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("pageTitle", "Thêm nhà xuất bản");
        model.addAttribute("publisherForm", new PublisherForm());
        return "publishers/form";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Chi tiết nhà xuất bản");
        model.addAttribute("publisher", publisherService.findById(id));
        model.addAttribute("books", bookService.findSummariesByPublisher(id));
        return "publishers/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Sửa nhà xuất bản");
        model.addAttribute("publisherForm", publisherService.findFormById(id));
        return "publishers/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute PublisherForm publisherForm,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", publisherForm.getId() == null ? "Thêm nhà xuất bản" : "Sửa nhà xuất bản");
            return "publishers/form";
        }
        publisherService.save(publisherForm);
        redirectAttributes.addFlashAttribute("success", "Đã lưu nhà xuất bản.");
        return "redirect:/publishers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            publisherService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Đã xóa nhà xuất bản.");
        } catch (BusinessException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
        }
        return "redirect:/publishers";
    }
}

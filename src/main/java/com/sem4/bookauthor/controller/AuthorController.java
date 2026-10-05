package com.sem4.bookauthor.controller;

import com.sem4.bookauthor.dto.AuthorForm;
import com.sem4.bookauthor.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("pageTitle", "Tác giả");
        model.addAttribute("keyword", q == null ? "" : q);
        model.addAttribute("authors", authorService.search(q));
        return "authors/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("pageTitle", "Thêm tác giả");
        model.addAttribute("authorForm", new AuthorForm());
        return "authors/form";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Chi tiết tác giả");
        model.addAttribute("author", authorService.findById(id));
        return "authors/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Sửa tác giả");
        model.addAttribute("authorForm", authorService.findFormById(id));
        return "authors/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute AuthorForm authorForm,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", authorForm.getId() == null ? "Thêm tác giả" : "Sửa tác giả");
            return "authors/form";
        }
        authorService.save(authorForm);
        redirectAttributes.addFlashAttribute("success", "Đã lưu tác giả.");
        return "redirect:/authors";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        authorService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Đã xóa tác giả.");
        return "redirect:/authors";
    }
}

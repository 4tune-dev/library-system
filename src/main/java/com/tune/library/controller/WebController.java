package com.tune.library.controller;

import com.tune.library.dto.BookDTO;
import com.tune.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class WebController {

    private final BookService bookService;

    @GetMapping("/")
    public String showDashboard() {
        return "dashboard";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/books/new")
    public String showAddForm(Model model) {
        model.addAttribute("bookDTO", new BookDTO());
        return "add-book";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute BookDTO bookDTO) {
        bookService.createBook(bookDTO);
        return "redirect:/books";
    }

    @GetMapping("/books/search")
    public String showSearchForm() {
        return "search-book";
    }

    @PostMapping("/books/search")
    public String processSearch(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("books", bookService.searchBooksByTitleOrAuthor(keyword));
        return "books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
package com.jpabook.jpastudy.controller;

import com.jpabook.jpastudy.domain.item.Book;
import com.jpabook.jpastudy.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("new")
    public String create(BookForm form) {

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", itemService.findItems());
        return "items/itemList";
    }

    @GetMapping("{itemId}/edit")
    public String updateForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("{itemId}/edit")
    public String updateItem(@ModelAttribute BookForm form) {

        Book book = new Book();
        book.setId(form.getId()); // 이미 jpa에서 다녀왔기 때문에 준영속엔티티 (관리하지 않는 엔티티 -> 기존의 식별자를 가지고 있음)
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }
}

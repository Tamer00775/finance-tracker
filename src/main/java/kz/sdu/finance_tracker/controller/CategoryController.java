package kz.sdu.finance_tracker.controller;

import kz.sdu.finance_tracker.entity.Category;
import kz.sdu.finance_tracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/open-api/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(
            @RequestParam(defaultValue = "income") String type) {
        return ResponseEntity.ok(categoryService.getCategories(type));
    }
}

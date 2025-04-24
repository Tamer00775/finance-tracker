package kz.sdu.finance_tracker.service;

import kz.sdu.finance_tracker.entity.Category;
import kz.sdu.finance_tracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(String type) {
        return categoryRepository.findAllByType(type);
    }
}

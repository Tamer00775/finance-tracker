package kz.sdu.finance_tracker.controller;

import kz.sdu.finance_tracker.dto.CheckDto;
import kz.sdu.finance_tracker.dto.CheckExpenseDto;
import kz.sdu.finance_tracker.service.CheckExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/check")
public class CheckController {

    private final CheckExpenseService checkExpenseService;


    @PostMapping
    public ResponseEntity<String> create(@RequestBody CheckDto checkDto) {
        checkExpenseService.save(checkDto);
        return ResponseEntity.ok("Successfully!");
    }

    @GetMapping
    public ResponseEntity<Page<CheckExpenseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(checkExpenseService.findAll(pageable));
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam String objectName) {
        try (InputStream imageStream = checkExpenseService.getImage(objectName)) {
            byte[] imageBytes = imageStream.readAllBytes();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + objectName + "\"")
                    .contentType(MediaType.IMAGE_PNG) // Укажите тип вашего изображения
                    .body(imageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке изображения: " + objectName, e);
        }
    }
}

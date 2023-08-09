package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Info;
@Tag(name = "ИНФОРМАЦИЯ")
@RestController
public class InfoController {


    private final Info info;

    @Autowired
    public InfoController(Info info) {
        this.info = info;
    }
@Operation(summary = "INFO")
    @GetMapping("/app_info")
    public ResponseEntity<Info> appInfo() {
        return ResponseEntity.ok(info);
    }
}

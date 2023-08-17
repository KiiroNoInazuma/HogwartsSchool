package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class AvatarController {

    private final AvatarService avatarService;
    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "cover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upload(@RequestParam Long id, @RequestParam MultipartFile cover) throws IOException {
        avatarService.upload(id, cover);
        return ResponseEntity.ok().build();
    }

    @GetMapping("pic_bd/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long imageId) {
        Avatar avatar = avatarService.avatar(imageId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }


    @GetMapping("pic_hd")
    public void getPicHd(@RequestParam long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.avatar(id);
        try (FileInputStream fileInputStream = new FileInputStream(avatar.getFilePath());
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            fileInputStream.transferTo(outputStream);
        }
    }
}

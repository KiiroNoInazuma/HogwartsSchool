package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Students;
import ru.hogwarts.school.repositories.AvatarRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

@Service
@Transactional
public class AvatarService {
    @Value("${path.my.photo}")
    String path;
    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private MetCrud<Students> metCrud;


    public Avatar avatar(long id) {
        return avatarRepository.findByStudentsId(id).orElse(null);
    }

    private static String point(String txt) {
        return txt.substring(txt.indexOf('.'));
    }

    private byte[] microPic(String file) throws IOException {

        try (InputStream inputStream = new FileInputStream(file);
             BufferedInputStream is = new BufferedInputStream(inputStream, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage originalImage = ImageIO.read(is);
            int height = originalImage.getHeight()/(originalImage.getWidth()/100);
            BufferedImage microImage = new BufferedImage(100, height, originalImage.getType());
            Graphics2D graphics2D = microImage.createGraphics();
            graphics2D.drawImage(originalImage, 0, 0, 100, 100, null);
            graphics2D.dispose();
            ImageIO.write(microImage, "png", baos);
            return baos.toByteArray();
        }
    }

    public void upload(Long id, MultipartFile multipartFile) throws IOException {
        Students students = metCrud.find(id);
        String pathFile = path + point(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        multipartFile.getInputStream().transferTo(new FileOutputStream(pathFile));
        Avatar avatar = avatarRepository.findByStudentsId(id).orElse(new Avatar());
        avatar.setStudents(students);
        avatar.setFilePath(pathFile);
        avatar.setFileSize(multipartFile.getSize());
        avatar.setMediaType(multipartFile.getContentType());
        avatar.setData(microPic(pathFile));
        if (students != null) {
            avatarRepository.save(avatar);
        } else {
            throw new NullPointerException("Такого ученика нет!");
        }
    }
}





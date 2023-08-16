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
    private static byte[] microPic(String file) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage originalImage = ImageIO.read(new File(file));
        originalImage.setRGB(128, 128, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = originalImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, 128, 128, null);
        graphics2D.dispose();
        ImageIO.write(originalImage, "jpg", baos);
        return baos.toByteArray();
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
        avatar.setData(multipartFile.getBytes());
        if (students != null) {
            avatarRepository.save(avatar);
        } else {
            throw new NullPointerException("Такого ученика нет!");
        }
    }
}





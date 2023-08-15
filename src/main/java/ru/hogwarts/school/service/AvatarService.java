package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Students;
import ru.hogwarts.school.repositories.AvatarRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class AvatarService {
    @Value("${path.my.photo}")
    String path;
    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private MetCrud<Students> metCrud;

    private static String point(String txt) {
        return txt.substring(txt.indexOf('.'));
    }

    public void upload(Long id, MultipartFile multipartFile) throws IOException {
        Students students = metCrud.find(id);
        String pathFile = path + point(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        InputStream inputStream = multipartFile.getInputStream();
        inputStream.transferTo(new FileOutputStream(pathFile));
        Avatar avatar = avatarRepository.findByStudentsId(id).orElse(new Avatar());

        avatar.setStudents(students);
        avatar.setFilePath(path);
        avatar.setFileSize(multipartFile.getSize());
        avatar.setMediaType(multipartFile.getContentType());
        avatar.setData(inputStream.readAllBytes());
        avatarRepository.save(avatar);

    }


}



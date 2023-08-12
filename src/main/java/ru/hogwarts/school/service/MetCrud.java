package ru.hogwarts.school.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Школа волшебства ХОГВАРТС"))
public interface MetCrud<T> {
    T creat(T t);

    T find(long id);

    T edit(T t);

    String delete(long id);

    List<T> getAll();
}

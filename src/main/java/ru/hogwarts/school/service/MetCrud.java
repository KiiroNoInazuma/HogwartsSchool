package ru.hogwarts.school.service;

public interface MetCrud<T> {
    T creat(T t);

    T find(long id);

    T edit(long id, T t);

    String delete(long id);

    StringBuilder getAll();
}

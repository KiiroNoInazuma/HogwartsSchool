package ru.hogwarts.school.model;

public record Students(String name, int age) {


    @Override
    public String toString() {
        return "Имя: " + name + "; Возраст: " + age;
    }
}

--liquibase formatted sql

--changeset John:1
create table faculties
(
    id    bigserial
        primary key,
    name  varchar(255) UNIQUE,
    color varchar(255)

);

--changeset John:2

create table students
(
    id         bigserial
        primary key,
    name       varchar(255),
    age        integer not null,
    faculty_id bigint
        constraint fkjy3uttpwfpb0s83e2pvpatg9j
            references faculties

);

--changeset John:3

create table avatar
(
    id          bigserial
        primary key,
    data        oid,
    file_path   varchar(255),
    file_size   bigint not null,
    media_type  varchar(255),
    students_id bigint
        constraint uk_fc6bnul41uh2s4d3158y3ipvu
            unique
        constraint fketud1dcvs8uy8jeohv9ui4jqb
            references students
);

--changeset John:4

alter table students
     add constraint check_positive check (students.age > 16);






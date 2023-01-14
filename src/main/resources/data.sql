insert into class (ects, cnps, zzu, area, category, course_type, name, type, way_of_crediting)
values ('2', '70', '30', 'area1', 'COURSE', 'LECTURE', 'Bazy danych', 'K', 'EXAM'),
       ('2', '45', '15', 'area1', 'COURSE', 'EXERCISES', 'Bazy danych', 'K', 'EXAM'),
       ('2', '45', '15', 'area1', 'COURSE', 'LAB', 'Bazy danych', 'K', 'EXAM'),
       ('2', '60', '30', 'area1', 'COURSE', 'LECTURE', 'Projektowanie baz danych', 'K', 'EXAM'),
       ('3', '60', '30', 'area1', 'COURSE', 'PROJECT', 'Projektowanie baz danych', 'K', 'EXAM'),
       ('2', '60', '30', 'area1', 'COURSE', 'LECTURE', 'Bazy danych Oracle', 'K', 'EXAM'),
       ('2', '60', '30', 'area1', 'COURSE', 'PROJECT', 'Bazy danych Oracle', 'K', 'EXAM'),
       ('3', '40', '20', 'area2', 'COURSE', 'SEMINARY', 'Techniki prezentacji', 'PD', 'PASS'),
       ('4', '60', '15', 'area2', 'COURSE', 'PROJECT', 'Projektowanie oprogramowania', 'KO', 'PASS');

insert into learning_effect (symbol, description)
values ('ABCD1234', 'Lorem ipsum dolor nr 1'),
       ('EFGH5678', 'Lorem ipsum dolor nr 2'),
       ('IJKL91011', 'Lorem ipsum dolor nr 3'),
       ('MNOP1213', 'Lorem ipsum dolor nr 4'),
       ('QRST1415', 'Lorem ipsum dolor nr 5'),
       ('UWYZ1617', 'Lorem ipsum dolor nr 6');

insert into faculty
values (1, 'Wydział Informatyki i Telekomunikacji'),
       (2, 'Wydział Zarządzania');

insert into plan
values(1, '2022/2023', 'Informatyka stosowana', 'FIRST', 'Plan 1', 1),
      (2, '2022/2023', 'Informatyka stosowana', 'FIRST', 'Plan 2', 1);

insert into semester
values(1, 1, 1),
        (2, 2, 1),
        (3, 3, 1),
        (4, 1, 2);
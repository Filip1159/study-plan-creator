insert into class (id, ects, cnps, zzu, area, category, course_type, name, type, way_of_crediting, group_id, module_id)
values (1, 4, 105, 45, 'area1', 'GROUP', NULL, 'Bazy danych', 'K', 'EXAM', NULL, NULL),
       (2, 2, 70, 30, 'area1', 'COURSE', 'LECTURE', 'Bazy danych', 'K', 'EXAM', 1, NULL),
       (3, 2, 45, 15, 'area1', 'COURSE', 'EXERCISES', 'Bazy danych', 'K', 'EXAM', 1, NULL),
       (4, 2, 45, 15, 'area1', 'COURSE', 'LAB', 'Bazy danych', 'K', 'EXAM', NULL, NULL),
       (5, 5, 120, 60, 'area1', 'GROUP', NULL, 'Projektowanie baz danych', 'K', 'EXAM', NULL, NULL),
       (6, 2, 60, 30, 'area1', 'COURSE', 'LECTURE', 'Projektowanie baz danych', 'K', 'EXAM', 5, NULL),
       (7, 3, 60, 30, 'area1', 'COURSE', 'PROJECT', 'Projektowanie baz danych', 'K', 'EXAM', 5, NULL),
       (8, 4, 120, 60, 'area1', 'GROUP', NULL, 'Bazy danych Oracle', 'K', 'EXAM', NULL, NULL),
       (9, 2, 60, 30, 'area1', 'COURSE', 'LECTURE', 'Bazy danych Oracle', 'K', 'EXAM', 8, NULL),
       (10, 2, 60, 30, 'area1', 'COURSE', 'PROJECT', 'Bazy danych Oracle', 'K', 'EXAM', 8, NULL),
       (11, 3, 40, 20, 'area2', 'COURSE', 'SEMINARY', 'Techniki prezentacji', 'PD', 'PASS', NULL, NULL),
       (12, 4, 60, 15, 'area2', 'COURSE', 'PROJECT', 'Projektowanie oprogramowania', 'KO', 'PASS', NULL, NULL),
       (13, 0, 30, 30, 'area3', 'MODULE', 'EXERCISES', 'WF', 'KO', 'PASS', NULL, NULL),
       (14, 0, 30, 30, 'area3', 'COURSE', 'EXERCISES', 'Zdrowe plecy', 'KO', 'PASS', NULL, 13),
       (15, 0, 30, 30, 'area3', 'COURSE', 'EXERCISES', 'Babington', 'KO', 'PASS', NULL, 13),
       (16, 5, 90, 45, 'area4', 'MODULE', NULL, 'Administrowanie systemami', 'K', 'EXAM', NULL, NULL),
       (17, 5, 90, 45, 'area4', 'GROUP', NULL, 'Administrowanie systemami Linux', 'K', 'EXAM', NULL, 16),
       (18, 2, 30, 15, 'area4', 'COURSE', 'LECTURE', 'Administrowanie systemami Linux', 'K', 'EXAM', 17, NULL),
       (19, 3, 60, 30, 'area4', 'COURSE', 'LAB', 'Administrowanie systemami Linux', 'K', 'EXAM', 17, NULL),
       (20, 5, 90, 45, 'area4', 'GROUP', NULL, 'Administrowanie systemami Windows', 'K', 'EXAM', NULL, 16),
       (21, 2, 30, 15, 'area4', 'COURSE', 'LECTURE', 'Administrowanie systemami Windows', 'K', 'EXAM', 20, NULL),
       (22, 3, 60, 30, 'area4', 'COURSE', 'LAB', 'Administrowanie systemami Windows', 'K', 'EXAM', 20, NULL),
       (23, 5, 90, 45, 'area4', 'GROUP', NULL, 'Routing i przełączanie', 'K', 'EXAM', NULL, 16),
       (24, 2, 30, 15, 'area4', 'COURSE', 'LECTURE', 'Routing i przełączanie', 'K', 'EXAM', 23, NULL),
       (25, 3, 60, 30, 'area4', 'COURSE', 'LAB', 'Routing i przełączanie', 'K', 'EXAM', 23, NULL);


insert into learning_effect (id, symbol, description)
values (1, 'ABCD1234', 'Lorem ipsum dolor nr 1'),
       (2, 'EFGH5678', 'Lorem ipsum dolor nr 2'),
       (3, 'IJKL91011', 'Lorem ipsum dolor nr 3'),
       (4, 'MNOP1213', 'Lorem ipsum dolor nr 4'),
       (5, 'QRST1415', 'Lorem ipsum dolor nr 5'),
       (6, 'UWYZ1617', 'Lorem ipsum dolor nr 6');

insert into learning_effect_realisation (class_id, learning_effect_id)
values (1, 1),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 3),
       (2, 4),
       (3, 1),
       (3, 3),
       (3, 4),
       (4, 1),
       (4, 3),
       (4, 4),
       (13, 6),
       (14, 6),
       (15, 6);

insert into faculty
values (1, 'Wydział Informatyki i Telekomunikacji'),
       (2, 'Wydział Zarządzania');

insert into plan
values (1, '2022/2023', 'Informatyka stosowana', 'FIRST', 'Plan 1', 1),
       (2, '2022/2023', 'Informatyka stosowana', 'FIRST', 'Plan 2', 1);

insert into semester
values (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 1, 2);
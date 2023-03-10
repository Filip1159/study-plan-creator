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
       (16, 5, 90, 45, 'area4', 'GROUP', NULL, 'Administrowanie systemami Linux', 'K', 'EXAM', NULL, NULL),
       (17, 2, 30, 15, 'area4', 'COURSE', 'LECTURE', 'Administrowanie systemami Linux', 'K', 'EXAM', 16, NULL),
       (18, 3, 60, 30, 'area4', 'COURSE', 'LAB', 'Administrowanie systemami Linux', 'K', 'EXAM', 16, NULL),
       (19, 5, 90, 45, 'area4', 'GROUP', NULL, 'Administrowanie systemami Windows', 'K', 'EXAM', NULL, NULL),
       (20, 2, 30, 15, 'area4', 'COURSE', 'LECTURE', 'Administrowanie systemami Windows', 'K', 'EXAM', 19, NULL),
       (21, 3, 60, 30, 'area4', 'COURSE', 'LAB', 'Administrowanie systemami Windows', 'K', 'EXAM', 19, NULL),
       (22, 5, 90, 45, 'area4', 'GROUP', NULL, 'Routing i prze????czanie', 'K', 'EXAM', NULL, NULL),
       (23, 2, 30, 15, 'area4', 'COURSE', 'LECTURE', 'Routing i prze????czanie', 'K', 'EXAM', 22, NULL),
       (24, 3, 60, 30, 'area4', 'COURSE', 'LAB', 'Routing i prze????czanie', 'K', 'EXAM', 22, NULL);


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
       (15, 6),
       (16, 5),
       (17, 5),
       (18, 5),
       (19, 5),
       (20, 5),
       (21, 5),
       (22, 5),
       (23, 5),
       (24, 5);

insert into faculty
values (1, 'Wydzia?? Informatyki i Telekomunikacji'),
       (2, 'Wydzia?? Zarz??dzania'),
       (3, 'Wydzia?? Chemiczny'),
       (4, 'Wydzia?? Architektury'),
       (5, 'Wydzia?? Medyczny'),
       (6, 'Wydzia?? Mechaniczny'),
       (7, 'Wydzia?? Imprez i Zabaw (LEGACY)');

insert into plan
values (1, '2022/2023', 'Informatyka stosowana', 'FIRST', 'Plan 1', 1),
       (2, '2021/2022', 'Cyberbezpiecze????two', 'FIRST', 'Plan 2', 1),
       (3, '2019/2020', 'Weterynaria', 'FIRST', 'Plan 3', 5),
       (4, '2021/2022', 'Informatyka stosowana', 'SECOND', 'Plan 4', 1),
       (5, '2020/2021', 'Zarz??dzanie', 'FIRST', 'Plan 5', 2),
       (6, '2022/2023', 'Mechanika i budowa maszyn', 'SECOND', 'Plan 6', 6),
       (7, '2018/2019', 'Architektura i budownictwo', 'FIRST', 'Plan 7', 4),
       (8, '2022/2023', 'Biotechnologia', 'FIRST', 'Plan 8', 3),
       (9, '2021/2022', 'Farmaceutyka', 'SECOND', 'Plan 9', 3),
       (10, '2022/2023', 'Elektrotechnika', 'FIRST', 'Plan 10', 6);

insert into semester
values (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 1, 2);

insert into users (id, institution_name, job_title, login, name, password, surname, username)
values (1, 'Wydzia?? Informatyki i Telekomunikacji', 'dziekan', 'zygmunt.mazur@pwr.edu.pl', 'Zygmunt',
        'drefc4refef', 'Mazur', 'zyga420'),
       (2, 'Samorz??d studencki', 'pracownik', 'filip.wisniewski@pwr.edu.pl', 'Filip',
        '09ermf09ecmxx', 'Wi??niewski', 'fifi5411'),
       (3, 'Senat PWr', 'senator', 'adam.burak@pwr.edu.pl', 'Adam',
        'c43m09cm3', 'Burak', 'adamoo'),
       (4, 'Komisja ds. jako??ci kszta??cenia PWr', 'pracownik', 'jadwiga.olszewska@pwr.edu.pl', 'Jadwiga',
        '34r0349r4x2er', 'Olszewska-Witkowska', 'jadzka777');

insert into plan_authorship (plan_id, user_id)
values (1, 1),
       (1, 4),
       (2, 2),
       (3, 3),
       (3, 2),
       (4, 1),
       (4, 2),
       (5, 4),
       (6, 3),
       (7, 2),
       (8, 4),
       (9, 1),
       (9, 3),
       (10, 4),
       (10, 2);

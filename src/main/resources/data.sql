DROP TABLE if EXISTS employee_tbl;

CREATE TABLE employee_tbl
(
    empid   int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ename   varchar(50),
    address varchar(20)
);

DROP TABLE if EXISTS user_info_tbl;

CREATE TABLE user_info_tbl
(
    id       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     varchar(50),
    email    varchar(50),
    password varchar(500),
    roles    varchar(50)
);

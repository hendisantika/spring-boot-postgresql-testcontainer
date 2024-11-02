DROP TABLE if EXISTS employee_tbl;

CREATE TABLE employees
(
    empid   int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ename   varchar(50),
    address varchar(20)
);

DROP TABLE if EXISTS user_info;

CREATE TABLE user_info
(
    id       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     varchar(50),
    email    varchar(50),
    password varchar(500),
    roles    varchar(50)
);

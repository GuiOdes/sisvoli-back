CREATE TABLE tb_role (
    id INTEGER PRIMARY KEY NOT NULL,
    role_name VARCHAR(100) NOT NULL
);

INSERT INTO tb_role VALUES (1, 'DEFAULT');
INSERT INTO tb_role VALUES (2, 'ADMIN');
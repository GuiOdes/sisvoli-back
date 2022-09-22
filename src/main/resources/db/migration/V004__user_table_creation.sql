CREATE TABLE tb_user (
    id UUID PRIMARY KEY NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    phone_number VARCHAR(20) UNIQUE,
    birth_date DATE,
    username VARCHAR(50) NOT NULL UNIQUE,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    update_date TIMESTAMP WITH TIME ZONE NOT NULL,
    role_id INTEGER NOT NULL
);

INSERT INTO tb_user VALUES(
    '9137a38d-1a03-4c54-9eec-ca6006ecd67e',
    'Usuário Administrador Padrão',
    'admin@sisvoli.com.br',
    '123456',
    '111.111.111-11',
    null,
    '2000-01-01',
    'SISVOLI_Admin',
    now(),
    now(),
    2
);
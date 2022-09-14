CREATE TABLE tb_address (
    id UUID PRIMARY KEY NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    number VARCHAR(10) NOT NULL,
    street VARCHAR(100) NOT NULL,
    district VARCHAR(100) NOT NULL,
    complement VARCHAR(255) NOT NULL,
    city_id INTEGER NOT NULL,
    user_owner_id INTEGER NOT NULL
);
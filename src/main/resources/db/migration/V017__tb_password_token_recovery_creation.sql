CREATE TABLE tb_password_token_recovery (
    id UUID PRIMARY KEY NOT NULL,
    token VARCHAR(6),
    user_id UUID NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL
);

ALTER TABLE tb_password_token_recovery ADD CONSTRAINT fk_password_token
    FOREIGN KEY (user_id) REFERENCES tb_user(id);
ALTER TABLE tb_user ADD CONSTRAINT fk_user_role
    FOREIGN KEY (role_id) REFERENCES tb_role(id);
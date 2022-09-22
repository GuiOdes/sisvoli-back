ALTER TABLE tb_poll ADD CONSTRAINT fk_poll_user
    FOREIGN KEY (user_owner_id) REFERENCES tb_user(id);
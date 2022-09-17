ALTER TABLE tb_vote ADD CONSTRAINT fk_vote_user
    FOREIGN KEY (user_id) REFERENCES tb_user(id);
ALTER TABLE tb_vote ADD CONSTRAINT fk_vote_option
    FOREIGN KEY (option_id) REFERENCES tb_poll_option(id);
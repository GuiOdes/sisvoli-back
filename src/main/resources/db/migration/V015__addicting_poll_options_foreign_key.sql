ALTER TABLE tb_poll_option ADD CONSTRAINT fk_option_poll
    FOREIGN KEY (poll_id) REFERENCES tb_poll(id);
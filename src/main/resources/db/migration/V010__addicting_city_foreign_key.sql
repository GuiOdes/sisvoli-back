ALTER TABLE tb_city ADD CONSTRAINT fk_city_state
    FOREIGN KEY (state_id) REFERENCES tb_state(id);
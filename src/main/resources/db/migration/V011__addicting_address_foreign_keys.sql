ALTER TABLE tb_address ADD CONSTRAINT fk_address_city
    FOREIGN KEY (city_id) REFERENCES tb_city(id);

ALTER TABLE tb_address ADD CONSTRAINT fk_address_user
    FOREIGN KEY (user_owner_id) REFERENCES tb_user(id);
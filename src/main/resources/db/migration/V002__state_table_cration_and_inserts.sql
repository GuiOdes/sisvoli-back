CREATE TABLE tb_state (
  id integer primary key,
  uf varchar(10) NOT NULL default '',
  state_name varchar(20) NOT NULL default ''
);

INSERT INTO tb_state VALUES (1, 'AC', 'Acre');
INSERT INTO tb_state VALUES (2, 'AL', 'Alagoas');
INSERT INTO tb_state VALUES (3, 'AM', 'Amazonas');
INSERT INTO tb_state VALUES (4, 'AP', 'Amapá');
INSERT INTO tb_state VALUES (5, 'BA', 'Bahia');
INSERT INTO tb_state VALUES (6, 'CE', 'Ceará');
INSERT INTO tb_state VALUES (7, 'DF', 'Distrito Federal');
INSERT INTO tb_state VALUES (8, 'ES', 'Espírito Santo');
INSERT INTO tb_state VALUES (9, 'GO', 'Goiás');
INSERT INTO tb_state VALUES (10, 'MA', 'Maranhão');
INSERT INTO tb_state VALUES (11, 'MG', 'Minas Gerais');
INSERT INTO tb_state VALUES (12, 'MS', 'Mato Grosso do Sul');
INSERT INTO tb_state VALUES (13, 'MT', 'Mato Grosso');
INSERT INTO tb_state VALUES (14, 'PA', 'Pará');
INSERT INTO tb_state VALUES (15, 'PB', 'Paraíba');
INSERT INTO tb_state VALUES (16, 'PE', 'Pernambuco');
INSERT INTO tb_state VALUES (17, 'PI', 'Piauí');
INSERT INTO tb_state VALUES (18, 'PR', 'Paraná');
INSERT INTO tb_state VALUES (19, 'RJ', 'Rio de Janeiro');
INSERT INTO tb_state VALUES (20, 'RN', 'Rio Grande do Norte');
INSERT INTO tb_state VALUES (21, 'RO', 'Rondônia');
INSERT INTO tb_state VALUES (22, 'RR', 'Roraima');
INSERT INTO tb_state VALUES (23, 'RS', 'Rio Grande do Sul');
INSERT INTO tb_state VALUES (24, 'SC', 'Santa Catarina');
INSERT INTO tb_state VALUES (25, 'SE', 'Sergipe');
INSERT INTO tb_state VALUES (26, 'SP', 'São Paulo');
INSERT INTO tb_state VALUES (27, 'TO', 'Tocantins');
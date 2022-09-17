CREATE TABLE tb_poll_option (
    id UUID PRIMARY KEY NOT NULL,
    option_name VARCHAR(255) NOT NULL,
    poll_id UUID NOT NULL
);
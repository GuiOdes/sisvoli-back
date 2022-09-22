CREATE TABLE tb_vote(
    user_id UUID NOT NULL,
    option_id UUID NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL
);
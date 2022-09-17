CREATE TABLE tb_poll (
    id UUID PRIMARY KEY NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    type VARCHAR(100) NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    update_date TIMESTAMP WITH TIME ZONE NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(100) NOT NULL,
    user_owner_id UUID NOT NULL
);
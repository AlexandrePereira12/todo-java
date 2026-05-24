--liquibase formatted sql
--changeset alexandre:1

CREATE table public.user(
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    email varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null
);

alter table public.user add constraint pk_user_id primary key (id);

--rollback DROP TABLE public.user;

--changeset alexandre:2


CREATE table public.list(
                            id BIGINT GENERATED ALWAYS AS IDENTITY,
                            name varchar(255) not null,
                            user_id BIGINT not null
);

Alter table public.list add constraint pk_list_id primary key (id);

alter table public.list add constraint fk_list_user foreign key (user_id) references public."user"(id);

--rollback drop table public.list

--changeset alexandre:3

CREATE table public.item(
                            id BIGINT GENERATED ALWAYS AS IDENTITY,
                            description varchar(255) not null,
                            checked boolean not null default false,
                            list_id BIGINT NOT NULL

);

alter table public.item add constraint pk_item_id primary key (id);
alter table public.item add constraint fk_item_list foreign key (list_id) references public.list(id);
--rollback drop table public.item

-- changeset alexandre:4

ALTER table public.user add constraint uc_user_email UNIQUE (email);


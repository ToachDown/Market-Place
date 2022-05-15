create table person (
    id uuid not null unique,
    email varchar(255) not null unique,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    password varchar(255) not null,
    rol varchar(255) not null,
    status varchar(255) not null,
    primary key (id)
);

ALTER TABLE person add constraint email_uniq UNIQUE (email);
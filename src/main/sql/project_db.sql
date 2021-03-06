create table person(
    id int generated by default as identity primary key,
    full_name varchar(100) not null unique,
    date_of_birth int not null
);

create table books(
    id int generated by default as identity primary key,
    title varchar(100) not null,
    author varchar(100) not null,
    year int not null,
    user_id int references person(id) on delete set null
);
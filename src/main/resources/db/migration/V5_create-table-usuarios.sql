create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    senha varchar(255) not null unique,

    primary key(id)
    );
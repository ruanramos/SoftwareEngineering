-- executar como root
create user 'testuser'@'localhost' identified by '123test'
create database bancoDeDados
default character set utf8
default collate utf8_general_ci;

grant all privileges on bancoDeDados.* to 'testuser'@'localhost';

use bancoDeDados;

create table clientes (
cpf varchar(11) unique not null,
nome varchar(45) not null,
endereco varchar(45) not null,
telefone varchar(45),
nascimento date not null,
validadecnh date not null,
primary key(cpf)
)default charset utf8;

create table carros(
placa varchar(8) not null unique,
quilometragem int,
grupo varchar(1),
ano int,
modelo varchar(20),
primary key(placa)
)default charset utf8;

create table locacoes(
id int not null auto_increment,
idcliente varchar(11) not null,
idcarro varchar(8) not null,
problema varchar(50),
primary key(id),
foreign key(idcliente) references clientes(cpf),
foreign key(idcarro) references carros(placa)
)default charset utf8;

create table reservas(
id int not null auto_increment,
idcliente varchar(11) not null,
data date,
grupo varchar(1),
modelo varchar(20),
duracaodias int,
primary key(id),
foreign key(idcliente) references clientes(cpf)
)default charset utf8;
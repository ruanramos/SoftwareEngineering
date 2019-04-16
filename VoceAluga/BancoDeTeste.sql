-- executar como root
create user 'testuser'@'localhost' identified by '123test'
create database testdb;
grant all privileges on testdb . * to 'testuser'@'localhost';

use testdb;

create table Customer(
    Id int not null auto_increment primary key,
    Cpf varchar(20) unique not null,
    FirstName varchar(255) not null default '',
    LastName varchar(255) not null default '',
    Cnh varchar(20) not null default '',
    Birthday date not null,
    Cellphone varchar(20)
);

create table Carro(
	Id int not null auto_increment primary key,
	Modelo varchar(40) not null default '',
	Classe varchar(2) not null default '',
	Idade int not null default 0,
	Quilometragem float not null default 0
);

INSERT INTO `Customer` VALUES
    (2,'22222222222','João','Mamão','5555555555','3216-03-03','22222222'),
    (3,'11111111111','Joaquim José','da Silva Xavier','0000000000','1950-01-05','11111111'),
    (4,'33333333333','José','das Couves','0101010101','9631-12-31','33333333333'),
    (5,'44444444444','Nana','Shara','4444444444','0003-01-31','5521444444444');

INSERT INTO `Carro` VALUES
    (1,'Uno','A','0', '0'),
    (2, 'Brasilia','A', '123', '456');
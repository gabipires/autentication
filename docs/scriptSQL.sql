CREATE DATABASE TODO;

USE TODO;


CREATE TABLE USERS(
	id int identity,
	username nvarchar(40) not null,
	password nvarchar(100) not null,
	admin bit not null,
	constraint PK_USERS primary key (id)
)


CREATE TABLE TASKS(
	id int identity,
	user_id int not null,
	name nvarchar(40) not null,
	date datetime not null,
	constraint PK_TASKS primary key (id),
	constraint FK_USERID_REFERENCE_USERS foreign key (user_id) references "USERS" (id)
)


INSERT INTO USERS (username, password, admin) VALUES
('RHE', '1234', 0),
('FAV', '4567', 1),
('GPR', '8911', 0)

INSERT INTO TASKS (user_id, name, date) VALUES
(1, 'Estudar Java POO', GETDATE()),
(2, 'Estudar Angular', GETDATE()),
(3, 'Estudar Autenticação', GETDATE()),
(1, 'Criptografia', GETDATE()),
(1, 'Inglês', GETDATE()),
(2, 'Reunião Diretoria', GETDATE())

SELECT T.user_id, U.username, U.admin, T.name, T.date FROM USERS U
INNER JOIN TASKS T
ON U.id = T.user_id

SELECT * FROM USERS

SELECT * FROM TASKS

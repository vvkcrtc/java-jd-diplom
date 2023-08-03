
#insert into netology.users(login, password, role) values('user1', '12345', 'USER');
#insert into netology.users( login, password, role) values('admin', 'password', 'ADMIN');

REPLACE INTO netology.users

SET id = 1,
    login = 'user1',
    password = '12345',
    role = 'USER';

REPLACE INTO netology.users
SET id = 2,
    login = 'admin',
    password = 'admin',
    role = 'ADMIN';


insert into region(id, name) values(1, 'Sudamerica');

insert into region(id, name) values(2, 'Centroamerica');

insert into region(id, name) values(3, 'Norteamerica');

insert into region(id, name) values(4, 'Europa');

insert into region(id, name) values(5, 'Asia');

insert into region(id, name) values(6, 'Africa');

insert into region(id, name) values(7, 'Oceania');

insert into region(id, name) values(8, 'Antartica');

insert into cliente(region_id, client_name, last_name, email, create_at) values(5, 'Marco', 'Hernandez', 'mail@mail.com', now());

insert into cliente(region_id, client_name, last_name, email, create_at) values(6, 'Sofia', 'Hernandez', 'sofia@mail.com', '2012-06-03');

insert into cliente(region_id, client_name, last_name, email, create_at) values(7, 'Amaia', 'Hernandez', 'amaia@mail.com', '2014-08-02');

insert into cliente(region_id, client_name, last_name, email, create_at) values(8, 'Oscar', 'Chavez', 'oscar@mail.com', '1970-06-10');

insert into cliente(region_id, client_name, last_name, email, create_at) values(1, 'Mario', 'Hernandez', 'mario@mail.com', now());

insert into cliente(region_id, client_name, last_name, email, create_at) values(2, 'Paty', 'Hernandez', 'paty@mail.com', '2012-06-03');

insert into cliente(region_id, client_name, last_name, email, create_at) values(3, 'Kara', 'Hernandez', 'kara@mail.com', '2014-08-02');

insert into cliente(region_id, client_name, last_name, email, create_at) values(4, 'Ben', 'Chavez', 'benr@mail.com', '1970-06-10');

insert into cliente(region_id, client_name, last_name, email, create_at) values(5, 'Marco', 'Hernandez', 'mail@mail.com', now());

insert into cliente(region_id, client_name, last_name, email, create_at) values(6, 'Sofia', 'Hernandez', 'sofia@mail.com', '2012-06-03');

insert into cliente(region_id, client_name, last_name, email, create_at) values(7, 'Amaia', 'Hernandez', 'amaia@mail.com', '2014-08-02');

insert into cliente(region_id, client_name, last_name, email, create_at) values(8, 'Oscar', 'Chavez', 'oscar@mail.com', '1970-06-10');

insert into cliente(region_id, client_name, last_name, email, create_at) values(1, 'Mario', 'Hernandez', 'mario@mail.com', now());

insert into cliente(region_id, client_name, last_name, email, create_at) values(2, 'Paty', 'Hernandez', 'paty@mail.com', '2012-06-03');

insert into cliente(region_id, client_name, last_name, email, create_at) values(3, 'Kara', 'Hernandez', 'kara@mail.com', '2014-08-02');

insert into cliente(region_id, client_name, last_name, email, create_at) values(4, 'Ben', 'Chavez', 'benr@mail.com', '1970-06-10');

insert into cliente(region_id, client_name, last_name, email, create_at) values(5, 'Marco', 'Hernandez', 'mail@mail.com', now());

insert into cliente(region_id, client_name, last_name, email, create_at) values(6, 'Sofia', 'Hernandez', 'sofia@mail.com', '2012-06-03');

insert into cliente(region_id, client_name, last_name, email, create_at) values(7, 'Amaia', 'Hernandez', 'amaia@mail.com', '2014-08-02');

insert into cliente(region_id, client_name, last_name, email, create_at) values(8, 'Oscar', 'Chavez', 'oscar@mail.com', '1970-06-10');

insert into role (id_role, role_name) values (1, 'ROLE_USER');
insert into role (id_role, role_name) values (2, 'ROLE_ADMIN');
insert into usuario (id_usuario, user_name, password, enable) values (1, 'Marco', '$2a$10$VCAkmMpdG1o.y5e.UnqLeuO5.F3rHy2up4Gg3at9KumKwoav/oGp2', 1);
insert into usuario (id_usuario, user_name, password, enable) values (2, 'Kara', '$2a$10$7RjZDv5yIJhW4Lk.OYjZ9OoHjcIYz8shRlNs8YT7i5oaajxTYhhPK', 1);
insert into users_authorities (user_id, role_id) values(1,1);
insert into users_authorities (user_id, role_id) values(2,2);
insert into users_authorities (user_id, role_id) values(2,1);

insert into producto values(1, 'Pantalla Samsumg', 500.00);
insert into producto values(2, 'Lavadora secadora Samsumg', 1500.00);
insert into producto values(3, 'Camara Samsumg', 100.00);
insert into producto values(4, 'Tablet Samsumg', 150.00);
insert into producto values(5, 'Telefono Samsumg', 200.00);
insert into producto values(6, 'Sound bar Samsumg', 300.00);
insert into producto values(7, 'Sourroud system', 400.00);

insert into factura (id_factura, create_at, description, observation, cliente_id) values(1000, now(), 'Venta de pantalla', 'Garantia extendida', 1)
insert into item_factura(cantidad, producto_id, factura_id) values(2, 1, 1000);

insert into factura (id_factura, create_at, description, observation, cliente_id) values(1001, now(), 'Venta de Lavadora Secadora', 'Garantia extendida', 2)
insert into item_factura(cantidad, producto_id, factura_id) values(1, 2, 1001);

insert into factura (id_factura, create_at, description, observation, cliente_id) values(1003, now(), 'Venta de articulos varios', 'Garantia extendida', 3)
insert into item_factura(cantidad, producto_id, factura_id) values(3, 3, 1003);
insert into item_factura(cantidad, producto_id, factura_id) values(4, 4, 1003);
insert into item_factura(cantidad, producto_id, factura_id) values(2, 2, 1003);




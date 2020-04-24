insert into clientes (nombre,apellido,mail,created,foto) values ('juan','pablo','jpp@htm.com',NOW(),'');
insert into clientes (nombre,apellido,mail,created,foto) values ('maria','duarte','mapa8838@htm.com',NOW(),'');

insert into producto (nombre,precio,fecha_creacion) values ('iphone',3000000,NOW());
insert into producto (nombre,precio,fecha_creacion) values ('Galaxy',2700000,NOW());

insert into facturas (descripcion,observacion,fecha_creacion,cliente_id) values ('Importante','celular',NOW(),1);
insert into item_factura (cantidad,factura_id,producto_id) values (3,1,1);
insert into item_factura (cantidad,factura_id,producto_id) values (3,1,2);
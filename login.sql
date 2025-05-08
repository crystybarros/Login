select * from usuario;
 

insert into usuario (usuario, nome, senha)
values('marcos','Marcos Costa de Sousa', '12345678');


insert into usuario (usuario, nome, senha)
values('antonio','Antonio Costa de Sousa', md5('12345678'));
 

select * from usuario
where usuario = 'antonio'
  and senha = md5('12345678');
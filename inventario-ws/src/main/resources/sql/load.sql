INSERT INTO USUARIO VALUES(1,'Maria', 'admin','admin123')
INSERT INTO USUARIO VALUES(2,'Fernando', 'user','user123')
INSERT INTO PERFIL VALUES(1, 'Administrador','Perfil para administrar el inventario' )
INSERT INTO PERFIL VALUES(2, 'Usuario','Perfil para usuario' )
INSERT INTO MENU VALUES(1, 'Administrar Inventario','/adm/gestionar/inventario' )
INSERT INTO MENU VALUES(2, 'Reporte','/adm/reporte' )
INSERT INTO MENU VALUES(3, 'Productos','/usr/productos' )
INSERT INTO PERFILXUSUARIO VALUES(1, 1, 1, true)
INSERT INTO PERFILXUSUARIO VALUES(2, 2, 2, true)
INSERT INTO PERFILXMENU VALUES(1,1,1, true )
INSERT INTO PERFILXMENU VALUES(2,2,1, true )
INSERT INTO PERFILXMENU VALUES(3,3,2, true )
INSERT INTO ESTABLECIMIENTO VALUES(1,'Hulk Store','1111111111001' )
INSERT INTO SUCURSAL VALUES(1,'HS001','Hulk Store - MATRIZ','001','Av. La Paz y Gran Colombia', 1 )
INSERT INTO BODEGA VALUES(1,'B001','Bodega Principal','Carcelén Alto',1  )

INSERT INTO CATALOGO VALUES(1,'PRENVES','CAM','Camisetas', true )
INSERT INTO CATALOGO VALUES(2,'MANTEYCOC','VASOS','Vasos', true )
INSERT INTO CATALOGO VALUES(3,'COMIC','DC','Comics DC', true )
INSERT INTO CATALOGO VALUES(4,'COMIC','MARVEL','Comics MARVEL', true )
INSERT INTO CATALOGO VALUES(5,'COMIC','OTROS','Comics otros autores', true )
INSERT INTO CATALOGO VALUES(6,'JUG','DC','Figuras de acción - DC', true )
INSERT INTO CATALOGO VALUES(7,'JUG','MARVEL','Figuras de acción - MARVEL', true )
INSERT INTO CATALOGO VALUES(8,'JUG','OTROS','Juguetes otros', true )
INSERT INTO CATALOGO VALUES(9,'ACS','DC','Accesorios universo DC', true )
INSERT INTO CATALOGO VALUES(10,'ACS','MARVEL','Accesorios universo MARVEL', true )
INSERT INTO CATALOGO VALUES(11,'ACS','OTROS','Accesorios otros autores', true )
INSERT INTO PRODUCTO VALUES(1,'COD001','S/N','Camiseta de super heroe', 'Descripción d ela camiseta', 'S/N',10,12.50,1,1, true)
INSERT INTO PRODUCTO VALUES(2,'COD002','S/N','Camiseta de algodón', 'Descripción de la camiseta de algodon', 'S/N',25,15,1,1,true)
INSERT INTO PRODUCTO VALUES(3,'COD003','S/N','COMIC DC', 'COMIC de SPIDERMAN', 'S/N',5,45,3,1,true)
INSERT INTO PRODUCTO VALUES(4,'COD004','S/N','COMIC MARVEL', 'COMIC X-MEN', 'S/N',10,35,4,1,true)
INSERT INTO PRODUCTO VALUES(5,'COD005','S/N','FIGURA DE ACCIÓN UNIVERSO DC', 'Hecho en pĺastico duro de 35 mm x 10 mm', 'S/N',5,25,6,1,true)
INSERT INTO PRODUCTO VALUES(6,'COD006','S/N','FIGURA DE ACCIÓN UNIVERSO MARVEL', 'Hecho en cuero', 'S/N',25,15,7,1,true)
INSERT INTO ESTABLECIMIENTOXUSUARIO VALUES(1, 1, 1, true)
INSERT INTO ESTABLECIMIENTOXUSUARIO VALUES(2, 1, 2, true)

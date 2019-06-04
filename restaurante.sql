--------------------------------------------------------
-- Archivo creado  - lunes-junio-03-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CLIENTE
--------------------------------------------------------

  CREATE TABLE "CLIENTE" 
   (	"IDCLIENTE" NUMBER(13,0), 
	"NOMBRE" VARCHAR2(200 BYTE), 
	"APELLIDO1" VARCHAR2(200 BYTE), 
	"APELLIDO2" VARCHAR2(200 BYTE), 
	"OBSERVACIONES" VARCHAR2(4000 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table CAMARERO
--------------------------------------------------------

  CREATE TABLE "CAMARERO" 
   (	"IDCAMARERO" NUMBER(13,0), 
	"NOMBRE" VARCHAR2(200 BYTE), 
	"APELLIDO1" VARCHAR2(200 BYTE), 
	"APELLIDO2" VARCHAR2(200 BYTE)
   );
--------------------------------------------------------
--  DDL for Table FACTURA
--------------------------------------------------------

  CREATE TABLE "FACTURA" 
   (	"IDFACTURA" NUMBER(13,0), 
	"IDCLIENTE" NUMBER(13,0), 
	"IDCAMARERO" NUMBER(13,0), 
	"IDMESA" NUMBER(13,0), 
	"FECHAFACTURA" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table DETALLEFACTURA
--------------------------------------------------------

  CREATE TABLE "DETALLEFACTURA" 
   (	"IDDETALLEFCTURA" NUMBER(13,0), 
	"IDFACTURA" NUMBER(13,0), 
	"IDCOCINERO" NUMBER(13,0), 
	"PLATO" VARCHAR2(200 BYTE), 
	"IMPORTE" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table MESA
--------------------------------------------------------

  CREATE TABLE "MESA" 
   (	"IDMESA" NUMBER(13,0), 
	"NUMMAXCOMENSALES" NUMBER, 
	"UBICACION" VARCHAR2(200 BYTE)
   );
--------------------------------------------------------
--  DDL for Table COCINERO
--------------------------------------------------------

  CREATE TABLE "COCINERO" 
   (	"IDCOCINERO" NUMBER(13,0), 
	"NOMBRE" VARCHAR2(200 BYTE), 
	"APELLIDO1" VARCHAR2(200 BYTE), 
	"COLUMN2" VARCHAR2(200 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for View CAMARERO_FACTURA
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "CAMARERO_FACTURA" ("ID", "CONTADOR", "IDCAMARERO", "NOMBRE", "APELLIDO", "TOTAL", "MES") AS 
  Select RowNum Id, Contador, IdCamarero, Nombre, Apellido, Total,   Decode(Mes,'01','ENERO','02','FEBRERO','03','MARZO',
                                                 '04','ABRIL','05','MAYO','06','JUNIO',
                                                 '07','JULIO','08','AGOSTO','09','SEPTIEMBRE',
                                                 '10','OCTUBRE','11','NOVIEMBRE','12','DICIEMBRE') Mes
From (Select Count (*) contador,
f.idcamarero, 
C.Nombre,
C.Apellido1 Apellido, Sum(D.Importe)Total,   
             To_Char(f.fechafactura,'MM') mes
    From factura f, camarero C, detalleFactura D
   Where to_Char(f.fechafactura,'YYYY')=To_Char(Sysdate,'YYYY')
   And C.idCamarero = F.idCamarero
   And d.idfactura = F.IdFactura
   Group By To_Char(f.fechafactura,'MM'), f.idcamarero, 
C.Nombre,
C.Apellido1
Order By To_Char(f.fechafactura,'MM') Asc)
;
--------------------------------------------------------
--  DDL for View SUMACLIENTE
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "SUMACLIENTE" ("IDCLIENTE", "NOMBRE", "APELLIDO1", "SUMA") AS 
  Select C.idCliente, C.Nombre, C.APELLIDO1, Sum(Importe)Suma
From Detallefactura D, Factura F, Cliente C
Where D.IdFactura = F.IdFactura
And F.IdCliente = C.IdCliente
Having Sum(Importe) >= 100000
Group By c.IdCliente,C.Nombre, C.APELLIDO1
;
REM INSERTING into CLIENTE
SET DEFINE OFF;
Insert into CLIENTE (IDCLIENTE,NOMBRE,APELLIDO1,APELLIDO2,OBSERVACIONES) values ('1','Ruben','Maury','Thorrens','Cliente frecuente');
REM INSERTING into CAMARERO
SET DEFINE OFF;
Insert into CAMARERO (IDCAMARERO,NOMBRE,APELLIDO1,APELLIDO2) values ('1','Juan','Gonzales',null);
REM INSERTING into CAMARERO_FACTURA
SET DEFINE OFF;
Insert into CAMARERO_FACTURA (ID,CONTADOR,IDCAMARERO,NOMBRE,APELLIDO,TOTAL,MES) values ('1','2','1','Juan','Gonzales','510000','ENERO');
Insert into CAMARERO_FACTURA (ID,CONTADOR,IDCAMARERO,NOMBRE,APELLIDO,TOTAL,MES) values ('2','1','1','Juan','Gonzales','500000','MARZO');
REM INSERTING into SUMACLIENTE
SET DEFINE OFF;
Insert into SUMACLIENTE (IDCLIENTE,NOMBRE,APELLIDO1,SUMA) values ('1','Ruben','Maury','1010000');
REM INSERTING into FACTURA
SET DEFINE OFF;
Insert into FACTURA (IDFACTURA,IDCLIENTE,IDCAMARERO,IDMESA,FECHAFACTURA) values ('1','1','1','1',to_date('05/03/19','DD/MM/RR'));
Insert into FACTURA (IDFACTURA,IDCLIENTE,IDCAMARERO,IDMESA,FECHAFACTURA) values ('2','1','1','1',to_date('06/01/19','DD/MM/RR'));
REM INSERTING into DETALLEFACTURA
SET DEFINE OFF;
Insert into DETALLEFACTURA (IDDETALLEFCTURA,IDFACTURA,IDCOCINERO,PLATO,IMPORTE) values ('1','1','1','Comida principal','500000');
Insert into DETALLEFACTURA (IDDETALLEFCTURA,IDFACTURA,IDCOCINERO,PLATO,IMPORTE) values ('2','2','1','prueba de comida','10000');
Insert into DETALLEFACTURA (IDDETALLEFCTURA,IDFACTURA,IDCOCINERO,PLATO,IMPORTE) values ('3','2','1','comida principal','500000');
REM INSERTING into MESA
SET DEFINE OFF;
Insert into MESA (IDMESA,NUMMAXCOMENSALES,UBICACION) values ('1','4','Al lado de la puerta');
REM INSERTING into COCINERO
SET DEFINE OFF;
Insert into COCINERO (IDCOCINERO,NOMBRE,APELLIDO1,COLUMN2) values ('1','Maria','Jimenez',null);
--------------------------------------------------------
--  DDL for Index CLIENTE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CLIENTE_PK" ON "CLIENTE" ("IDCLIENTE") ;
--------------------------------------------------------
--  DDL for Index CAMARERO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CAMARERO_PK" ON "CAMARERO" ("IDCAMARERO") ;
--------------------------------------------------------
--  DDL for Index FACTURA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FACTURA_PK" ON "FACTURA" ("IDFACTURA") ;
--------------------------------------------------------
--  DDL for Index DETALLEFACTURA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DETALLEFACTURA_PK" ON "DETALLEFACTURA" ("IDDETALLEFCTURA", "IDFACTURA")  ;
--------------------------------------------------------
--  DDL for Index MESA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESA_PK" ON "MESA" ("IDMESA") ;
--------------------------------------------------------
--  DDL for Index COCINERO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "COCINERO_PK" ON "COCINERO" ("IDCOCINERO")  ;
--------------------------------------------------------

--------------------------------------------------------
--  Constraints for Table CLIENTE
--------------------------------------------------------

  ALTER TABLE "CLIENTE" ADD CONSTRAINT "CLIENTE_PK" PRIMARY KEY ("IDCLIENTE")
  ALTER TABLE "CLIENTE" MODIFY ("IDCLIENTE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CAMARERO
--------------------------------------------------------

  ALTER TABLE "CAMARERO" ADD CONSTRAINT "CAMARERO_PK" PRIMARY KEY ("IDCAMARERO");
  ALTER TABLE "CAMARERO" MODIFY ("IDCAMARERO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FACTURA
--------------------------------------------------------

  ALTER TABLE "FACTURA" ADD CONSTRAINT "FACTURA_PK" PRIMARY KEY ("IDFACTURA");
  ALTER TABLE "FACTURA" MODIFY ("IDFACTURA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DETALLEFACTURA
--------------------------------------------------------

  ALTER TABLE "DETALLEFACTURA" ADD CONSTRAINT "DETALLEFACTURA_PK" PRIMARY KEY ("IDDETALLEFCTURA", "IDFACTURA");
  ALTER TABLE "DETALLEFACTURA" MODIFY ("IDFACTURA" NOT NULL ENABLE);
  ALTER TABLE "DETALLEFACTURA" MODIFY ("IDDETALLEFCTURA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MESA
--------------------------------------------------------

  ALTER TABLE "MESA" ADD CONSTRAINT "MESA_PK" PRIMARY KEY ("IDMESA");
  ALTER TABLE "MESA" MODIFY ("IDMESA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table COCINERO
--------------------------------------------------------

  ALTER TABLE "COCINERO" ADD CONSTRAINT "COCINERO_PK" PRIMARY KEY ("IDCOCINERO");
  ALTER TABLE "COCINERO" MODIFY ("IDCOCINERO" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table FACTURA
--------------------------------------------------------

  ALTER TABLE "FACTURA" ADD CONSTRAINT "FACTURA_FK1" FOREIGN KEY ("IDCLIENTE")
	  REFERENCES "CLIENTE" ("IDCLIENTE") ENABLE;
  ALTER TABLE "FACTURA" ADD CONSTRAINT "FACTURA_FK2" FOREIGN KEY ("IDCAMARERO")
	  REFERENCES "CAMARERO" ("IDCAMARERO") ENABLE;
  ALTER TABLE "FACTURA" ADD CONSTRAINT "FACTURA_FK3" FOREIGN KEY ("IDMESA")
	  REFERENCES "MESA" ("IDMESA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DETALLEFACTURA
--------------------------------------------------------

  ALTER TABLE "DETALLEFACTURA" ADD CONSTRAINT "DETALLEFACTURA_FK1" FOREIGN KEY ("IDFACTURA")
	  REFERENCES "FACTURA" ("IDFACTURA") ENABLE;
  ALTER TABLE "DETALLEFACTURA" ADD CONSTRAINT "DETALLEFACTURA_FK2" FOREIGN KEY ("IDCOCINERO")
	  REFERENCES "COCINERO" ("IDCOCINERO") ENABLE;

USE AMMobile;

IF EXISTS (SELECT * FROM  dbo.SYSOBJECTS WHERE id = object_id('RangosHora') AND OBJECTPROPERTY(id, 'IsUserTable')=1)
DROP TABLE RangosHora;

CREATE TABLE RangosHora ( 
	RangosHoraId int NOT NULL,
	Inferior int NOT NULL,
	Superior int NOT NULL,
)
;

DELETE RangosHora;
INSERT INTO RangosHora VALUES ('1', '16', '10000');
INSERT INTO RangosHora VALUES ('2', '6', '15');
INSERT INTO RangosHora VALUES ('3', '0', '5');

ALTER TABLE RangosHora ADD CONSTRAINT PK_RangosHora PRIMARY KEY (RangosHoraId);

IF EXISTS (SELECT * FROM  dbo.SYSOBJECTS WHERE id = object_id('RangosDistancia') AND OBJECTPROPERTY(id, 'IsUserTable')=1)
DROP TABLE RangosDistancia;

CREATE TABLE RangosDistancia ( 
	RangosDistanciaId int NOT NULL,
	Inferior int NOT NULL,
	Superior int NOT NULL,
)
;
INSERT INTO RangosDistancia VALUES ('1', '110', '2000000');
INSERT INTO RangosDistancia VALUES ('2', '50', '109');
INSERT INTO RangosDistancia VALUES ('3', '0', '49');

ALTER TABLE RangosDistancia ADD CONSTRAINT PK_RangosDistancia PRIMARY KEY (RangosDistanciaId);


if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='ReporteOE'
and COLUMN_NAME='RangoDistancia')
ALTER TABLE ReporteOE  ADD RangoDistancia int  NULL;
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='ReporteOE'
and COLUMN_NAME='RangoHora')
ALTER TABLE ReporteOE  ADD RangoHora int  NULL;
GO


if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='ReporteOE'
and COLUMN_NAME='Latitud')
ALTER TABLE ReporteOE  ADD Latitud nvarchar(50)  NULL;
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='ReporteOE'
and COLUMN_NAME='Longitud')
ALTER TABLE ReporteOE  ADD Longitud nvarchar(50)  NULL;
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='Latitud')
ALTER TABLE Ruta  ADD Latitud nvarchar(50)  NULL;
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='Longitud')
ALTER TABLE Ruta  ADD Longitud nvarchar(50)  NULL;
GO


select * from ReporteOE
select * from Ruta

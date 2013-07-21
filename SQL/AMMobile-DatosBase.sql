USE AMMobile;

INSERT INTO TipoRol VALUES ('Admin', 'Administrador del Sistema');
INSERT INTO TipoRol VALUES ('Auxiliar', 'Auxiliar de Operaciones');


INSERT INTO EstadoRuta VALUES ('1', 'Pendiente');
INSERT INTO EstadoRuta VALUES ('2', 'Realizada');

INSERT INTO EstadoVisita VALUES ('1', 'Efectiva');
INSERT INTO EstadoVisita VALUES ('2', 'No Efectiva');

INSERT INTO [Usuario]
           ([Cedula]
           ,[Nombre]
           ,[RolNm]
           ,[Telefono]
           ,[Celular]
           ,[Email]
           ,[PIN]
           ,[NombreUsuario]
           ,[Clave]
           ,[Estado]
           ,[FechaCreacion])
     VALUES
           (1012370886
           ,'KATHERINE FERREIRA'
           ,'Auxiliar'
           ,'600000'
           ,'123'
           ,'315600000'
           ,'12'
           ,'KATHERINE'
           ,'1012370886'
           ,'1'
           ,getDate())
GO

INSERT INTO [Usuario]
           ([Cedula]
           ,[Nombre]
           ,[RolNm]
           ,[Telefono]
           ,[Celular]
           ,[Email]
           ,[PIN]
           ,[NombreUsuario]
           ,[Clave]
           ,[Estado]
           ,[FechaCreacion])
     VALUES
           (123456789
           ,'KATHERINE FERREIRA'
           ,'Admin'
           ,'600000'
           ,'123'
           ,'315600000'
           ,'1'
           ,'Admin'
           ,'1234'
           ,'1'
           ,getDate())
GO

select * from usuario

INSERT INTO TipoRol VALUES ('Controlador', 'Controlador');


INSERT INTO [Usuario]
           ([Cedula]
           ,[Nombre]
           ,[RolNm]
           ,[Telefono]
           ,[Celular]
           ,[Email]
           ,[PIN]
           ,[NombreUsuario]
           ,[Clave]
           ,[Estado]
           ,[FechaCreacion])
     VALUES
           (323456789
           ,'Controlador'
           ,'Controlador'
           ,'600000'
           ,'123'
           ,'315600000'
           ,'1'
           ,'Controlador'
           ,'1234'
           ,'1'
           ,getDate())
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='INO')
ALTER TABLE Ruta  ADD INO nvarchar(500)  NULL;
GO


if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='EG')
ALTER TABLE Ruta  ADD EG nvarchar(500)  NULL;
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='Datos')
ALTER TABLE Ruta  ADD Datos nvarchar(500)  NULL;
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='Causal')
ALTER TABLE Ruta  ADD Causal nvarchar(500)  NULL;
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='FechaRegistroControlador')
ALTER TABLE Ruta  ADD FechaRegistroControlador datetime  NULL;
GO

if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='NombreControlador')
ALTER TABLE Ruta   ADD NombreControlador nvarchar(500)  NULL;
GO


if not  exists ( select * from INFORMATION_SCHEMA.COLUMNS
where TABLE_NAME='Ruta'
and COLUMN_NAME='ModificadaPorControlador')
ALTER TABLE Ruta   ADD ModificadaPorControlador bit NULL;
GO
select * from ruta

update ruta set FechaRegistroControlador =getDate();
update ruta set ModificadaPorControlador =0;

sp_columns ruta
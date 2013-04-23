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
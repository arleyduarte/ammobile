---- AM Mobile

----------------------------------

IF EXISTS (SELECT * FROM  dbo.SYSOBJECTS WHERE id = object_id('ReporteOE') AND OBJECTPROPERTY(id, 'IsUserTable')=1)
DROP TABLE ReporteOE;

IF EXISTS (SELECT * FROM  dbo.SYSOBJECTS WHERE id = object_id('Ruta') AND OBJECTPROPERTY(id, 'IsUserTable')=1)
DROP TABLE Ruta;

IF EXISTS (SELECT * FROM  dbo.SYSOBJECTS WHERE id = object_id('Usuario') AND OBJECTPROPERTY(id, 'IsUserTable')=1)
DROP TABLE Usuario;

CREATE TABLE Usuario (
	UsuarioID	int identity NOT NULL,
	Cedula	nvarchar(10) NOT NULL,
	Nombre	nvarchar(40) NOT NULL,
	RolNm	nvarchar(12)  NULL,
	Telefono nvarchar(10)  NULL,
	Celular nvarchar(10)  NULL,
	Email nvarchar(50)  NULL,
	PIN nvarchar(50)  NULL,
	NombreUsuario nvarchar(50)  NULL,
	Clave  nvarchar(50)  NULL,
	Estado bit,
	FechaCreacion datetime
	
);

ALTER TABLE Usuario ADD CONSTRAINT PK_Usuario PRIMARY KEY (UsuarioID);
ALTER TABLE Usuario ADD CONSTRAINT UQ_Usuario UNIQUE(NombreUsuario);
ALTER TABLE Usuario ADD CONSTRAINT UQ_CedulaUsuario UNIQUE(Cedula);
ALTER TABLE Usuario ADD CONSTRAINT UQ_PIN UNIQUE(PIN);


IF EXISTS (SELECT * FROM  dbo.SYSOBJECTS WHERE id = object_id('TipoRol') AND OBJECTPROPERTY(id, 'IsUserTable')=1)
DROP TABLE TipoRol;

CREATE TABLE TipoRol ( 
	RolNm nvarchar(12) NOT NULL,
	Descripcion nvarchar(50) NOT NULL,
)
;
ALTER TABLE TipoRol ADD CONSTRAINT PK_TipoRol PRIMARY KEY (RolNm);




CREATE TABLE Ruta (
	RutaID	int identity NOT NULL,
	RutaNo	int  NULL,
	FechaRuta datetime NOT NULL,
	
	Direccion	nvarchar(500)  NULL,
	Barrio	nvarchar(500)  NULL,
	Departamento nvarchar(500)  NULL,
	Ciudad nvarchar(500)  NULL,
	Referente nvarchar(500)  NULL,
	Sector nvarchar(500)  NULL,
	RutaConsecutivo int  NULL,
	Guia nvarchar(100)  NULL,
	E nvarchar(100)  NULL,
	NotaOperativa nvarchar(500)  NULL,
	Hora nvarchar(100)  NULL,
	UsuarioID int,
	NombreUsuario nvarchar(50) NOT NULL,
		
	EstadoRutaID int,
	FechaCreacion datetime
	

);

ALTER TABLE Ruta ADD CONSTRAINT PK_Ruta PRIMARY KEY (RutaID);



CREATE TABLE ReporteOE (
	ReporteOEID	int identity NOT NULL,
	RutaID	int  NOT NULL,
	FechaRegistro datetime NOT NULL,
	
	INO	nvarchar(500)  NULL,
	EG	nvarchar(500)  NULL,
	Datos	nvarchar(500)  NULL,
	Causal	nvarchar(500)  NULL,
	GestionOE	nvarchar(500)  NULL,
		
	UsuarioID int,
	NombreUsuario nvarchar(50) NOT NULL,
	EstadoVisitaID int,
	
);

ALTER TABLE ReporteOE ADD CONSTRAINT PK_ReporteOE PRIMARY KEY (ReporteOEID);
ALTER TABLE  ReporteOE ADD CONSTRAINT FK_ReporteOERutaID FOREIGN KEY (RutaID) REFERENCES Ruta (RutaID);



IF EXISTS (SELECT * FROM  dbo.SYSOBJECTS WHERE id = object_id('EstadoRuta') AND OBJECTPROPERTY(id, 'IsUserTable')=1)
DROP TABLE EstadoRuta;

CREATE TABLE EstadoRuta ( 
	EstadoRutaID int NOT NULL,
	EstadoNm nvarchar(50) NOT NULL,
)
;
ALTER TABLE EstadoRuta ADD CONSTRAINT PK_EstadoRuta PRIMARY KEY (EstadoRutaID);
ALTER TABLE  Ruta ADD CONSTRAINT FK_EstadoRutaID FOREIGN KEY (EstadoRutaID) REFERENCES EstadoRuta (EstadoRutaID);


IF EXISTS (SELECT * FROM  dbo.SYSOBJECTS WHERE id = object_id('EstadoVisita') AND OBJECTPROPERTY(id, 'IsUserTable')=1)
DROP TABLE EstadoVisita;

CREATE TABLE EstadoVisita ( 
	EstadoVisitaID int NOT NULL,
	EstadoNm nvarchar(50) NOT NULL,
)
;
ALTER TABLE EstadoVisita ADD CONSTRAINT PK_EstadoVisita PRIMARY KEY (EstadoVisitaID);
ALTER TABLE ReporteOE ADD CONSTRAINT FK_EstadoVisitaID FOREIGN KEY (EstadoVisitaID) REFERENCES EstadoVisita (EstadoVisitaID);

select * from Usuario
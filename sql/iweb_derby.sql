CREATE TABLE USUARIO(
   ID_USUARIO INT PRIMARY KEY NOT NULL 
      GENERATED ALWAYS AS IDENTITY
      (START WITH 1, INCREMENT BY 1),
   EMAIL CHAR(45) NOT NULL,
   NOMBRE CHAR(45),    
   ROL CHAR(45) NOT NULL);

CREATE TABLE TABLON(
   ID_TABLON INT PRIMARY KEY NOT NULL 
      GENERATED ALWAYS AS IDENTITY
      (START WITH 1, INCREMENT BY 1),
   FECHA_CREACION DATE,
   INFORMACION CHAR(45));

CREATE TABLE COMENTARIO(
   ID_COMENTARIO INT PRIMARY KEY NOT NULL 
      GENERATED ALWAYS AS IDENTITY
      (START WITH 1, INCREMENT BY 1),
   CONTENIDO CHAR(45) NOT NULL,
   ID_TABLON INT NOT NULL REFERENCES TABLON(ID_TABLON));


CREATE TABLE COMENTARIO_USUARIO(
   ID_COMENTARIO INT NOT NULL REFERENCES COMENTARIO(ID_COMENTARIO),
   ID_USUARIO INT NOT NULL REFERENCES USUARIO(ID_USUARIO),
   PRIMARY KEY(ID_COMENTARIO, ID_USUARIO));
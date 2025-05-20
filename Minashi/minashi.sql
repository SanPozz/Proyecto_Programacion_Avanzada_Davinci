
CREATE TABLE rol (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    rol VARCHAR(50) NOT NULL
);


INSERT INTO rol (rol) VALUES 
('administrador'),
('venta'),
('deposito'),
('operario'),
('cliente');

CREATE TABLE usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    edad INT,
    correo VARCHAR(100)
);


INSERT INTO usuario (nombre, apellido, edad, correo) VALUES
('Juan', 'Pérez', 30, 'juan.perez@example.com'),
('María', 'González', 25, 'maria.gonzalez@example.com'),
('Luis', 'Martínez', 40, 'luis.martinez@example.com'),
('Ana', 'López', 35, 'ana.lopez@example.com'),
('Carlos', 'Ramírez', 28, 'carlos.ramirez@example.com');

CREATE TABLE usuario_rol (
    idUsuario INT,
    idRol INT,
    PRIMARY KEY (idUsuario, idRol),
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario),
    FOREIGN KEY (idRol) REFERENCES rol(idRol)
);


INSERT INTO usuario_rol (idUsuario, idRol) VALUES
(1, 1),
(2, 2), 
(3, 3), 
(4, 4), 
(5, 5); 

CREATE TABLE mineral (
    idMineral INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    pureza FLOAT
    );

    INSERT INTO mineral (tipo, pureza) VALUES
('Oro', 98.5),
('Plata', 92.3),
('Cobre', 85.0),
('Hierro', 65.4),
('Níquel', 88.1);

CREATE TABLE mina (
    idMina INT AUTO_INCREMENT PRIMARY KEY,
    ubicacion VARCHAR(100) NOT NULL
);

INSERT INTO mina (ubicacion) VALUES
('Salta, Argentina'),
('Jujuy, Argentina'),
('San Juan, Argentina'),
('Catamarca, Argentina'),
('La Rioja, Argentina');

CREATE TABLE mina_mineral (
    idMina INT,
    idMineral INT,
    PRIMARY KEY (idMina, idMineral),
    FOREIGN KEY (idMina) REFERENCES mina(idMina),
    FOREIGN KEY (idMineral) REFERENCES mineral(idMineral)
);

INSERT INTO mina_mineral (idMina, idMineral) VALUES
(1, 1), 
(2, 2), 
(3, 3), 
(4, 4), 
(5, 5); 

CREATE TABLE ordenes_de_compra (
    idOrden INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    destinatario INT, 
    total DOUBLE,
    FOREIGN KEY (destinatario) REFERENCES usuario(idUsuario)
);

INSERT INTO ordenes_de_compra (fecha, destinatario, total) VALUES
('2025-05-01', 1, 19700.00),
('2025-05-10', 2, 12600.00),
('2025-05-15', 3, 14250.00);







-- Insertar datos en la tabla Usuarios
INSERT INTO Usuarios (Nombre, CIF, Direccion, Ciudad, Pais, Email, Telefono) VALUES
    ('Juan Perez', '12345678A', 'Calle Falsa 123', 'Madrid', 'España', 'juan.perez@example.com', '600123456'),
    ('Ana García', '23456789B', 'Avenida Siempre Viva 456', 'Barcelona', 'España', 'ana.garcia@example.com', '601234567'),
    ('Pedro Martínez', '34567890C', 'Plaza Mayor 789', 'Valencia', 'España', 'pedro.martinez@example.com', '602345678');

-- Insertar datos en la tabla Clientes
INSERT INTO Clientes (Nombre, CIF_DNI, Direccion, Ciudad, Pais, Email, Telefono, UsuarioId) VALUES
    ('Empresa XYZ', '56789012D', 'Calle Empresa 1', 'Madrid', 'España', 'contacto@empresaxyz.com', '610123456', 1),
    ('Cliente 1', '12345678E', 'Calle Cliente 1', 'Sevilla', 'España', 'cliente1@example.com', '611234567', 2),
    ('Cliente 2', '23456789F', 'Calle Cliente 2', 'Bilbao', 'España', 'cliente2@example.com', '612345678', 3);

CREATE TABLE Iva (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL
);

INSERT INTO Iva (nombre, porcentaje) VALUES
    ('IVA 0%', 0),
    ('IVA 4%', 0.04),
    ('IVA 10%', 0.10),
    ('IVA 21%', 0.21);

-- Tabla para proveedores
CREATE TABLE Usuarios (
    UsuarioId SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    CIF VARCHAR(20) NOT NULL UNIQUE, -- Haciendo que el campo CIF sea único
    Direccion VARCHAR(200),
    Ciudad VARCHAR(100),
    Pais VARCHAR(100),
    Email VARCHAR(100) NOT NULL UNIQUE, -- Haciendo que el campo Email sea único
    Telefono VARCHAR(20)
);

-- Tabla para clientes
CREATE TABLE Clientes (
    ClienteID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    CIF_DNI VARCHAR(20) UNIQUE, -- Haciendo que el campo CIF o DNI sea único
    Direccion VARCHAR(200),
    Ciudad VARCHAR(100),
    Pais VARCHAR(100),
    Email VARCHAR(100) UNIQUE, -- Haciendo que el campo Email sea único
    Telefono VARCHAR(20), -- Agregando el campo Teléfono
    UsuarioId INT, -- Agregar columna para la relación uno a muchos con Usuarios
    FOREIGN KEY (UsuarioId) REFERENCES Usuarios(UsuarioId) -- Definir la clave externa
);

-- Tabla para productos
CREATE TABLE Productos (
    ProductoID Serial PRIMARY KEY,
    Nombre VARCHAR(100),
    Precio DECIMAL(10, 2),
    Stock INT
);

-- Tabla para impuestos de IVA
CREATE TABLE ImpuestoIVA (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Porcentaje DECIMAL(5, 2)
);

-- Tabla para impuestos de IRPF
CREATE TABLE ImpuestoIRPF (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Porcentaje DECIMAL(5, 2)
);


-- Tabla para facturas con la columna Ejercicio añadidas
CREATE TABLE Facturas (
    FacturaID SERIAL PRIMARY KEY,
    ClienteID INT,
    UsuarioID INT,
    Fecha DATE,
    Irpf DECIMAL(5, 2),
    ivaID INT NOT NULL,
    Subtotal DECIMAL(10, 2),
    Total DECIMAL(10, 2),
    NumeroFactura VARCHAR(20), -- Columna añadida para el número de factura
    Ejercicio INT, -- Columna añadida para el ejercicio (año)
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID),
    FOREIGN KEY (UsuarioID) REFERENCES Usuarios(UsuarioID),
    FOREIGN KEY (ivaID) REFERENCES IVA(id)
);


-- Tabla para detalles de factura
CREATE TABLE DetallesFactura (
    DetalleID SERIAL PRIMARY KEY,
    FacturaID INT,
    ProductoID INT,
    ServicioPersonalizado VARCHAR(200),
    Cantidad INT,
    PrecioUnitario DECIMAL(10, 2),
    TotalLinea DECIMAL(10, 2),
    FOREIGN KEY (FacturaID) REFERENCES Facturas(FacturaID),
    FOREIGN KEY (ProductoID) REFERENCES Productos(ProductoID)
);

-- Tabla para detalles de impuestos por factura
--CREATE TABLE DetallesImpuestos (
  --  DetalleImpuestoID SERIAL PRIMARY KEY,
    --FacturaID INT,
    --TipoImpuesto VARCHAR(50),
    --Monto DECIMAL(10, 2),
    --FOREIGN KEY (FacturaID) REFERENCES Facturas(FacturaID)
--);

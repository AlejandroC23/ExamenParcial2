/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Alejandro
 * Created: 5 ago. 2024
 */

CREATE TABLE Proveedores (
    pro_id INT AUTO_INCREMENT PRIMARY KEY,
    pro_nombre VARCHAR(100) NOT NULL,
    pro_ciudad VARCHAR(50) NOT NULL,
    pro_codigo VARCHAR(24) NOT NULL UNIQUE,
    pro_cedula VARCHAR(24) NOT NULL UNIQUE,
    pro_telefono VARCHAR(15) NOT NULL,
    pro_email VARCHAR(100) NOT NULL
);

CREATE TABLE Productos (
    produ_id INT AUTO_INCREMENT PRIMARY KEY,
    produ_nombre VARCHAR(100) NOT NULL,
    produ_tamanio DECIMAL(10,2) NOT NULL,
    produ_peso DECIMAL(10,2) NOT NULL,
    produ_precio DECIMAL(10,2) NOT NULL,
    pro_id INT NOT NULL
);

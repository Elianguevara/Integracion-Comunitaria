# 🤝 Integración Comunitaria - Desktop App

![Java](https://img.shields.io/badge/Java-23-ED8B00?style=for-the-badge&logo=openjdk)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql)
![Architecture](https://img.shields.io/badge/Pattern-MVC%20%2B%20DAO-blue?style=for-the-badge)

## 📖 Descripción

Esta aplicación de escritorio es una solución integral para fomentar la **economía colaborativa local**. Su objetivo es conectar a miembros de la comunidad que necesitan servicios ("Clientes") con vecinos capacitados para ofrecerlos ("Proveedores").

A diferencia de la versión web, este cliente pesado está construido en **Java puro**, implementando una arquitectura clásica en capas para garantizar robustez en la gestión de datos y transacciones.

## 🚀 Módulos y Funcionalidades

### 👤 Gestión de Usuarios
* **Perfiles Diferenciados:** Registro y gestión de perfiles de Cliente y Proveedor.
* **Seguridad:** Módulo de autenticación propio (`AuthController`, `SecurityModuleUser`).
* **Reputación:** Sistema de calificaciones (`GradeCustomer`, `GradeProvider`) para generar confianza en la comunidad.

### 💼 Mercado de Servicios
* **Peticiones (Petitions):** Los clientes pueden publicar necesidades de servicio.
* **Postulaciones:** Los proveedores pueden postularse a trabajos abiertos.
* **Ofertas:** Gestión de propuestas de servicios.

### 🛠️ Administración
* **Inventario:** Control de recursos o herramientas disponibles.
* **Reportes:** Visualización de datos mediante tablas dinámicas (`PeticionesTableModel`, `PostulationsTableModel`).

## 🏗️ Arquitectura del Software

El proyecto sigue estrictamente el patrón de diseño **MVC (Modelo-Vista-Controlador)** junto con **DAO (Data Access Object)** para desacoplar la lógica de negocio de la persistencia de datos.

* **Model:** Entidades de negocio (POJOs) que representan las tablas de la BD (ej. `User`, `Petition`, `Provider`).
* **View:** Interfaz gráfica de usuario construida con **Java Swing** (ej. `DashboardView`, `LoginView`).
* **Controller:** Orquesta la comunicación entre la vista y el modelo (ej. `AuthController`, `ProviderController`).
* **Service:** Capa intermedia que contiene la lógica de negocio compleja.
* **DAO:** Abstracción de las operaciones CRUD directas contra MySQL (JDBC).

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 23 (Utilizando *Preview Features*)
* **Gestión de Dependencias:** Maven
* **Base de Datos:** MySQL 8.0
* **Conectividad:** JDBC (Java Database Connectivity)
* **Interfaz Gráfica:** Java Swing (AWT/Swing)

## ⚙️ Instalación y Configuración

### Prerrequisitos
* **JDK 23**: Es estrictamente necesario debido a la configuración del `pom.xml`.
* **MySQL Server**: Instancia local o remota.

### 1. Clonar el repositorio
```bash
git clone [https://github.com/elianguevara/Integracion-Comunitaria.git](https://github.com/elianguevara/Integracion-Comunitaria.git)

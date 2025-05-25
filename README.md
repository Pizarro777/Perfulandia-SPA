# Perfulandia
Parcial 2 - FullStack
﻿# 🍏 Perfulandia-SPA – Gestión de Productos

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)  
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.6-brightgreen?logo=springboot)  

---

## 📖 Descripción

**Perfulandia-SPA** es un servicio RESTful desarrollado con Spring Boot para la gestión de productos de una plataforma de delivery.  
Permite crear, leer, actualizar y eliminar (CRUD) productos, validando datos y documentando automáticamente su API con OpenAPI/Swagger.

---

## 🚀 Tabla de Contenidos

1. [Características](#-características)
2. [Tecnologías Usadas](#-tecnologías-usadas)
3. [Requisitos Previos](#-requisitos-previos)
4. [Instalación](#-instalación)
5. [Ejecución](#-ejecución)
6. [Documentación Swagger](#-documentación-swagger)
7. [Endpoints Principales](#-endpoints-principales)
8. [MapStruct DTO & Mapper](#-mapstruct-dto--mapper)
9. [Buenas Prácticas](#-buenas-prácticas)
10. [Contribuir](#-contribuir)
11. [Licencia](#-licencia)

---

## ⭐️ Características

- CRUD de productos con validaciones (`@NotBlank`, `@Positive`, etc.)
- Persistencia JPA + H2
- DTOs 
- Gestión de versiones de entidad con `@Version`
- Fechas de creación/actualización con Hibernate (`@CreationTimestamp`, `@UpdateTimestamp`)

---

## 🛠 Tecnologías Usadas

- **Java 21**
- **Spring Boot 3.4.6**
- **Spring Boot DevTools**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database** (runtime, pruebas locales)
- **OpenFeign**
- **Valitadion**
- **Lombok**
- **Maven**

---

## ⚙️ Requisitos Previos

- JDK 21+
- Maven 3.5+
- Oracle Database (o usar H2 para pruebas)
- Git

---

## 📥 Instalación

```bash
# Clona el repositorio
git clone https://github.com/Pizarro777/Perfulandia-SPA
cd Perfulandia-SPA

# Configura tu profile en src/main/resources/application-*.properties
# (Oracle o H2 según tu entorno)

# Compila y empaqueta
mvn clean package

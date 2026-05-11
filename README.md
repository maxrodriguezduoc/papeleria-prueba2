# PRUEBA PARCIAL II: Papelería The Office

## DESCRIPCIÓN

Un sistema para gestionar la papelería The Office mediantes entidades relacionadas a los diferentes aspectos a tratar, tales como cargos a crear, locales junto a la comuna y región respectivas, productos, formas de pago, entre otros que se podrá ir viendo en el código. El objetivo del proyecto es agilizar los inventarios, mantener una base de datos con clientes y compras realizadas para futuras ventas.

## INTEGRANTES

Ignacio Andrés Acevedo Espinoza

Yaritxa Gonzales Soto

Maximiliano Esteban Rodriguez Conejan

## TECNOLOGÍAS UTILIZADAS

* Java 21

Spring Boot

Spring Data JPA + Hibernate

Laragorn

Maven

Lombok

GitHub

## ARQUITECTURA CSR

Para el proyecto utilizaremos el patrón de Controller, Service y Repository.

## FUNCIONALIDADES

POST /api/v1/productos → Crear producto 
GET /api/v1/productos → Listar productos 
PUT /api/v1/productos/{id} → Actualizar producto 
DELETE /api/v1/productos/{id} → Eliminar producto 
INSTRUCCIONES PARA UTILIZAR CÓDIGO

## Clonar Repositorio

git clone https://github.com/maxrodriguezduoc/papeleria-prueba2.git 
cd papeleria-prueba2 

## Configurar Base de Datos

spring.datasource.url=jdbc:mysql://localhost:3306/papeleria_theoffice
spring.datasource.username=root
spring.datasource.password=

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


Ejecutar proyecto
mvn spring-boot:run

Ejecutar API en Postman en:
http://localhost:8080

# com-tbk-demo-exercise

Microservicio orientado al registro de usuarios y registros telefonicos asi como tambien a la informacion de login de dichos usuarios

## Comenzando 🚀

Descargar Fuentes de git

```
git clone https://github.com/JesusGarcia9009/com-tbk-demo-exercise.git
git checkout main
```
Una vez descargada las fuentes se debe ejecutar el siguiente comando en consola:

```
mvn clean install
```


La ejecucion de la consola para acceder a la  base de datos es a través del siguiente link:

```
https://localhost:8080/h2-console
user: admin
pass: admin
```
La ejecucion del swagger es atravez del sigiente link:

```
http://localhost:8080/swagger-ui.html#
```
La ejecucion del servicio de registro con el siguiente request:

```
body:
{
    "name":"Jesus Garcia",
    "email":"jesus@gmail.com",
    "password":"Ja11",
    "phones":[
        {
            "number":"1234567",
            "citycode":"1",
            "contrycode":"57"
        }
    ]
}
```
quedando de la siguiente forma

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/ejecucion%20del%20registro.png)

los registros guardados quedan de la siguiente forma

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/ejecucion%20del%20registro%20-%20datos%20guardados.png)


## Estructura 🚀

El siguiente proyecto tiene la siguiente estructura de ejecucion

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/diagrama.png)

Los scripts de para la creacion de la base de datos esta en el archivo

* [DDL.sql](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/DDL.sql) - Archivo generador de entidades


Las configuraciones del sistema están en el archivo el cual se puede eliminar la propiedad de creacion del modelo:

> **bootstrap.yml**
```
jpa:
    database-platform: org.hibernate.dialect.H2Dialect 
    hibernate:
      ddl-auto: update  #esta propiedad se podria cambiar por none y ejecutar el script de base de datos
    properties:
      hibernate: 
        show_sql: false
        use_sql_comments: true  
        format_sql: true
```

## Pre-requisitos 🛠

- Maquina Virtual de Java
- Gradle
- Variables de entorno
- IDE
- Lombok


## Construido con 🛠


Herramientas y lenguajes utilizados


* [Java](https://www.java.com/) - Lenguaje de programacion.
* [Maven](https://maven.apache.org/) - Manejador de dependencias.
* [Eclipse](https://www.eclipse.org/) - IDE de desarrollo.
* [Lombok](https://projectlombok.org/) - Creacion de metodos basicos de objetos.

## Autores.

* **Jesus Garcia** - *Trabajo Inicial-Programacion-Documentacion* 


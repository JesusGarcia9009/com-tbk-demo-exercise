# com-tbk-demo-exercise

Microservicio orientado al registro de usuarios y registros telefónicos así como también a la información de login de dichos usuarios.

## Comenzando ðŸš€

Descargar Fuentes de git:

```
git clone https://github.com/JesusGarcia9009/com-tbk-demo-exercise.git
git checkout main
```
Una vez descargadas las fuentes se debe ejecutar el siguiente comando en consola del sistema operativo:

```
mvn clean install
```


Después de iniciado el proyecto, se puede ejecutar la consola H2 para acceder a la  base de datos, a través del siguiente link:

```
https://localhost:8080/h2-console
user: admin
pass: admin
```
La ejecución del swagger es a través del siguiente link:

```
http://localhost:8080/swagger-ui.html#
```
La ejecución del servicio de registro con el siguiente request:

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


## Estructura ðŸš€

El siguiente proyecto tiene la siguiente estructura de ejecucion

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/diagrama.png)

Los scripts de para la creacion de la base de datos esta en el archivo

* [DDL.sql](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/DDL.sql) - Archivo generador de entidades


Las configuraciones del sistema estÃ¡n en el archivo el cual se puede eliminar la propiedad de creacion del modelo:

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


## Test Unitarias
En el proyecto se crearon test unitarios utilizando mockito como referencia, estos TEST se hicieron de manera representativa, solo para demostrar el conocimiento por lo que no esta la cobertura al 100%, los test unitarios estan en:

```
RegisterControllerImplTest
UserControllerImplTest
```

## Despliegue ðŸ“¦

* Para desplegar el proyecto en una image docker o k8s


```
$mvn clean install
docker build -t com-tbk-demo-exercise .
docker run --name com-tbk-demo-exercise -p 8080:8080 com-tbk-demo-exercise
```
* En caso de querer desplegar en K8s se puede subir al registry (dockerhub, gitlab, bitbucket, etc) y aplicar el deployment

```
$docker push registry.gitlab.com/com-tbk-demo-exercise/{component}:{release}_{enviroment}
$kubectl apply -f k8s
```
PD: Ademas de esto en este proyecto se crea carpeta de despliegue de K8s en la raiz del proyecto

## Queue

* Al ejecutar el MS se debe generar una cola "Apache ActiveMQ" la cual quedara en consola de la siguiente forma

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%201.png)

* El MS constara con unos metodos que le daran uso a la Cola y podra simular una cantidad alta de peticiones 
- /home - crea los parametros iniciales de la cola
- /health - nos muestra si la cola esta activa 
- /metrics - nos muestra la cantidad de mensajes encolados
- /submit - metodo que recibe un long y este inserta ese mismo numero de peticiones en la cola

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%202.png)

* AL subscribir 500 mensajes 

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%203.png)
![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%204.png)

* Metrica de mensajes enviados

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%205.png)

* Metrica de mensajes enviados

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%205.png)

* Logs de mensajes procesados

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%206.png)


```
$mvn clean install
docker build -t com-tbk-demo-exercise .
docker run --name com-tbk-demo-exercise -p 8080:8080 com-tbk-demo-exercise
```
* En caso de querer desplegar en K8s se puede subir al registry (dockerhub, gitlab, bitbucket, etc) y aplicar el deployment

```
$docker push registry.gitlab.com/com-tbk-demo-exercise/{component}:{release}_{enviroment}
$kubectl apply -f k8s
```
PD: Ademas de esto en este proyecto se crea carpeta de despliegue de K8s en la raiz del proyecto

## Pre-requisitos ðŸ› 

- Maquina Virtual de Java
- Gradle
- Variables de entorno
- IDE
- Lombok


## Construido con ðŸ› 


Herramientas y lenguajes utilizados


* [Java](https://www.java.com/) - Lenguaje de programacion.
* [Maven](https://maven.apache.org/) - Manejador de dependencias.
* [Eclipse](https://www.eclipse.org/) - IDE de desarrollo.
* [Lombok](https://projectlombok.org/) - Creacion de metodos basicos de objetos.

## Autores.

* **Jesus Garcia** - *Trabajo Inicial-Programacion-Documentacion* 


# com-tbk-demo-exercise

Microservicio orientado al registro de usuarios y registros telef�nicos as� como tambi�n a la informaci�n de login de dichos usuarios.

## Comenzando

Descargar Fuentes de git:

```
git clone https://github.com/JesusGarcia9009/com-tbk-demo-exercise.git
git checkout main
```
Una vez descargadas las fuentes se debe ejecutar el siguiente comando en consola del sistema operativo:

```
mvn clean install
```


Despu�s de iniciado el proyecto, se puede ejecutar la consola H2 para acceder a la  base de datos, a trav�s del siguiente link:

```
https://localhost:8080/h2-console
user: admin
pass: admin
```
La base de datos se crea junto con el modelo al iniciar el Microservicio, en caso de que se quiera generar el modelo a traves de un archivo SQL, se debe cambiar la siguiente propiedad en el archivo:

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

Los scripts para la generaci�n del modelo de base de datos est� en el archivo:

* [DDL.sql](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/DDL.sql) - Archivo generador de entidades


La ejecuci�n del Swagger es a trav�s del siguiente link:

```
http://localhost:8080/swagger-ui.html#
```
Dentro del Swagger se van a encontrar varios endpoints relacionados con el ejercicio que se pueden ejecutar desde el mismo Swagger, quedando de la siguiente forma:

* Ejemplo de Registro

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/ejecucion%20del%20registro.png)

* Los registros guardados quedan de la siguiente forma:
	
![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/ejecucion%20del%20registro%20-%20datos%20guardados.png)


## Estructura

El proyecto tiene la siguiente estructura:

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/diagrama.png)


## Test Unitarios
En el proyecto se crearon test unitarios utilizando mockito como referencia. Estos TEST se hicieron de manera representativa, solo para demostrar el conocimiento, por lo que no est� la cobertura al 100%. Se encuentran en:

```
RegisterControllerImplTest
UserControllerImplTest
```

## Despliegue

* Para desplegar el proyecto en una image docker o k8s:


```
$mvn clean install
docker build -t com-tbk-demo-exercise .
docker run --name com-tbk-demo-exercise -p 8080:8080 com-tbk-demo-exercise
```
* En este proyecto se gener� una carpeta de despliegue de K8s en la ra�z. En caso de querer desplegar en K8s, se puede subir al registry (dockerhub, gitlab, bitbucket, etc) y aplicar el deployment:

```
$docker push registry.gitlab.com/com-tbk-demo-exercise/{component}:{release}_{enviroment}
$kubectl apply -f k8s
```

## Queue

* Al ejecutar el MS se debe generar una cola "Apache ActiveMQ" la cual quedar� en consola de la siguiente forma:

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%201.png)

El MS consta con m�todos que le dar�n uso a la cola y podr� simular una cantidad alta de peticiones: 
- /home - crea los par�metros iniciales de la cola
- /health - nos muestra si la cola est� activa 
- /metrics - nos muestra la cantidad de mensajes encolados
- /submit - m�todo que recibe un long e inserta ese mismo n�mero de peticiones en la cola

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%202.png)

* Al subscribir 500 mensajes: 

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%203.png)
![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%204.png)

* M�trica de mensajes enviados:

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%205.png)

* M�trica de mensajes enviados durante el tiempo de procesamiento:

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%206.png)

* Logs de mensajes procesados:

![alt text](https://github.com/JesusGarcia9009/com-tbk-demo-exercise/blob/main/doc/queue%207.png)


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


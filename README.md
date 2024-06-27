# LiterAlura

Este proyecto es un catálogo de libros que utiliza la API web JSON para metadatos de libros electrónicos del Proyecto Gutenberg para obtener información de los libros.

## Instalación

1. Clona el repositorio:

    ```bash
    git clone https://github.com/BrayanGamba/literAlura.git
    ```

2. Importa el proyecto en tu IDE favorito como un proyecto Maven.

3. Configura la base de datos PostgreSQL:
   - Asegúrate de tener PostgreSQL instalado y configurado en tu máquina.
   - Crea una base de datos llamada `literalura` (o el nombre que desees) en PostgreSQL.
   - Actualiza las configuraciones de conexión a la base de datos en el archivo `application.properties` dentro de `src/main/resources`:

     ```properties
     spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
	 spring.datasource.username=${DB_USER}
	 spring.datasource.password=${DB_PASSWORD}
	 spring.datasource.driver.class-name=org.postgresql.Driver
	 hibernate.dialect=org.hibernate.dialect.HSQLDialect
	 spring.jpa.hibernate.ddl-auto=update
     ```

4. Ejecuta el proyecto desde tu IDE ejecutando la clase `ChallengeApplication`.

## Uso General

1. Ejecuta la clase `ChallengeApplication` desde tu IDE.

2. Selecciona una de las siguientes opciones:

   - **1**: Buscar libro por título
   - **2**: Listar libros registrados
   - **3**: Listar autores registrados
   - **4**: Listar autores vivos desde un año determinado
   - **5**: Listar libros por idioma
   - **0**: Cierra el programa.

3. Sigue las instrucciones proporcionadas por el programa para realizar la acción deseada.

## Contribución

¡Contribuciones son bienvenidas! Si deseas contribuir, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz un commit (`git commit -am 'Agrega nueva característica'`).
4. Haz push a la rama (`git push origin feature/nueva-caracteristica`).
5. Abre un pull request.

## Licencia

Este proyecto está bajo la [Licencia MIT](LICENSE).

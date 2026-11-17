# Proyecto Final: Calidad, Verificación y Validación del Software

Pruebas automatizadas de la tienda de demostración Swag Labs (https://www.saucedemo.com), hechas con Java, Selenium y JUnit 5.

## Antes de empezar

Tener instalado:

- Java (Temurin 17 o superior)
- Maven
- Google Chrome
- IntelliJ IDEA
- GitHub Desktop

## Cómo abrir el proyecto

1. En GitHub Desktop: File, Clone repository, buscar `Proyecto-Calidad-Verificacion-Validacion` y clonar.
2. Abrir IntelliJ IDEA y elegir File, Open.
3. Seleccionar la carpeta del proyecto y abrirla.
4. Esperar a que IntelliJ termine de cargar (barra de progreso abajo a la derecha).

## Cómo correr las pruebas

1. En IntelliJ, abrir la pestaña **Terminal** (abajo).
2. Escribir:

```
mvn test
```

3. Esperar cerca de un minuto. Se abre Chrome solo y se cierra solo.
4. Al final debe decir `BUILD SUCCESS` y `Failures: 0, Errors: 0`.

Para correr una sola clase, por ejemplo la de login:

```
mvn test -Dtest=LoginTest
```

## Dónde ver el reporte

Al terminar, abrir en el navegador el archivo:

```
target/reporte/reporte.html
```

## Cómo trabajar sin dañar lo de los demás

1. En GitHub Desktop: Current Branch, New Branch, y ponerle tu nombre (por ejemplo `samanta-torunno`).
2. Hacer tus cambios y probar con `mvn test`.
3. En GitHub Desktop: escribir un mensaje corto de lo que hiciste, Commit y luego Push.
4. Avisar al grupo para que revisen tu rama.

No subir las carpetas `target` ni `.idea`.

## Integrantes

- Samanta Toruño Sequeira
- Camila Morales Solano
- Gabriel Bryan Salazar
- Angelo Orlando Campos
- Carlos Manuel De La O

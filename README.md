# Proyecto Final: Calidad, Verificación y Validación del Software

Pruebas automatizadas de la tienda de demostración Swag Labs (https://www.saucedemo.com), hechas con Java, Selenium WebDriver y JUnit 5, con el patrón Page Object Model.

Son 29 pruebas: 27 casos funcionales y 2 no funcionales (tiempo de respuesta del login). Se ejecutaron en Chrome, Firefox y Edge sin fallas.

## Antes de empezar

Tener instalado:

- Java (Temurin 17 o superior)
- Maven
- Google Chrome (Firefox y Edge solo si se quiere probar en esos navegadores)
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

3. Esperar entre 2 y 4 minutos. Se abre el navegador solo y se cierra solo, una vez por prueba.
4. Al final debe decir `BUILD SUCCESS` y `Tests run: 29, Failures: 0, Errors: 0`.

Otras formas de correrlas:

| Qué | Comando |
|---|---|
| Una sola clase, por ejemplo la de login | `mvn test -Dtest=LoginTest` |
| En Firefox | `mvn test -Dbrowser=firefox` |
| En Edge | `mvn test -Dbrowser=edge` |
| Sin abrir ventana | `mvn test -Dheadless=true` |
| Pruebas de defectos conocidos | `mvn test -Pdefectos` |

El navegador, la URL, la clave y el tiempo de espera también se pueden cambiar en `src/test/resources/config.properties`.

## Pruebas de defectos (`DefectosTest`)

Detectan de forma automática los defectos de `problem_user` y `error_user` que se reportaron a mano en `evidencias/defectos`. Cada prueba verifica el comportamiento correcto, así que **se espera que fallen** mientras el defecto exista. El mensaje de la falla dice qué defecto es (por ejemplo `DEF03`).

Por eso no corren con `mvn test`. Solo corren con `mvn test -Pdefectos`.

## Qué hay en cada carpeta

```
src/test/java/proyecto/
├── base/    Abre y cierra el navegador, lee la configuración y genera el reporte
├── pages/   Una clase por pantalla (login, catálogo, carrito, compra, menú)
└── tests/   Las pruebas
src/test/resources/   config.properties y los CSV de las tablas de decisión
evidencias/defectos/  Reportes de defectos con sus capturas
```

Las pruebas dicen qué se espera, las páginas saben cómo usar cada pantalla y no tienen verificaciones, y la clase `BasePage` tiene las esperas explícitas.

| Clase de prueba | Pruebas | Casos |
|---|---|---|
| LoginTest | 8 | TC01 a TC08 |
| InventoryTest | 4 | TC13 a TC16 |
| CartTest | 5 | TC19 a TC23 |
| CheckoutFormTest | 6 | TC26 a TC31 |
| CheckoutResumenTest | 1 | TC36 |
| CheckoutTest | 1 | TC37 |
| SessionTest | 2 | TC25 y TC39 |
| RendimientoTest | 2 | NF01 y NF02 (tiempo de login) |

## Dónde ver el reporte

Al terminar, abrir en el navegador el archivo:

```
target/reporte/reporte.html
```

Si una prueba falla, el reporte incluye una captura de pantalla del momento de la falla.

No subir las carpetas `target` ni `.idea`.

## Integrantes

- Samanta Toruño Sequeira
- Camila Morales Solano
- Carlos Manuel De La O

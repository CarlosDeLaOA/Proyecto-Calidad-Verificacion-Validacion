# Proyecto-Calidad-Verificacion-Validacion
# Proyecto Final BISOFT-32: Pruebas automatizadas con Selenium y POM

Aplicación bajo prueba: Swag Labs (https://www.saucedemo.com)
Equipo: Carlos, Samanta y Camila

## Cómo trabajamos en el repositorio

Cada integrante trabaja en **su propia rama**, para no pisarnos los cambios. La rama `main` es la versión estable y solo recibe cambios ya probados.

### 1. Clonar el repositorio (una sola vez)
En GitHub Desktop: **File → Clone repository → URL**, pegue la dirección del repo y elija una carpeta local.

### 2. Crear su rama personal
1. En GitHub Desktop, haga clic en **Current branch** (arriba) y luego en **New branch**.
2. Póngale su nombre, en minúsculas y sin espacios: `samanta` o `camila`.
3. Verifique que la rama base sea `main` y haga clic en **Create branch**.
4. Haga clic en **Publish branch** para subirla a GitHub.

### 3. Trabajar y guardar cambios
1. Antes de empezar cada día, cambie a su rama y haga clic en **Fetch origin** y luego **Pull** para traer lo más reciente.
2. Trabaje en su flujo (login, carrito o compra).
3. En GitHub Desktop revise los cambios, escriba un mensaje de commit claro (por ejemplo: `Agrega CartPage y TC12`) y haga clic en **Commit to su-rama**.
4. Haga clic en **Push origin** para subir los cambios.

Haga commits pequeños y frecuentes. **Todas las personas del equipo deben tener commits** en el historial, porque es un requisito del proyecto.

### 4. Pasar el trabajo a `main`
Cuando una parte esté lista y sus tests pasen con `mvn test`:
1. En GitHub Desktop: **Branch → Create Pull Request**.
2. En GitHub, otra persona del equipo revisa el Pull Request y lo aprueba.
3. Se hace **Merge** a `main`. Después, todas actualizamos nuestras ramas con los cambios de `main`.

## Reparto de flujos
| Persona | Flujo |
|---------|-------|
| Carlos | Login y menú/sesión |
| Samanta | Inventario y carrito |
| Camila | Compra y pruebas no funcionales |

(Ajustar el reparto según lo acordado.)

## Reglas básicas
- No hacemos commits directamente en `main`.
- No subimos la carpeta `target/` ni archivos del IDE.
- Si hay un conflicto al fusionar, avisamos al equipo antes de resolverlo.

## Cómo ejecutar las pruebas
```
mvn test
```
(Se completará cuando el proyecto esté armado.)

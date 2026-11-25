# Resultados de los casos manuales

Ejecución manual en Windows 11 con Google Chrome, en https://www.saucedemo.com, con la clave secret_sauce.
Resultado: 13 pasaron y 2 fallaron (TC41 y TC46).


Resultado: **Pasó**, **Falló** (se reporta como defecto DEF11 en adelante en `evidencias/defectos`) o **Bloqueado**.

## Resumen

| Caso | Req. | Resultado | Evidencia |
|---|---|---|---|
| TC09 | RF03 | Pasó | TC09.png |
| TC10 | RF03 | Pasó | TC10.png |
| TC11 | RF03 | Pasó | TC11.png |
| TC12 | RF05 | Pasó | TC12.png |
| TC17 | RF06 | Pasó | TC17.png |
| TC18 | RF07 | Pasó | TC18.png |
| TC24 | RF07, RF08 | Pasó | TC24.png |
| TC46 | RF05 | Falló | TC46.png |
| TC32 | RF09 | Pasó | TC32.png |
| TC33 | RF09 | Pasó | TC33.png |
| TC34 | RF09 | Pasó | TC34.png |
| TC35 | RF09 | Pasó | TC35.png |
| TC38 | RF10 | Pasó | TC38.png |
| TC40 | RF11 | Pasó | TC40.png |
| TC41 | RF11 | Falló | TC41.png |

## Detalle

Para cada caso: resultado obtenido en una línea. Si falló, copiar el formato de `evidencias/defectos/README.md`.

### TC09 Usuario en mayúsculas
- Pasos: 1. Escribir STANDARD_USER y secret_sauce. 2. Presionar Login.
- Esperado: mensaje de credenciales inválidas (Username and password do not match any user in this service).
- Obtenido: Mostró "Epic sadface: Username and password do not match any user in this service".

### TC10 Clave con espacio al final
- Pasos: 1. Escribir standard_user y "secret_sauce " (con espacio al final). 2. Presionar Login.
- Esperado: mensaje de credenciales inválidas.
- Obtenido: Mostró el mismo mensaje de credenciales inválidas; la clave con espacio no se acepta.

### TC11 Cerrar el mensaje de error con la X
- Pasos: 1. Presionar Login con los campos vacíos. 2. Presionar la X del mensaje.
- Esperado: el mensaje desaparece.
- Obtenido: Con campos vacíos mostró "Username is required"; al presionar la X el mensaje desapareció.

### TC12 El catálogo muestra 6 productos
- Pasos: 1. Iniciar sesión con standard_user. 2. Contar los productos y revisar nombre, descripción, precio e imagen.
- Esperado: 6 productos, cada uno con los cuatro datos.
- Obtenido: 6 productos; cada uno con nombre, descripción, precio e imagen.

### TC17 Orden inicial del catálogo
- Pasos: 1. Iniciar sesión con standard_user. 2. Ver la opción seleccionada del filtro y el orden de los nombres.
- Esperado: Name (A to Z).
- Obtenido: Opción seleccionada Name (A to Z) y nombres en orden alfabético (Backpack, Bike Light, Bolt T-Shirt, Fleece Jacket, Onesie, Test.allTheThings()).

### TC18 Agregar un producto con el carrito vacío
- Pasos: 1. Iniciar sesión con standard_user. 2. Presionar Add to cart en un producto.
- Esperado: el botón cambia a Remove y el contador del carrito es 1.
- Obtenido: El botón cambió a Remove y el contador del carrito mostró 1.

### TC24 Carrito al cerrar sesión
- Pasos: 1. Iniciar sesión con standard_user. 2. Agregar un producto. 3. Menú, Logout. 4. Iniciar sesión de nuevo y mirar el carrito.
- Esperado: a confirmar (hallazgo H3). Anotar si el carrito se conserva o queda vacío.
- Obtenido: Tras Logout y nuevo login el carrito se conservó: contador en 1 y botón Remove en la mochila. Hallazgo H3 confirmado: el carrito no se vacía al cerrar sesión.

### TC46 visual_user
- Pasos: 1. Iniciar sesión con visual_user. 2. Comparar posición e imágenes de los elementos con standard_user.
- Esperado: elementos con posición e imagen correctas.
- Obtenido: Con visual_user el catálogo se ve distinto a standard_user: la imagen de la mochila es la foto de un perro, los precios cambian ($20.7, $31.82, $74.21, $21.82, $96.97, $93.75), el ícono del carrito está movido y hay títulos y botones desalineados (el último Add to cart sale de su tarjeta). Se reporta como DEF12.

### TC32 Un carácter en cada campo
- Pasos: 1. Agregar un producto, ir al carrito y presionar Checkout. 2. Escribir "a", "b" y "1". 3. Presionar Continue.
- Esperado: pasa al resumen de la compra.
- Obtenido: Con "a", "b" y "1" pasa al resumen (checkout-step-two).

### TC33 Nombre de 100 caracteres
- Pasos: 1. En el formulario, escribir un nombre de 100 caracteres, apellido y código postal válidos. 2. Presionar Continue.
- Esperado: exploratorio. Anotar si lo acepta o lo rechaza.
- Obtenido: Acepta un nombre de 100 caracteres y pasa al resumen. No hay límite de largo.

### TC34 Nombre solo con espacios
- Pasos: 1. En el formulario, escribir espacios en el nombre y texto en los demás. 2. Presionar Continue.
- Esperado: exploratorio. Anotar si lo acepta o lo rechaza.
- Obtenido: Acepta un nombre de solo espacios y pasa al resumen. Observación: no valida que el nombre tenga contenido.

### TC35 Números y símbolos en nombre y apellido
- Pasos: 1. En el formulario, escribir por ejemplo "123" y "#$%" en nombre y apellido. 2. Presionar Continue.
- Esperado: exploratorio. Anotar si lo acepta.
- Obtenido: Acepta "123" y "#$%" como nombre y apellido y pasa al resumen. No valida el tipo de caracteres.

### TC38 Compra con un solo producto
- Pasos: 1. Agregar un solo producto. 2. Llegar al resumen con datos válidos.
- Esperado: el impuesto es el 8 % del subtotal y el total es subtotal más impuesto.
- Obtenido: Un producto de $29.99: impuesto $2.40 (8 %) y total $32.39 (subtotal más impuesto).

### TC40 All Items
- Pasos: 1. Iniciar sesión, abrir el carrito. 2. Menú, All Items.
- Esperado: regresa al catálogo.
- Obtenido: Desde el carrito, el menú All Items regresa al catálogo (inventory.html).

### TC41 Reset App State restablece los botones
- Pasos: 1. Agregar un producto (el botón cambia a Remove). 2. Menú, Reset App State.
- Esperado: el carrito queda vacío y el botón del catálogo vuelve a Add to cart. Si el botón sigue en Remove, es el defecto del hallazgo H6.
- Obtenido: Reset App State vacía el carrito (la insignia desaparece) pero el botón de la mochila sigue en Remove y no vuelve a Add to cart. Es el hallazgo H6; se reporta como DEF11.

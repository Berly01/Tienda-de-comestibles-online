# Tienda de Comestibles Online

Una tienda de comestibles online es un sistema que registra usuarios, categorías, productos, gestiona pedidos y crea ofertas para productos con descuento aleatorios que cambian después de un tiempo determinado

## Propósito del proyecto


## Tecnologías 

| Tecnologia  | Versión |
| ------------- | ------------- |
| Java | JDK21  |
| Maven | 4.0.0 |
| Spring boot  | 2.7.18  |
| Thymeleaf    | 3.1 |
| Mysql | 8.0.33 |
| Mysql-connector-java | 8.0.33 |

## Herramientas Extras
* ModelMapper
* Cloudinary 

## Entorno de ejecución
* Windows 10 x64
* Spring Tool Suite - 4
* Java - 21.0.3

## Domain Driven Design

Entidades: Category, Offer, Order, OrderProduct, Product, Receipt, Role, User.

Objetos de valor:

Fabricas: 

Repositorios: CategoryRepository, OfferRepository, OrderRepository, ProductRepository, ReceiptRepository, 
UserRepository, UserRoleRepository.

Servicios: CategoryServiceImpl, OfferServiceImpl, 
OrderServiceImpl, ProductServiceImpl, ReceiptServiceImpl, UserServiceImpl.

### Representacion en Arquitectura en Capas
![image](https://github.com/user-attachments/assets/644b6e1c-481a-484f-8ad1-b157fb02c61b)

## Patrón de Arquitectura

### Modelo-Vista-Controlador (MVC):

La aplicación sigue el patrón de diseño MVC, separando las preocupaciones en diferentes capas:

Modelo: Representa los datos y la lógica de negocio.

Vista: Maneja la presentación de los datos (Thymeleaf para este proyecto).

Controlador: Gestiona la entrada del usuario y actualiza el modelo y la vista en consecuencia.

### Representacion gráfica

## Principios SOLID



```java


```
## Funcionalidades

### User
Los usuarios pueden agregar productos a su "Carrito" y luego pueden revisar productos y crear pedidos con el estado "Pendiente". Los pedidos de los usuarios son controlados por los administradores (rol = "Administrador" o "Moderador"). Los usuarios pueden ver detalles sobre sus propios pedidos. Cuando se entrega un pedido, un usuario puede adquirirlo, momento en el que se crea un recibo con ese pedido y ese usuario. Los usuarios pueden ver sus recibos y detalles sobre cada recibo. Los administradores (rol = "Administrador", "Moderador") son esencialmente como los usuarios normales. También pueden tener pedidos, que se entregan, adquieren y también tienen recibos. Los administradores también pueden administrar pedidos para un usuario específico.

* Pueden ver todos los pedidos pendientes y pueden enviarlos.
* Pueden ver todos los pedidos enviados y entregarlos.
* Pueden ver todos los pedidos entregados y ver detalles sobre ellos.

La aplicación proporciona a los usuarios invitados (no registrados) la funcionalidad para:

* Iniciar sesión
* Registrarse
* Ver la página de índice de invitados con productos de venta

La aplicación proporciona a los usuarios (registrados) la funcionalidad para:

* Cerrar sesión
* Comprar productos
* Crear pedidos
* Ver sus pedidos
* Ver detalles sobre un pedido
* Ver sus recibos
* Ver detalles sobre un recibo

La aplicación proporciona a los moderadores (que han iniciado sesión y tienen el rol de Moderador) la funcionalidad de:

* Cerrar sesión
* Administrar todos los productos
* Administrar todas las categorías
* Comprar productos
* Crear pedidos
* Ver sus pedidos
* Ver detalles sobre un pedido
* Ver sus recibos
* Ver detalles sobre un recibo
* Ver todos los pedidos pendientes
* Ver todos los pedidos enviados
* Ver todos los pedidos entregados
* Ver detalles sobre todos los pedidos entregados
* Enviar pedidos
* Entregar pedidos

La aplicación proporciona a los administradores (que han iniciado sesión y tienen el rol de administrador) la funcionalidad para:

* Cerrar sesión
* Administrar todos los productos
* Administrar todas las categorías
* Comprar productos
* Crear pedidos
* Ver sus pedidos
* Ver detalles sobre un pedido
* Ver sus recibos
* Ver detalles sobre un recibo
* Ver todos los pedidos pendientes
* Ver todos los pedidos enviados
* Ver todos los pedidos entregados
* Ver detalles sobre todos los pedidos entregados
* Enviar pedidos
* Entregar pedidos

### Productos
Los productos se crean con un Nombre, una Descripción, un Precio, una URL de Imagen (Nube) y Categorías a las que pertenece.

### Ofertas
Las ofertas se crean aleatoriamente utilizando la anotación @Scheduled en Spring con un producto aleatorio y un nuevo precio con descuento.

### Pedidos
Cuando se crean Pedidos, se crean con una lista de Productos, un Precio Total, una Dirección de Envío y un Usuario Destinatario.

* Tras la creación, el estado de un pedido debe establecerse en Pendiente.
* Tras la creación, la fecha de emisión de una orden debe establecerse en la fecha actual.

#### Pedido pendientes
Un pedido pendiente puede ser enviado por un administrador haciendo clic en el botón [Enviar] en la página de pedidos pendientes. En ese momento, el estado del pedido pasa a ser "Enviado" y la fecha del estado (enviado el) debe establecerse en una fecha actual.

* Todas las Órdenes Pendientes se presentan en la Página de Órdenes Pendientes.
* Un usuario puede ver sus Órdenes pendientes en su página Mis órdenes en el bloque rectangular Pendiente.
* Un Usuario puede ver detalles sobre cada una de sus Órdenes Pendientes desde su Página Mis Órdenes, haciendo clic en el botón [Detalles].

#### Pedidos enviados
Un pedido enviado puede ser entregado por un administrador haciendo clic en el botón [Entregar] en la página pedido enviados. En ese momento, el Estado del pedido pasa a ser "Entregado" y la Fecha de estado (Entregado el) debe establecerse en una fecha actual.

* Todos los pedidos enviados se presentan en la página de paquetes enviados.
* Un usuario puede ver sus pedidos enviados en su página de índice en el bloque rectangular enviado.
* Un Usuario puede ver detalles sobre cada uno de sus pedidos Enviados desde su Página de Índice, haciendo clic en el botón [Detalles].

#### Pedidos entregados
Un pedido entregado puede ser adquirido por el destinatario del pedido haciendo clic en el botón [Adquirir] en su página Mis pedidos. En ese momento el Estado del Pedido pasa a ser "Adquirido" y se genera un Recibo al Usuario por ese Pedido. Todos los pedidos entregados se presentan en la página de pedidos entregados. Un Usuario puede ver sus Pedidos Entregados en su Página Mis Pedidos en el bloque rectangular Entregado. Un Usuario puede Adquirir cada uno de sus Pedidos Entregados desde su Página Mis Pedidos, haciendo clic en el botón [Adquirir].

### Recibos
Los recibos son solo entidades de datos. Se crean cuando un Pedido es Adquirido por su Usuario Destinatario.

## Pruebas de APIs (Postman)

PRUEBAS DE APIS:
Este proyecto registra usuarios, administrar productos y categorías, maneja pedidos y ofrece funciones como la creación de ofertas para productos con descuento aleatorio. Estas son algunas de las API y funcionalidades clave utilizadas en el proyecto:

# API de gestión de usuarios: maneja el registro de usuarios, el inicio de sesión, la asignación de roles y la gestión de perfiles. Los usuarios pueden tener roles como "Administrador raíz", "Administrador", "Moderador" o "Usuario", cada uno con permisos específicos.
Registro:
Probar el endpoint de registro de usuario
![image](https://github.com/user-attachments/assets/97175fc3-3e2a-4c83-8323-966b79a6dcbf)
Capturamos la solicitud:
![image](https://github.com/user-attachments/assets/f0334346-f212-426a-b861-82ee7738ff19)
Obtenemos un resultado exitoso con respecto a nuestra request
![image](https://github.com/user-attachments/assets/ee2dfcd4-d733-4b0b-99e4-98ad73a3df3d)
# API de gestión de productos: permite a los administradores administrar productos, incluida la creación, actualización y eliminación de listados de productos. Los productos están asociados con categorías y tienen atributos como nombre, descripción, precio y URL de imagen.

Comprobamos el status al momento de seleccionar un producto 
Se envia el codigo del producto y la cantidad a solicitar
![image](https://github.com/user-attachments/assets/d7a96596-649a-4cf5-8e47-0cfc12f737ba)
![image](https://github.com/user-attachments/assets/427f6de7-4f5c-4045-9fe1-390fd09bdb3e)
Recibimos un status 200 
![image](https://github.com/user-attachments/assets/6387ac71-ed3d-4fd2-afeb-f0e86a175bce)
# API de gestión de pedidos: administra el ciclo de vida de un pedido desde su creación hasta su finalización. Los usuarios pueden crear pedidos, ver sus pedidos y los administradores pueden enviar, entregar y ver los detalles de los pedidos.
Podemos ver los productos solicitados anteriormente
![image](https://github.com/user-attachments/assets/2a356b8c-3062-4c3f-9b3e-51a963fb530c)
![image](https://github.com/user-attachments/assets/31f4cc46-7f00-426c-b91c-5154452671c3)
API de busqueda de productos: La anotación @GetMapping("/api/find") en el método searchProducts en el UserController define un endpoint de API de búsqueda. Este endpoint permite a los usuarios buscar productos basados en una parte del nombre del producto.
![image](https://github.com/user-attachments/assets/56d24e61-cdc7-4255-b10c-a7738f5da978)
![image](https://github.com/user-attachments/assets/2cd544da-2c17-40e8-afcb-c5b8dc3e55e4)

Configuración de seguridad: implementa Spring Security para gestionar la autenticación y la autorización de diferentes roles de usuario, lo que garantiza que solo los usuarios autorizados puedan acceder a funcionalidades específicas.
## Pruebas de Rendimiento (jMeter)
## Pruebas de Seguridad (Zap)

Esta tabla muestra el número de alertas de cada tipo de alerta, junto con el nivel de riesgo del tipo de alerta.
(Los porcentajes entre paréntesis representan cada recuento como un porcentaje, redondeado a un decimal, del número total de alertas incluidas en este informe).
![image](https://github.com/user-attachments/assets/c55b1a3d-f24c-4418-84f4-ed99124e79e0)

## Gestión de Cambios – Issues (Trello)

https://trello.com/b/5EylDJmu
## Documentación de Servicios en base al estándar OpenAPI y

## ScreenShots
  

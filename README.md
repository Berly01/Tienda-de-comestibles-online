Resumen de la Arquitectura
### Framework de Backend:
La aplicación utiliza el framework Spring MVC para manejar las solicitudes web y gestionar la lógica de negocio.

### Gestión de Base de Datos:
Utiliza repositorios para interactuar con la base de datos, aprovechando Spring Data JPA para las operaciones de base de datos.

### Seguridad:
Se emplea Spring Security para la autenticación y autorización. Los usuarios tienen roles como "Usuario", "Moderador", "Admin" y "Root Admin", cada uno con diferentes niveles de acceso.

### Tareas Programadas:
La aplicación utiliza la anotación @Scheduled en Spring para crear y actualizar ofertas de descuento a intervalos regulares.

### Modelo-Vista-Controlador (MVC):
La aplicación sigue el patrón de diseño MVC, separando las preocupaciones en diferentes capas:
- **Modelo**: Representa los datos y la lógica de negocio.
- **Vista**: Maneja la presentación de los datos (normalmente plantillas JSP o Thymeleaf).
- **Controlador**: Gestiona la entrada del usuario y actualiza el modelo y la vista en consecuencia.

### Componentes Clave
- **Gestión de Usuarios**: Incluye registro, inicio de sesión, gestión de roles y gestión de perfiles.
- **Gestión de Productos**: Permite agregar, actualizar y visualizar productos, categorizados adecuadamente.
- **Gestión de Pedidos**: Los usuarios pueden crear pedidos y los administradores pueden gestionar el ciclo de vida del pedido (pendiente, enviado, entregado, adquirido).
- **Ofertas**: Creación dinámica de descuentos de productos para aumentar el compromiso de los usuarios y las ventas.

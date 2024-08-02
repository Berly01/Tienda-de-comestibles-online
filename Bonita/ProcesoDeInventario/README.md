# Proceso De Inventario

![image](https://github.com/user-attachments/assets/dfdedd8b-08f8-4171-aa95-bc770708d1d2)
# # Flujo:

- Se realiza la tarea humana de inspección de productos, tomandose en cuenta el identificador de producto, la cantidad de productos procesados y el Id del encargado de la tarea.
- Se procede a validar o invalidar los productos (se utiliza un XOR que recepciona un valor booleano desde el formulario validarCalidad).
- Si el/los producto(s) es/son válido(s), se registran los productos en el inventario y se actualiza, finaliza el proceso.
- Si el/los producto(s) es/son válido(s), se rechaza el registro de los productos y se notifica al proveedor la necesidad de reenvío de la cantidad de productos rechazados, finaliza proceso.
# # Data manejada (variables generadas):
- Inspección (objeto de negocios, aplicando contrato para asegurar la integridad de el envío de datos):
![image](https://github.com/user-attachments/assets/acb395c9-1595-434e-8241-98da7a6b4c4e)

El flujo por defecto es rechazar el proceso (false) de inspección.

# Supermercado BPMN

## Soporte al Cliente

![soporte-cliente-2 0](https://github.com/user-attachments/assets/e0c128ac-ad65-43b5-8d29-f8b0d6fb8418)

## Descripción del modelo

El modelo BPMN permite al usuario consultar al soporte de cliente, cual se divide en 2 niveles, nivel general y técnico. Los actores del soporte técnico pueden aceptar o rechazar una consulta y solicitar al cliente información extra. El actor de soporte general puede derivar una consulta si considera que no puede solicitar la consulta del cliente.

### Variables

![variables](https://github.com/user-attachments/assets/878ad1c2-0460-42af-af3c-2e4d78d2f123)

## Actores

![cliente](https://github.com/user-attachments/assets/5b127b67-75da-471e-a7c1-4630749e9b14)

### Contratos y Restricciones

![c1](https://github.com/user-attachments/assets/77a4f1fb-5fa4-4c2d-93ce-24292ce0ed4d)

![c2](https://github.com/user-attachments/assets/cb108593-376f-4437-8b18-63911b1cab03)

![c3](https://github.com/user-attachments/assets/f2cc77d6-2646-48b3-8fa6-28c4438b9257)

![c4](https://github.com/user-attachments/assets/297af1fe-841c-4805-b7a7-6a5c6b33c42a)

### Formularios: GUI

![f1](https://github.com/user-attachments/assets/c17db523-04c7-4d77-8981-36d9973f3297)

## Elementos de información necesarios (datos o información) en el proceso de negocio.
Servicios del proyecto de IS3 mediante RestAPI.
* Consultar productos: http://localhost:8000/products/fetch
* Consultar ofertas: http://localhost:8000/offers/fetch

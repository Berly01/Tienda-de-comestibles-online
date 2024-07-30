-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: online_grocery_shopping_db
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` varchar(255) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_41g4n0emuvcm3qyf1f6cn43c0` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES ('3057b100-1e6e-425e-8d42-e360eb5dcc47',_binary '\0','Carne y pescado'),('54c41dd6-173e-480b-b29c-966871cda4dc',_binary '\0','Carnes y salami'),('781faf36-446f-45d1-a81a-6d4cea0854eb',_binary '\0','Pan y tortillas'),('9218c278-21d3-46be-9014-56fc2acd469b',_binary '\0','Empaquetado'),('a05e844f-a59b-4fa6-9240-6a76da999059',_binary '\0','Lácteos y refrigerados'),('a688f4f6-4047-4313-952e-f0646023c2de',_binary '\0','Frutas y vegetales'),('b321ea69-aba6-4b52-8230-3d5fa94590ee',_binary '\0','Bebé y niño'),('b4662b4f-cf0a-4f11-a246-0216e1060930',_binary '\0','Productos cosméticos'),('d7c26365-704a-433b-9f9e-99a5ccc56752',_binary '\0','Farmacia'),('dbab687a-4028-419f-9b99-0ced0991e09f',_binary '\0','Bebidas'),('dc93559f-a3ae-4df8-842d-5440446123ee',_binary '\0','Productos Congelados'),('ed97cd74-6513-40c8-9859-4b60959f846d',_binary '\0','Mascotas');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offers` (
  `id` varchar(255) NOT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjf1jh3h4v4m7diel8vvhmuqas` (`product_id`),
  CONSTRAINT `FKjf1jh3h4v4m7diel8vvhmuqas` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES ('02438320-6f94-47bb-8015-8481dde1ffc9',1.27,'15058738-724e-49b0-955f-9bbfda740f47'),('07f75d9e-0ca7-4099-a0f8-294c28a6577c',1.04,'a633a2cd-99b2-4aa9-bbc7-2af352bb6ff6'),('19a10d85-b810-4885-ab3c-a99433404341',14.36,'54f23916-481a-44e4-bf32-92ddad56d2e3'),('3082f4c4-aaf3-4ef9-8799-94e84d1d02e0',5.17,'2e303796-38b5-44a6-965f-eae4f27c205b'),('31156d1e-ff6b-4b60-a9b7-5f24915374c3',2.92,'3b4b6559-0f33-4c02-9368-338f29724c6b'),('7f9f2d5c-0d78-477c-98d8-cbacf8494484',0.77,'567e87a7-4d93-443e-9329-7f42c9612226'),('86fd902d-d580-4972-b77d-fa01e30ea324',7.50,'74b75e2b-c834-40f6-ab93-129a37ad149d'),('e81b16ad-e646-4f9c-afaa-d9b1b11e125c',7.49,'888ccac1-85b9-49c0-b611-34e59c47d716'),('f6f4e983-6bf6-43da-ac44-1a6c7132540f',10.49,'cd8d2b3e-036b-4422-bf77-0f5722e51899');
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_products`
--

DROP TABLE IF EXISTS `order_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_products` (
  `id` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdxjduvg7991r4qja26fsckxv8` (`product_id`),
  CONSTRAINT `FKdxjduvg7991r4qja26fsckxv8` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_products`
--

LOCK TABLES `order_products` WRITE;
/*!40000 ALTER TABLE `order_products` DISABLE KEYS */;
INSERT INTO `order_products` VALUES ('b8c044d3-7f81-4d80-926e-4250ee7d02ef',1.07,'1113c156-3000-4566-a344-47f5e568d192'),('ca245063-28be-4931-bd3d-010272141865',5.17,'2e303796-38b5-44a6-965f-eae4f27c205b');
/*!40000 ALTER TABLE `order_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL,
  `issued_on` datetime NOT NULL,
  `shipping_address` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `status_date` datetime DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsjfs85qf6vmcurlx43cnc16gy` (`customer_id`),
  CONSTRAINT `FKsjfs85qf6vmcurlx43cnc16gy` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('42c47b00-931f-4884-b73c-e10b900d7e37','2024-07-29 21:46:17','casa del chalan','SHIPPED','2024-07-30 03:02:16',17.65,'3788ffa6-f12e-4b4d-a0af-27fcf0db2e42');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_products`
--

DROP TABLE IF EXISTS `orders_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_products` (
  `order_id` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  KEY `FK9uy9gvge1wvkbiu44wl6sjm3e` (`product_id`),
  KEY `FKe4y1sseio787e4o5hrml7omt5` (`order_id`),
  CONSTRAINT `FK9uy9gvge1wvkbiu44wl6sjm3e` FOREIGN KEY (`product_id`) REFERENCES `order_products` (`id`),
  CONSTRAINT `FKe4y1sseio787e4o5hrml7omt5` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_products`
--

LOCK TABLES `orders_products` WRITE;
/*!40000 ALTER TABLE `orders_products` DISABLE KEYS */;
INSERT INTO `orders_products` VALUES ('42c47b00-931f-4884-b73c-e10b900d7e37','b8c044d3-7f81-4d80-926e-4250ee7d02ef'),('42c47b00-931f-4884-b73c-e10b900d7e37','b8c044d3-7f81-4d80-926e-4250ee7d02ef'),('42c47b00-931f-4884-b73c-e10b900d7e37','ca245063-28be-4931-bd3d-010272141865'),('42c47b00-931f-4884-b73c-e10b900d7e37','ca245063-28be-4931-bd3d-010272141865'),('42c47b00-931f-4884-b73c-e10b900d7e37','ca245063-28be-4931-bd3d-010272141865');
/*!40000 ALTER TABLE `orders_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` varchar(255) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `description` text NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('101a6f34-2a66-4cab-bde0-3f3dbba24dfc',_binary '\0','Postre de yogur Vereya con trozos de fresa 2% 290 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Yogur',1.19),('1113c156-3000-4566-a344-47f5e568d192',_binary '\0','Hongos Champiñones','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Hongos',1.07),('11b0d3fe-fbb5-48fc-83a4-836a14d86c64',_binary '\0','Frijoles Krina Calypso 500 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Frijoles Krina Calypso 500 g',2.59),('130721b5-9a4e-4a3b-a96f-9ae5edb0d381',_binary '\0','Vino rosado Inovino 750 ml','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Vino rosado Inovino 750 ml',5.59),('15058738-724e-49b0-955f-9bbfda740f47',_binary '\0','Comida para gatos Poésie Bites en salsa Pollo con verduras del huerto 85 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Comida para gatos Poésie Bites en salsa Pollo con verduras del huerto 85 g',1.69),('1b49d950-15eb-48af-b63d-a59c9017d9a0',_binary '\0','Fórmula enriquecida con luteína, zeaxantina, arándano, zinc, vitamina E y vitamina C.','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Cápsulas ABOVision 15 uds.',19.80),('23494efe-2f8d-46a5-a190-3b7f6c340b50',_binary '\0','Cóctel Le Coq Sex on the Beach 4,7% Alcohol 330 ml','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Cóctel Le Coq Sex on the Beach 4,7% Alcohol 330 ml',2.49),('253ed024-c0cf-4dd6-83b5-eeb6fc593969',_binary '\0','Ralitsa de queso amarillo de leche de vaca 500 g aproximadamente','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Queso amarillo',6.84),('258be006-73ad-4983-a6bf-efa566115d1a',_binary '\0','Peras Williams Red','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Peras',3.99),('26c3fd3a-6e2b-4a0c-948a-7239e087100d',_binary '\0','Elena Filete Madjarov Rebanada 80 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Elena Filete',3.89),('2e303796-38b5-44a6-965f-eae4f27c205b',_binary '\0','Yogur colado Olympus 2% 1 kg','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Yogur colado 2%',6.89),('2f917b41-e096-4c40-863c-9e92d92ed1ff',_binary '\0','Cerveza Heineken lata de 330 ml','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Cerveza Heineken lata de 330 ml',1.19),('3b4b6559-0f33-4c02-9368-338f29724c6b',_binary '\0','Queso de Vaca Madjarov 200 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Queso de vaca',3.89),('54f23916-481a-44e4-bf32-92ddad56d2e3',_binary '\0','fundada en 1914','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Cerveza de león',19.14),('56594b7e-77fd-40d3-99b5-485b12855fd7',_binary '\0','Cereales de desayuno Nestlé León 400 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Cereales de desayuno Nestlé León 400 g',4.99),('567e87a7-4d93-443e-9329-7f42c9612226',_binary '\0','Yogur Vereya BDS 2% 400 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Yogur Vereya',1.02),('584d557a-feff-413f-9ed9-66b827bb586e',_binary '\0','Manzanas Ecológicas Granja Moravsko Selo 500 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Manzanas',2.10),('611860fc-ad6c-494c-8684-18d0142eb74c',_binary '\0','Crema Corporal Tesori d\'Oriente Loto 300 ml','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Crema Corporal Tesori d\'Oriente Loto 300 ml',8.99),('68149f12-4591-4cc4-8e08-0ba4ed698648',_binary '\0','Palta Grande 1 ud','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Palta',3.20),('6a37d4ca-2796-48e2-9868-220bd33f5631',_binary '\0','NAN 2 es una leche de transición para lactantes a partir de los 6 meses, como parte de una dieta de destete variada.','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Leche infantil Nestlé NAN OptiPro Transitional 2 400 g',15.99),('6a9f07dd-b70c-40c9-90b3-d24a0d003776',_binary '\0','Bahur Eko Mes unos 400 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Bahur',3.16),('6b391814-1ead-45b3-b641-b62e8ae73720',_binary '\0','Mantequilla de coco Dragon Superfoods sin aroma 1 l','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Dragón de mantequilla de coco',16.99),('6dafe540-6acc-4eb0-b1aa-cb4ffe20def7',_binary '\0','ongue Eco Mes Gelatina de Cerdo 200 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','ongue Eco',3.19),('74b75e2b-c834-40f6-ab93-129a37ad149d',_binary '\0','Bebida Gasificada','http://res.cloudinary.com/dyvzmgc5r/image/upload/v1722285394/xibc25rgemehduwvsa3y.png','Fanta',10.00),('7b20a6de-8960-480b-a1a2-ea90c14a8239',_binary '\0','Comida para gatos Purina One Adult con pollo seca 200 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Comida para gatos Purina One Adult con pollo seca 200 g',2.99),('7e132e94-196c-4346-b47a-5585cdcc709f',_binary '\0','Frijoles Krina Rojos 500 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Frijoles Krina Rojos 500 g',2.59),('83070669-ec6e-4c2d-b881-7596f251840a',_binary '\0','Potente elixir para fortalecer el sistema inmunológico. Elaborado a base de bayas de saúco, fructosa y pectina.','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Jarabe Business House de Saúco 280 g',4.39),('87ceb41a-902c-464a-90b5-085dcd7098bf',_binary '\0','Tortilla Flour El Paso Large Soft 6 pcs 350 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Tortilla',3.59),('888ccac1-85b9-49c0-b611-34e59c47d716',_binary '\0','Harina Para Tortilla El Paso Grande Blanda 6uds 350 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Brandy Sungurlarska Uva 700 ml',9.99),('88f1ebce-3a1d-48b7-bb3a-326e7c523845',_binary '\0','Lubina eviscerada unos 550 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Bajo',7.65),('98ba9ba0-37c5-43ba-a545-b8c3d331c2ba',_binary '\0','Cerveza MaDonna Blanche 500 ml','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Cerveza MaDonna Blanche 500 ml',1.64),('98cb0d61-6e1f-492c-85e1-d9af715a93f9',_binary '\0','Helado Coppa Della Maga Vegano Té Verde Matcha 85 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Helado Coppa Della Maga Té Verde Vegano',8.99),('9ae12ded-159a-4a8c-86f0-6c4553cd531d',_binary '\0','Bonduelle de maíz congelado 400 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Bonduelle de maíz',2.99),('a2f07a57-057c-43c5-826a-5f0adb3f7ca2',_binary '\0','Patatas lavadas','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Papas',1.79),('a633a2cd-99b2-4aa9-bbc7-2af352bb6ff6',_binary '\0','Pan Eliaz Pan Integral Centeno 400 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Pan Eliaz de Centeno Marrón',1.39),('aeecc076-b380-4e83-869b-e365959363e9',_binary '\0','Gel de ducha Nivea Crème Soft 750 ml','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Gel de ducha Nivea Crème Soft 750 ml',4.99),('b0f91b67-7610-4650-9a1c-e72a0bc31019',_binary '\0','Paté de salmón Nordson 100 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Salmón',2.39),('b335ec20-0109-422e-8047-d4783d349b87',_binary '\0','Tocino KFM Maestro 190 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Tocino KFM',3.49),('b7e2713f-f5db-4be4-a2ca-f0a7cfd41dd2',_binary '\0','Rábanos ecológicos de la granja Moravsko Selo 1 manojo','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Rábanos',2.19),('bb25501c-37e4-4c26-92da-0f895fcdb8f5',_binary '\0','Comida para perros Jungle Junior Seca 400 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Comida para perros Jungle Junior Seca 400 g',1.49),('c5d30c53-e943-45e2-935d-8c9c7886c824',_binary '\0','Marca: Rolnik Origen: Polonia Código del producto: 400634 Alcaparras Rolnik 200 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Alcaparras Rolnik 200 g',2.89),('c768c91a-9825-4d5e-83fe-1875e5a0960b',_binary '\0','Pan Eliaz Pan Integral Tostado 600 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Pan integral',1.55),('c9e21d30-87c0-4d61-9f72-205da6f73069',_binary '\0','Puré de ternera Ganchev con patatas y tomates 190 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Puré de ternera Ganchev con patatas y tomates 190 g',1.49),('cd8d2b3e-036b-4422-bf77-0f5722e51899',_binary '\0','Condones Durex Real Feel 10 uds','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Condones Durex Real Feel 10 uds',13.99),('ce176c14-e8b1-42c3-a898-65fe700e2c0f',_binary '\0','Complemento alimenticio orgánico Physalis contra la hinchazón de estómago 45 comprimidos','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Complemento alimenticio orgánico Physalis contra la hinchazón de estómago 45 comprimidos',19.99),('ce57210a-9983-49ce-94bd-527e77793fb1',_binary '\0','Brócoli 500 gr','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Brócoli',1.99),('db10b54e-330a-4c1d-90b9-1e639e67b2de',_binary '\0','Jamón Tandem Praga 100 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','jamón',2.05),('de28e362-8cb5-422f-b637-fb3f1b2e167a',_binary '\0','Helado Coppa Della Maga Amaretto 330 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Helado',8.90),('ea6d560f-cf66-4374-9f13-195933b3b386',_binary '\0','Espadines en aceite de Riga Gold Tarro 250 g','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Espadines',4.49),('f6f4a480-dd52-43bb-91c2-a222458bdb4e',_binary '\0','Refresco','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Naranjas',1.55),('fbfa85ec-2419-447a-8486-02c4d55e8239',_binary '\0','ACTION Atsetizal® in low doses prevents the occurrence of heart diseases such as recurrent myocardial infarction in patients with myocardial infarction or unstable angina (angina pectoris) or stroke caused by blockage of blood vessels.','https://res.cloudinary.com/dyvzmgc5r/image/upload/v1722271426/Y19maWxsLHdfMjIwLGhfMjIw_lfp5ur.jpg','Acetisal 500 mg 20 comprimidos',1.79);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_categories`
--

DROP TABLE IF EXISTS `products_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_categories` (
  `product_id` varchar(255) NOT NULL,
  `category_id` varchar(255) NOT NULL,
  KEY `FKqt6m2o5dly3luqcm00f5t4h2p` (`category_id`),
  KEY `FKtj1vdea8qwerbjqie4xldl1el` (`product_id`),
  CONSTRAINT `FKqt6m2o5dly3luqcm00f5t4h2p` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKtj1vdea8qwerbjqie4xldl1el` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_categories`
--

LOCK TABLES `products_categories` WRITE;
/*!40000 ALTER TABLE `products_categories` DISABLE KEYS */;
INSERT INTO `products_categories` VALUES ('f6f4a480-dd52-43bb-91c2-a222458bdb4e','a688f4f6-4047-4313-952e-f0646023c2de'),('258be006-73ad-4983-a6bf-efa566115d1a','a688f4f6-4047-4313-952e-f0646023c2de'),('a2f07a57-057c-43c5-826a-5f0adb3f7ca2','a688f4f6-4047-4313-952e-f0646023c2de'),('584d557a-feff-413f-9ed9-66b827bb586e','a688f4f6-4047-4313-952e-f0646023c2de'),('68149f12-4591-4cc4-8e08-0ba4ed698648','a688f4f6-4047-4313-952e-f0646023c2de'),('ce57210a-9983-49ce-94bd-527e77793fb1','a688f4f6-4047-4313-952e-f0646023c2de'),('1113c156-3000-4566-a344-47f5e568d192','a688f4f6-4047-4313-952e-f0646023c2de'),('b7e2713f-f5db-4be4-a2ca-f0a7cfd41dd2','a688f4f6-4047-4313-952e-f0646023c2de'),('b0f91b67-7610-4650-9a1c-e72a0bc31019','3057b100-1e6e-425e-8d42-e360eb5dcc47'),('6dafe540-6acc-4eb0-b1aa-cb4ffe20def7','3057b100-1e6e-425e-8d42-e360eb5dcc47'),('88f1ebce-3a1d-48b7-bb3a-326e7c523845','3057b100-1e6e-425e-8d42-e360eb5dcc47'),('ea6d560f-cf66-4374-9f13-195933b3b386','3057b100-1e6e-425e-8d42-e360eb5dcc47'),('101a6f34-2a66-4cab-bde0-3f3dbba24dfc','a05e844f-a59b-4fa6-9240-6a76da999059'),('253ed024-c0cf-4dd6-83b5-eeb6fc593969','a05e844f-a59b-4fa6-9240-6a76da999059'),('567e87a7-4d93-443e-9329-7f42c9612226','a05e844f-a59b-4fa6-9240-6a76da999059'),('2e303796-38b5-44a6-965f-eae4f27c205b','a05e844f-a59b-4fa6-9240-6a76da999059'),('3b4b6559-0f33-4c02-9368-338f29724c6b','a05e844f-a59b-4fa6-9240-6a76da999059'),('26c3fd3a-6e2b-4a0c-948a-7239e087100d','3057b100-1e6e-425e-8d42-e360eb5dcc47'),('26c3fd3a-6e2b-4a0c-948a-7239e087100d','54c41dd6-173e-480b-b29c-966871cda4dc'),('b335ec20-0109-422e-8047-d4783d349b87','3057b100-1e6e-425e-8d42-e360eb5dcc47'),('b335ec20-0109-422e-8047-d4783d349b87','54c41dd6-173e-480b-b29c-966871cda4dc'),('6a9f07dd-b70c-40c9-90b3-d24a0d003776','3057b100-1e6e-425e-8d42-e360eb5dcc47'),('6a9f07dd-b70c-40c9-90b3-d24a0d003776','54c41dd6-173e-480b-b29c-966871cda4dc'),('db10b54e-330a-4c1d-90b9-1e639e67b2de','3057b100-1e6e-425e-8d42-e360eb5dcc47'),('db10b54e-330a-4c1d-90b9-1e639e67b2de','54c41dd6-173e-480b-b29c-966871cda4dc'),('87ceb41a-902c-464a-90b5-085dcd7098bf','781faf36-446f-45d1-a81a-6d4cea0854eb'),('a633a2cd-99b2-4aa9-bbc7-2af352bb6ff6','781faf36-446f-45d1-a81a-6d4cea0854eb'),('c768c91a-9825-4d5e-83fe-1875e5a0960b','781faf36-446f-45d1-a81a-6d4cea0854eb'),('6b391814-1ead-45b3-b641-b62e8ae73720','9218c278-21d3-46be-9014-56fc2acd469b'),('9ae12ded-159a-4a8c-86f0-6c4553cd531d','dc93559f-a3ae-4df8-842d-5440446123ee'),('de28e362-8cb5-422f-b637-fb3f1b2e167a','dc93559f-a3ae-4df8-842d-5440446123ee'),('98cb0d61-6e1f-492c-85e1-d9af715a93f9','dc93559f-a3ae-4df8-842d-5440446123ee'),('11b0d3fe-fbb5-48fc-83a4-836a14d86c64','9218c278-21d3-46be-9014-56fc2acd469b'),('7e132e94-196c-4346-b47a-5585cdcc709f','9218c278-21d3-46be-9014-56fc2acd469b'),('56594b7e-77fd-40d3-99b5-485b12855fd7','9218c278-21d3-46be-9014-56fc2acd469b'),('c5d30c53-e943-45e2-935d-8c9c7886c824','9218c278-21d3-46be-9014-56fc2acd469b'),('2f917b41-e096-4c40-863c-9e92d92ed1ff','dbab687a-4028-419f-9b99-0ced0991e09f'),('98ba9ba0-37c5-43ba-a545-b8c3d331c2ba','dbab687a-4028-419f-9b99-0ced0991e09f'),('23494efe-2f8d-46a5-a190-3b7f6c340b50','dbab687a-4028-419f-9b99-0ced0991e09f'),('888ccac1-85b9-49c0-b611-34e59c47d716','dbab687a-4028-419f-9b99-0ced0991e09f'),('130721b5-9a4e-4a3b-a96f-9ae5edb0d381','dbab687a-4028-419f-9b99-0ced0991e09f'),('83070669-ec6e-4c2d-b881-7596f251840a','dbab687a-4028-419f-9b99-0ced0991e09f'),('54f23916-481a-44e4-bf32-92ddad56d2e3','dbab687a-4028-419f-9b99-0ced0991e09f'),('6a37d4ca-2796-48e2-9868-220bd33f5631','9218c278-21d3-46be-9014-56fc2acd469b'),('6a37d4ca-2796-48e2-9868-220bd33f5631','b321ea69-aba6-4b52-8230-3d5fa94590ee'),('c9e21d30-87c0-4d61-9f72-205da6f73069','9218c278-21d3-46be-9014-56fc2acd469b'),('c9e21d30-87c0-4d61-9f72-205da6f73069','b321ea69-aba6-4b52-8230-3d5fa94590ee'),('aeecc076-b380-4e83-869b-e365959363e9','b4662b4f-cf0a-4f11-a246-0216e1060930'),('611860fc-ad6c-494c-8684-18d0142eb74c','b4662b4f-cf0a-4f11-a246-0216e1060930'),('611860fc-ad6c-494c-8684-18d0142eb74c','d7c26365-704a-433b-9f9e-99a5ccc56752'),('cd8d2b3e-036b-4422-bf77-0f5722e51899','b4662b4f-cf0a-4f11-a246-0216e1060930'),('cd8d2b3e-036b-4422-bf77-0f5722e51899','d7c26365-704a-433b-9f9e-99a5ccc56752'),('bb25501c-37e4-4c26-92da-0f895fcdb8f5','ed97cd74-6513-40c8-9859-4b60959f846d'),('15058738-724e-49b0-955f-9bbfda740f47','ed97cd74-6513-40c8-9859-4b60959f846d'),('7b20a6de-8960-480b-a1a2-ea90c14a8239','ed97cd74-6513-40c8-9859-4b60959f846d'),('ce176c14-e8b1-42c3-a898-65fe700e2c0f','d7c26365-704a-433b-9f9e-99a5ccc56752'),('1b49d950-15eb-48af-b63d-a59c9017d9a0','d7c26365-704a-433b-9f9e-99a5ccc56752'),('fbfa85ec-2419-447a-8486-02c4d55e8239','d7c26365-704a-433b-9f9e-99a5ccc56752'),('74b75e2b-c834-40f6-ab93-129a37ad149d','dbab687a-4028-419f-9b99-0ced0991e09f');
/*!40000 ALTER TABLE `products_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipts`
--

DROP TABLE IF EXISTS `receipts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipts` (
  `id` varchar(255) NOT NULL,
  `fee` decimal(10,2) NOT NULL DEFAULT '0.00',
  `issued_on` datetime NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `recipient_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaww993iieqbnyihe43g2ussrw` (`order_id`),
  KEY `FK2d3mjqxef1un48sfoc76uo2yx` (`recipient_id`),
  CONSTRAINT `FK2d3mjqxef1un48sfoc76uo2yx` FOREIGN KEY (`recipient_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKaww993iieqbnyihe43g2ussrw` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipts`
--

LOCK TABLES `receipts` WRITE;
/*!40000 ALTER TABLE `receipts` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` varchar(255) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('4a6d2bdf-f42e-48e1-8ce0-bb0acd9e9b3b','ROLE_MODERATOR'),('523a2fd7-ba97-4830-ad2e-20b771da04e7','ROLE_ADMIN'),('b67b63c3-1359-4fb2-a792-7a4b35248214','ROOT_ADMIN'),('db865918-fdcc-4295-889b-a5e69f3144b6','ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('0b2adf92-6cb3-44c2-b5af-984c616c8f8f','direccion de rootModerAdmin','rootModerAdmin@gmail.com','$2a$10$n.s9DP8RhI/mvi9cL6f9IeUcAgHvprkQlpSGa/q6m38H14DX.mNq6','rootModerAdmin'),('1acea446-af6f-4f40-8407-18184a22f5da','direccion de rootModer','rootModer@gmail.com','$2a$10$foo8R0P02rSAQ/K2BVRiIu8El9kiNpSGVDailj0JFHRjRD59UUVeW','rootModer'),('3788ffa6-f12e-4b4d-a0af-27fcf0db2e42','casa del chalan','chalan@gmail.com','$2a$10$iYayvmZio9hO8G5c9k52C.wyTVjfBeBcj3biE.m5.IbwbjIgvwFaq','chalan'),('6338fb14-dd03-42b3-9346-b0233d88ad52','direccion de root','root@gmail.com','$2a$10$UQ3er.6ySg2fA6QsDzlOy.cNf7r9kUKhr5.2lpnJJWbGoalAD9YSS','root'),('ce3a006c-1914-480e-a6bc-ec25d2f16624','direccion de rootAdmin','rootadmin@gmail.com','$2a$10$Ue/x5taBtdsi6tDPv6wcPO63LczoOx0EH6m3ti3UaeHkhVUXUQ/om','rootAdmin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES ('0b2adf92-6cb3-44c2-b5af-984c616c8f8f','4a6d2bdf-f42e-48e1-8ce0-bb0acd9e9b3b'),('1acea446-af6f-4f40-8407-18184a22f5da','4a6d2bdf-f42e-48e1-8ce0-bb0acd9e9b3b'),('0b2adf92-6cb3-44c2-b5af-984c616c8f8f','523a2fd7-ba97-4830-ad2e-20b771da04e7'),('ce3a006c-1914-480e-a6bc-ec25d2f16624','523a2fd7-ba97-4830-ad2e-20b771da04e7'),('6338fb14-dd03-42b3-9346-b0233d88ad52','b67b63c3-1359-4fb2-a792-7a4b35248214'),('0b2adf92-6cb3-44c2-b5af-984c616c8f8f','db865918-fdcc-4295-889b-a5e69f3144b6'),('1acea446-af6f-4f40-8407-18184a22f5da','db865918-fdcc-4295-889b-a5e69f3144b6'),('3788ffa6-f12e-4b4d-a0af-27fcf0db2e42','db865918-fdcc-4295-889b-a5e69f3144b6');
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-30 15:02:36

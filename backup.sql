-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: storemanage
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `storemanage`
--

/*!40000 DROP DATABASE IF EXISTS `storemanage`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `storemanage` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `storemanage`;

--
-- Table structure for table `barcodes`
--

DROP TABLE IF EXISTS `barcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barcodes` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barcodes`
--

LOCK TABLES `barcodes` WRITE;
/*!40000 ALTER TABLE `barcodes` DISABLE KEYS */;
INSERT INTO `barcodes` VALUES (111111111125365278);
/*!40000 ALTER TABLE `barcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capenat`
--

DROP TABLE IF EXISTS `capenat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `capenat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `realValue` double DEFAULT NULL,
  `editedvalue` double DEFAULT NULL,
  `afterEdictvalue` double DEFAULT NULL,
  `OperationType` varchar(250) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capenat`
--

LOCK TABLES `capenat` WRITE;
/*!40000 ALTER TABLE `capenat` DISABLE KEYS */;
INSERT INTO `capenat` VALUES (1,0,0,0,NULL,NULL),(2,0,75,75,'توريد المبيعات','2023-11-19 09:40:43 PM'),(3,75,30,105,'توريد المبيعات','2023-11-20 04:32:34 AM'),(4,105,40,145,'توريد المبيعات','2023-11-20 04:34:09 AM'),(5,145,100,245,'توريد المبيعات','2023-11-20 04:37:52 AM'),(6,245,100,345,'توريد المبيعات','2023-11-20 04:39:50 AM'),(7,345,100,445,'توريد المبيعات','2023-11-20 04:41:25 AM');
/*!40000 ALTER TABLE `capenat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cartId` int NOT NULL AUTO_INCREMENT,
  `INID` int DEFAULT NULL,
  `productName` varchar(80) DEFAULT NULL,
  `Barcode` varchar(50) DEFAULT NULL,
  `QuantityNeeded` double DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `Total_price` double DEFAULT NULL,
  `empname` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `SaleType` varchar(50) DEFAULT NULL,
  `productPlace` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `priceOfBuy` double DEFAULT NULL,
  `priceOnStore` double DEFAULT NULL,
  `customerNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cartId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,'MM','200',5,15,75,'Admin','2023-11-19 09:40:43 PM','عادي','محل','الدهانات',10,50,''),(2,2,'MM','200',2,15,30,'Admin','2023-11-20 04:32:34 AM','عادي','محل','الدهانات',10,20,''),(3,3,'MKs','5',2,20,40,'Admin','2023-11-20 04:34:09 AM','عادي','محل','الدهانات',10,20,''),(4,4,'MKs','5',5,20,100,'Admin','2023-11-20 04:37:52 AM','عادي','محل','الدهانات',10,50,''),(5,5,'MKs','5',5,20,100,'Admin','2023-11-20 04:39:50 AM','عادي','محل','الدهانات',10,50,''),(6,6,'MKs','5',5,20,100,'Admin','2023-11-20 04:41:25 AM','عادي','محل','الدهانات',10,50,'');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartbuys`
--

DROP TABLE IF EXISTS `cartbuys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartbuys` (
  `id` int NOT NULL AUTO_INCREMENT,
  `INID` int DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `payed` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartbuys`
--

LOCK TABLES `cartbuys` WRITE;
/*!40000 ALTER TABLE `cartbuys` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartbuys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartreturn`
--

DROP TABLE IF EXISTS `cartreturn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartreturn` (
  `cartId` int NOT NULL AUTO_INCREMENT,
  `INID` int DEFAULT NULL,
  `productName` varchar(80) DEFAULT NULL,
  `Barcode` varchar(50) DEFAULT NULL,
  `QuantityNeeded` double DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `Total_price` double DEFAULT NULL,
  `empname` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `SaleType` varchar(50) DEFAULT NULL,
  `productPlace` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cartId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartreturn`
--

LOCK TABLES `cartreturn` WRITE;
/*!40000 ALTER TABLE `cartreturn` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartreturn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartsuppliers`
--

DROP TABLE IF EXISTS `cartsuppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartsuppliers` (
  `cartId` int NOT NULL AUTO_INCREMENT,
  `INID` int DEFAULT NULL,
  `productName` varchar(80) DEFAULT NULL,
  `Barcode` varchar(50) DEFAULT NULL,
  `companyBarcode` varchar(50) DEFAULT NULL,
  `QuantityNeeded` double DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `Total_price` double DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cartId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartsuppliers`
--

LOCK TABLES `cartsuppliers` WRITE;
/*!40000 ALTER TABLE `cartsuppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartsuppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `categoryName` (`categoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,''),(3,'الدهانات');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerId` int NOT NULL AUTO_INCREMENT,
  `customerName` varchar(50) NOT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `allRemained` double DEFAULT NULL,
  `customerType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  UNIQUE KEY `customerName` (`customerName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(50) DEFAULT NULL,
  `empType` varchar(50) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `remainedSalary` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employeeName` (`employeeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extra`
--

DROP TABLE IF EXISTS `extra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extra` (
  `exid` int NOT NULL AUTO_INCREMENT,
  `val` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`exid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra`
--

LOCK TABLES `extra` WRITE;
/*!40000 ALTER TABLE `extra` DISABLE KEYS */;
INSERT INTO `extra` VALUES (1,'6'),(2,'0'),(3,'0'),(4,'0');
/*!40000 ALTER TABLE `extra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `userType` varchar(50) NOT NULL,
  `Notes` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'Admin','Admin','25251436','0','Admin','_');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerName` varchar(50) DEFAULT NULL,
  `casherName` varchar(50) DEFAULT NULL,
  `remainedbefor` double DEFAULT NULL,
  `payed` double DEFAULT NULL,
  `remainedAfter` double DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `receiver` varchar(50) DEFAULT NULL,
  `Note` varchar(200) DEFAULT NULL,
  `customerNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payoperationforsuppliers`
--

DROP TABLE IF EXISTS `payoperationforsuppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payoperationforsuppliers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(50) DEFAULT NULL,
  `receiverName` varchar(50) DEFAULT NULL,
  `remainedBefor` double DEFAULT NULL,
  `payed` double DEFAULT NULL,
  `remainedAfter` double DEFAULT NULL,
  `operationdetails` varchar(200) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `purchaseNumber` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payoperationforsuppliers`
--

LOCK TABLES `payoperationforsuppliers` WRITE;
/*!40000 ALTER TABLE `payoperationforsuppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `payoperationforsuppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (2,'01020005212');
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `productId` bigint NOT NULL,
  `productName` varchar(100) NOT NULL,
  `priceOfBuy` double DEFAULT NULL,
  `priceOfSale` double DEFAULT NULL,
  `Taked` double DEFAULT NULL,
  `AvailableQty` double DEFAULT NULL,
  `Category` varchar(50) DEFAULT NULL,
  `storeName` varchar(50) DEFAULT NULL,
  `Note` varchar(200) DEFAULT NULL,
  `priceOfMarket` double DEFAULT NULL,
  `AvailableQtyStore` double DEFAULT NULL,
  `QuantityWarning` double DEFAULT NULL,
  `supplierName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`productId`),
  UNIQUE KEY `productName` (`productName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (5,'MKs',10.5,21,17,33,'الدهانات','مخزن برا','__',31.5,0,0,'glc'),(200,'MM',10.5,15.75,25,0,'الدهانات','مخزن برا','__',12.6,0,0,'glc'),(50262358,'s',10.5,15.75,0,0,'الدهانات','مخزن برا','__',12.6,0,0,'glc'),(111111111125365278,'Msmf',26.25,26.25,0,0,'الدهانات','مخزن برا','__',26.25,0,0,'glc');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productstypes`
--

DROP TABLE IF EXISTS `productstypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productstypes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `productId` bigint DEFAULT NULL,
  `productTypeName` varchar(100) NOT NULL,
  `priceOfBuy` double DEFAULT NULL,
  `priceOfSale` double DEFAULT NULL,
  `QuantityOfOne` double DEFAULT NULL,
  `AllQuantity` double DEFAULT NULL,
  `Category` varchar(50) DEFAULT NULL,
  `priceOfMarket` double DEFAULT NULL,
  `Note` varchar(200) DEFAULT NULL,
  `AvailableQtyStore` double DEFAULT NULL,
  `QuantityWarning` double DEFAULT NULL,
  `storeName` varchar(50) DEFAULT NULL,
  `supplierName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productTypeName` (`productTypeName`),
  KEY `productId` (`productId`),
  CONSTRAINT `productstypes_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productstypes`
--

LOCK TABLES `productstypes` WRITE;
/*!40000 ALTER TABLE `productstypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `productstypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(50) DEFAULT NULL,
  `empType` varchar(50) DEFAULT NULL,
  `Taked` double DEFAULT NULL,
  `remainedSalary` double DEFAULT NULL,
  `Note` varchar(250) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `operationType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `SaleId` int NOT NULL AUTO_INCREMENT,
  `INID` int DEFAULT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `Total_Quantity` double DEFAULT NULL,
  `payed` double DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `Remained` double DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `empname` varchar(50) DEFAULT NULL,
  `Discount` double DEFAULT NULL,
  `AllTotal` double DEFAULT NULL,
  `DayDate` varchar(50) DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `SaleType` varchar(50) DEFAULT NULL,
  `storePhone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`SaleId`),
  UNIQUE KEY `INID` (`INID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,1,'زبون',1,75,'مدفوع',0,'2023-11-19 09:40:43 PM','Admin',0,75,'2023-11-19',75,'عادي','01020005212'),(2,2,'زبون',1,30,'مدفوع',0,'2023-11-20 04:32:34 AM','Admin',0,30,'2023-11-20',30,'عادي','01020005212'),(3,3,'زبون',1,40,'مدفوع',0,'2023-11-20 04:34:09 AM','Admin',0,40,'2023-11-20',40,'عادي','01020005212'),(4,4,'زبون',1,100,'مدفوع',0,'2023-11-20 04:37:52 AM','Admin',0,100,'2023-11-20',100,'عادي','01020005212'),(5,5,'زبون',1,100,'مدفوع',0,'2023-11-20 04:39:50 AM','Admin',0,100,'2023-11-20',100,'عادي','01020005212'),(6,6,'زبون',1,100,'مدفوع',0,'2023-11-20 04:41:25 AM','Admin',0,100,'2023-11-20',100,'عادي','01020005212');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesbuys`
--

DROP TABLE IF EXISTS `salesbuys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salesbuys` (
  `saleId` int NOT NULL AUTO_INCREMENT,
  `INID` int DEFAULT NULL,
  `casherName` varchar(50) DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `Note` varchar(200) DEFAULT NULL,
  `Date` varchar(30) DEFAULT NULL,
  `empName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`saleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesbuys`
--

LOCK TABLES `salesbuys` WRITE;
/*!40000 ALTER TABLE `salesbuys` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesbuys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesreturn`
--

DROP TABLE IF EXISTS `salesreturn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salesreturn` (
  `SaleId` int NOT NULL AUTO_INCREMENT,
  `INID` int DEFAULT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `Total_Quantity` double DEFAULT NULL,
  `payed` double DEFAULT NULL,
  `Remained` double DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `empname` varchar(50) DEFAULT NULL,
  `Discount` double DEFAULT NULL,
  `AllTotal` double DEFAULT NULL,
  `DayDate` varchar(50) DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `SaleType` varchar(50) DEFAULT NULL,
  `storePhone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`SaleId`),
  UNIQUE KEY `INID` (`INID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesreturn`
--

LOCK TABLES `salesreturn` WRITE;
/*!40000 ALTER TABLE `salesreturn` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesreturn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salessuppliers`
--

DROP TABLE IF EXISTS `salessuppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salessuppliers` (
  `SaleId` int NOT NULL AUTO_INCREMENT,
  `INID` int DEFAULT NULL,
  `supplierName` varchar(50) DEFAULT NULL,
  `Total_Quantity` double DEFAULT NULL,
  `AllTotal` double DEFAULT NULL,
  `payed` double DEFAULT NULL,
  `Remained` double DEFAULT NULL,
  `Status` varchar(250) DEFAULT NULL,
  `casherName` varchar(50) DEFAULT NULL,
  `DayDate` varchar(50) DEFAULT NULL,
  `ReceiverName` varchar(50) DEFAULT NULL,
  `Note` varchar(100) DEFAULT NULL,
  `ArrivalDate` varchar(50) DEFAULT NULL,
  `companyAddress` varchar(250) DEFAULT NULL,
  `storeAddress` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`SaleId`),
  UNIQUE KEY `INID` (`INID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salessuppliers`
--

LOCK TABLES `salessuppliers` WRITE;
/*!40000 ALTER TABLE `salessuppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `salessuppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `id` int NOT NULL AUTO_INCREMENT,
  `storeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `storeName` (`storeName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'مخزن برا');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `supId` int NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(60) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Remained` double DEFAULT NULL,
  PRIMARY KEY (`supId`),
  UNIQUE KEY `supplierName` (`supplierName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'glc','','',0);
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-20 16:43:00

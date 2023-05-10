DROP DATABASE IF EXISTS `food_delivery`;
CREATE DATABASE `food_delivery`; 
USE `food_delivery`;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

CREATE TABLE `restaurant` (
  `restaurant_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `restaurant_name` varchar(50) NOT NULL,
  `restaurant_location` varchar(50) NOT NULL,
  PRIMARY KEY (restaurant_id)
) ;
INSERT INTO `restaurant` VALUES (1,'RestA','kochi');
INSERT INTO `restaurant` VALUES (2,'RestB','kottayam');
INSERT INTO `restaurant` VALUES (3,'Restc','trivandrum');
INSERT INTO `restaurant` VALUES (4,'RestD','angamaly');


CREATE TABLE `menu` (
  `menu_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `food_item` varchar(50) NOT NULL,
  `restaurant_id` tinyint(4) NOT NULL,
  PRIMARY KEY (menu_id),
  FOREIGN KEY(restaurant_id) references restaurant(restaurant_id)
) ;
INSERT INTO `menu` VALUES (1,'Salad',1);
INSERT INTO `menu` VALUES (2,'FriedRice',2);
INSERT INTO `menu` VALUES (3,'Lobster',3);
INSERT INTO `menu` VALUES (4,'Dessert',4);


CREATE TABLE `customer` (
  `customer_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(50) NOT NULL,
  `customer_phonenumber` varchar(50) NOT NULL,
  PRIMARY KEY (customer_id)
) ;
INSERT INTO `customer` VALUES (1,'customerA','1233345656');
INSERT INTO `customer` VALUES (2,'customerB','3456767677');
INSERT INTO `customer` VALUES (3,'customerC','2345456466');
INSERT INTO `customer` VALUES (4,'customerD','2345656576');


CREATE TABLE `orders` (
  `order_id` tinyint(4) NOT NULL AUTO_INCREMENT ,
   `customer_id` tinyint(4) NOT NULL ,
    `restaurant_id` tinyint(4) NOT NULL,
   `menu_id` tinyint(4) NOT NULL,
  PRIMARY KEY (order_id),
  FOREIGN KEY(restaurant_id) references restaurant(restaurant_id),
  FOREIGN KEY(menu_id) references menu(menu_id),
  FOREIGN KEY(customer_id) references customer(customer_id)
) ;
INSERT INTO `orders` VALUES (1,1,1,1);
INSERT INTO `orders` VALUES (2,2,2,2);
INSERT INTO `orders` VALUES (3,3,3,3);
INSERT INTO `orders` VALUES (4,4,4,4);

-- SELECT * FROM orders WHERE Customer_id='2';
-- INSERT INTO orders(customer_id,restaurant_id) VALUES('5','5');




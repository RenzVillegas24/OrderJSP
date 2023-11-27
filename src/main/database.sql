CREATE DATABASE if not exists 231126_Orders
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

use 231126_Orders;

CREATE TABLE if not exists `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `order` varchar(255) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` real NOT NULL,
  `isPending` tinyint(1) NOT NULL DEFAULT '1',
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

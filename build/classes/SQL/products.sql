/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  loma
 * Created: Jul 19, 2018
 */


CREATE TABLE `products` (
 `id` varchar(50) NOT NULL,
 `name` varchar(200) NOT NULL,
 `price` decimal(10,0) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

ALTER TABLE `products` ADD `quantity` INT NOT NULL AFTER `price`, ADD `purchased_price` INT NOT NULL AFTER `quantity`;
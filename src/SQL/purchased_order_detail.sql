/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  loma
 * Created: Jul 31, 2018
 */

CREATE TABLE `MiniPOS`.`purchased_order_details` ( `id` INT NOT NULL AUTO_INCREMENT , `purchased_order_id` INT NOT NULL , `product_id` INT NOT NULL , `quantity` INT NOT NULL , `price` DOUBLE NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;
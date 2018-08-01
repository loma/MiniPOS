/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  loma
 * Created: Jul 31, 2018
 */
CREATE TABLE `MiniPOS`.`purchased_order` ( `id` INT NOT NULL AUTO_INCREMENT , `total` DECIMAL NOT NULL , `purchased_on` DATETIME NOT NULL , `purchased_by` VARCHAR(100) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;

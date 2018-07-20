/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  loma
 * Created: Jul 20, 2018
 */
CREATE TABLE `sales` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `total` double NOT NULL,
 `discount` double NOT NULL,
 `paid` double NOT NULL,
 `vat` double NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1



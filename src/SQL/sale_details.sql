/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  loma
 * Created: Jul 20, 2018
 */

CREATE TABLE `sale_details` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `sale_id` int(11) NOT NULL,
 `product_id` varchar(50) NOT NULL,
 `quantity` int(11) NOT NULL,
 `price` double NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1



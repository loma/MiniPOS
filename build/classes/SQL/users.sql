/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  loma
 * Created: Jul 19, 2018
 */

CREATE TABLE `users` (
 `id` int(11) NOT NULL,
 `username` varchar(50) NOT NULL,
 `password` varchar(100) NOT NULL,
 `role` int(11) NOT NULL,
 PRIMARY KEY (`id`),
 UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

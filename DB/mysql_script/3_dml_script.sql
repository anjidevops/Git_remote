USE `mohStarter`; 

INSERT INTO `User` 
	( `Id`, `dob`, `email`, `fname`, `lname`, `mobile`, `mobile_code`, `password`, `rolename`) VALUES 
	( 7 , '2014-12-16 00:00:00', 'reception1@gmail.com', 'reception', 'reception', '5678905', 91, 'moh', 'Reception');
	
INSERT INTO `Address` (`street1`, `street2`, `city`, `country`, `pincode`,  `user_id`) VALUES
( 'street1', 'street1', 'Bangalore', 'india', 560024, 7),
( 'street2', 'street2', 'Hyderabad', 'india', 524201, 7);

INSERT INTO `role` (`id`,`name`) VALUES (7 ,'Receptionist');

INSERT INTO `role_privilege` (`role_id`, `privilege_id`) VALUES
(7,16) , 
(7,17) , 
(7,19) ,
(7,20) ; 

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(7,7) ; 
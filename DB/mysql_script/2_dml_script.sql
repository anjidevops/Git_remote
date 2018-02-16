USE `mohStarter`;


--
-- Dumping data for table `User`
--

INSERT INTO `User` (`Id`, `dob`, `email`, `fname`, `lname`, `mobile`, `mobile_code`, `password`, `rolename`) VALUES
(2, '2014-11-28 16:13:57', 'admin@gmail.com', 'admin', 'admin', '12345789', 91, 'moh', 'Admin'),
(3, '2014-12-15 00:00:00', 'doctor1@gmail.com', 'doctor1', 'doctor1', '0456789', 91, 'moh', 'Doctor'),
(4, '2014-12-15 00:00:00', 'doctor2@gmail.com', 'doctor2', 'doctor2', '085246', 91, 'moh', 'Doctor'),
(5, '2014-12-17 00:00:00', 'patient1@gmail.com', 'Patient1', 'Patient1', '12345', 91, 'moh', 'Patient'),
(6, '2014-12-16 00:00:00', 'patient2@gmail.com', 'Patient2', 'Patient2', '56789', 91, 'moh', 'Patient');


-- --------------------------------------------------------
--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`Id`, `street1`, `street2`, `city`, `country`, `pincode`,  `user_id`) VALUES
(1, 'street1', 'street1', 'Bangalore', 'india', 560024, 2),
(2, 'street1', 'street1', 'Hyderabad', 'india', 524201, 2),
(3, 'street1', 'street1', 'Bangalore', 'india', 560024, 3),
(4, 'street1', 'street1', 'Hyderabad', 'india', 524201, 3),
(5, 'street1', 'street1', 'Bangalore', 'india', 560024, 4),
(6, 'street1', 'street1', 'Hyderabad', 'india', 524201, 4),
(7, 'street1', 'street1', 'Bangalore', 'india', 560024, 5),
(8, 'street1', 'street1', 'Hyderabad', 'india', 524201, 5),
(9, 'street1', 'street1', 'Bangalore', 'india', 560024, 6),
(10, 'street1', 'street1', 'Hyderabad', 'india', 524201, 6);


--
-- Dumping data for table `Department`
--

INSERT INTO `Department` (`dept_id`, `dept_name`,`dept_desc`) VALUES
(2, 'CARDIOLOGY', 'CARDIOLOGY description'),
(3, 'DENTAL', 'DENTAL AND ORAL SURGERY description');


--
-- Dumping data for table `Doctor`
--

INSERT INTO `Doctor` (`doc_id`, `doc_spec`, `reg_number`, `dept_dept_id`, `user_id`, `accepting_apts`, `lanes`) VALUES
(2, 'CARDIOLOGY', 1234, 2, 3, b'1', 1),
(3, 'DENTAL', 6789, 3, 4, b'0', 1);

--
-- Dumping data for table `Appointment`
--

INSERT INTO `Appointment` (`Id`, `appointment_date`, `appointment_end_hour`, `appointment_end_min`, `doc_id`, `user_id`, `lanes`) VALUES
(2, '2014-12-19 09:00:00', 9, 30, 2, 5, 1),
(3, '2014-12-19 10:30:00', 10, 55, 2, 6, 1),
(4, '2014-12-19 12:30:00', 12, 55, 3, 5, 1);

--
-- Dumping data for table `medicine_inventory`
--

INSERT INTO `medicine_inventory` (`Id`, `available_quantity`, `medicine_name`, `thresold_quantity`) VALUES
(1, 50, 'كروسين', 100),
(2, 23, 'الباراسيتامول', 45);

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'nurse'),
(2, 'doctor'),
(3, 'clerk_desk'),
(4, 'clerk_inventory'),
(5, 'patient'),
(6, 'admin');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(2, 6),
(3, 2),
(4, 2),
(5, 5),
(6, 5);

INSERT INTO `privilege` (`id`, `name`, `pgroup`) VALUES
(1, 'appointment_day_apts', '1'),
(2, 'appointment_book_appointment', '1'),
(3, 'appointment_search', '1'),
(4, 'appointment_notification_to_patients', '1'),
(5, 'appointment_params', '1'),
(6, 'cache_clear', '1'),
(7, 'department_all', '1'),
(8, 'department_all_doctor_by_department', '1'),
(9, 'doctor_schedule', '1'),
(10, 'doctor_appointment_details', '1'),
(11, 'inventory_get_medicine_list', '2'),
(12, 'inventory_add_edit_medicine', '2'),
(13, 'inventory_send_report', '2'),
(14, 'patient_create_record', '2'),
(15, 'patient_view_record', '2'),
(16, 'user_registration', '2'),
(17, 'user_search', '2'),
(18, 'user_profile', '2'),
(19, 'user_visits', '2'),
(20, 'access_resources', '2');

INSERT INTO `role_privilege` (`role_id`, `privilege_id`) VALUES
(1, 3),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 14),
(1, 15),
(1, 17),
(1, 19),
(2, 1),
(2, 2),
(2, 3),
(2, 9),
(2, 10),
(2, 14),
(2, 15),
(2, 17),
(2, 18),
(2, 19),
(2, 20),
(3, 1),
(3, 2),
(3, 3),
(3, 7),
(3, 8),
(3, 9),
(3, 10),
(3, 15),
(3, 16),
(3, 17),
(3, 18),
(3, 19),
(3, 20),
(4, 11),
(4, 20),
(5, 3),
(5, 15),
(5, 18),
(5, 19),
(5, 20),
(6, 1),
(6, 2),
(6, 3),
(6, 4),
(6, 5),
(6, 6),
(6, 7),
(6, 8),
(6, 9),
(6, 10),
(6, 11),
(6, 12),
(6, 13),
(6, 14),
(6, 15),
(6, 16),
(6, 17),
(6, 18),
(6, 19),
(6, 20);


insert into app_settings (index_no,main_id,sub_id,language,param_name,param_value) VALUES
(1, '1','1','English','location','Oman'),
(2, '1','2','Hindi','location','India'),
(3, '1','1','English','Res_Root_Absolute','/data/tomcat/'),
(4, '1','1','English','Attachment_Dir_Location','/mohFiles/'),
(5, '1','1','English','DATE_FORMAT','yyyy-MM-dd'),
(6, '1','1','English','REPORT_DATE_FORMAT','dd-MMM-yyyy'),
(7, '1','1','English','PRESCRIPTION_DIR_DATE_FORMAT','yyyy/MM/dd'),
(8, '1','1','English','START_HOUR','7'),
(9, '1','1','English','END_HOUR','14'),
(10, '1','1','English','MAX_LANE_NUMBER','2'),
(11, '1','1','English','MAX_MINUTE_SLOT_NUMBER_IN_HOUR','6'),
(12, '1','1','English','DOWNLOAD_REPORT_PREFIX','att');

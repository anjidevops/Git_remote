-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 25, 2015 at 11:42 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mohStarter`
--

--
-- Truncate table before insert `Address`
--

TRUNCATE TABLE `Address`;
--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`Id`, `street1`, `street2`, `city`, `country`, `pincode`, `user_id`) VALUES
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
-- Truncate table before insert `Appointment`
--

TRUNCATE TABLE `Appointment`;
--
-- Dumping data for table `Appointment`
--

INSERT INTO `Appointment` (`Id`, `appointment_date`, `appointment_end_hour`, `appointment_end_min`, `doc_id`, `user_id`, `lanes`, `slot_status`) VALUES
(2, '2014-12-19 09:00:00', 9, 30, 2, 5, 1, NULL),
(3, '2014-12-19 10:30:00', 10, 55, 2, 6, 1, NULL),
(4, '2014-12-19 12:30:00', 12, 55, 3, 5, 1, NULL);

--
-- Truncate table before insert `Department`
--

TRUNCATE TABLE `Department`;
--
-- Dumping data for table `Department`
--

INSERT INTO `Department` (`dept_id`, `dept_desc`, `dept_name`) VALUES
(2, 'CARDIOLOGY description', 'CARDIOLOGY'),
(3, 'DENTAL AND ORAL SURGERY description', 'DENTAL');

--
-- Truncate table before insert `Doctor`
--

TRUNCATE TABLE `Doctor`;
--
-- Dumping data for table `Doctor`
--

INSERT INTO `Doctor` (`doc_id`, `doc_spec`, `reg_number`, `dept_dept_id`, `user_id`, `accepting_apts`, `lanes`) VALUES
(2, 'CARDIOLOGY', 1234, 2, 3, b'1', 1),
(3, 'DENTAL', 6789, 3, 4, b'0', 1);

--
-- Truncate table before insert `medicine_inventory`
--

TRUNCATE TABLE `medicine_inventory`;
--
-- Dumping data for table `medicine_inventory`
--

INSERT INTO `medicine_inventory` (`Id`, `available_quantity`, `medicine_name`, `thresold_quantity`) VALUES
(1, 50, 'كروسين', 100),
(2, 23, 'الباراسيتامول', 45);

--
-- Truncate table before insert `PatientRecord`
--

TRUNCATE TABLE `PatientRecord`;
--
-- Truncate table before insert `privilege`
--

TRUNCATE TABLE `privilege`;
--
-- Dumping data for table `privilege`
--

INSERT INTO `privilege` (`id`, `name`) VALUES
(1, 'appointment_day_apts'),
(2, 'appointment_book_appointment'),
(3, 'appointment_search'),
(4, 'appointment_notification_to_patients'),
(5, 'appointment_params'),
(6, 'cache_clear'),
(7, 'department_all'),
(8, 'department_all_doctor_by_department'),
(9, 'doctor_schedule'),
(10, 'doctor_appointment_details'),
(11, 'inventory_get_medicine_list'),
(12, 'inventory_add_edit_medicine'),
(13, 'inventory_send_report'),
(14, 'patient_create_record'),
(15, 'patient_view_record'),
(16, 'user_registration'),
(17, 'user_search'),
(18, 'user_profile'),
(19, 'user_visits');

--
-- Truncate table before insert `role`
--

TRUNCATE TABLE `role`;
--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'nurse'),
(2, 'doctor'),
(3, 'clerk_desk'),
(4, 'clerk_inventory'),
(5, 'patient'),
(6, 'admin'),
(7, 'r1'),
(8, 'R2');

--
-- Truncate table before insert `role_privilege`
--

TRUNCATE TABLE `role_privilege`;
--
-- Dumping data for table `role_privilege`
--

INSERT INTO `role_privilege` (`id`, `role_id`, `privilege_id`) VALUES
(1, 1, 3),
(2, 1, 9),
(3, 1, 10),
(4, 1, 11),
(5, 1, 12),
(6, 1, 14),
(7, 1, 15),
(8, 1, 17),
(9, 1, 19),
(10, 2, 1),
(11, 2, 2),
(12, 2, 3),
(13, 2, 9),
(14, 2, 10),
(15, 2, 14),
(16, 2, 15),
(17, 2, 17),
(18, 2, 18),
(19, 2, 19),
(20, 3, 1),
(21, 3, 2),
(22, 3, 3),
(23, 3, 7),
(24, 3, 8),
(25, 3, 9),
(26, 3, 10),
(27, 3, 15),
(28, 3, 16),
(29, 3, 17),
(30, 3, 18),
(31, 3, 19),
(32, 4, 11),
(33, 5, 3),
(34, 5, 15),
(35, 5, 18),
(36, 5, 19),
(37, 6, 1),
(38, 6, 2),
(39, 6, 3),
(40, 6, 4),
(41, 6, 5),
(42, 6, 6),
(43, 6, 7),
(44, 6, 8),
(45, 6, 9),
(46, 6, 10),
(47, 6, 11),
(48, 6, 12),
(49, 6, 13),
(50, 6, 14),
(51, 6, 15),
(52, 6, 16),
(53, 6, 17),
(54, 6, 18),
(55, 6, 19),
(56, 7, 1),
(59, 7, 2),
(60, 8, 1),
(61, 8, 6),
(62, 8, 7);

--
-- Truncate table before insert `User`
--

TRUNCATE TABLE `User`;
--
-- Dumping data for table `User`
--

INSERT INTO `User` (`Id`, `fname`, `lname`, `dob`, `email`, `mobile_code`, `mobile`, `rolename`, `password`, `gender`, `accept_terms_condition`) VALUES
(2, 'admin', 'admin', '2014-11-28 16:13:57', 'admin@gmail.com', 91, '12345789', 'Admin', 'moh', NULL, b'1'),
(3, 'doctor1', 'doctor1', '2014-12-15 00:00:00', 'doctor1@gmail.com', 91, '0456789', 'Doctor', 'moh', NULL, b'1'),
(4, 'doctor2', 'doctor2', '2014-12-15 00:00:00', 'doctor2@gmail.com', 91, '085246', 'Doctor', 'moh', NULL, b'1'),
(5, 'Patient1', 'Patient1', '2014-12-17 00:00:00', 'patient1@gmail.com', 91, '12345', 'Patient', 'moh', NULL, b'1'),
(6, 'Patient2', 'Patient2', '2014-12-16 00:00:00', 'patient2@gmail.com', 91, '56789', 'Patient', 'moh', NULL, b'1');

--
-- Truncate table before insert `user_role`
--

TRUNCATE TABLE `user_role`;
--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
(1, 2, 6),
(2, 3, 2),
(3, 4, 2),
(4, 5, 5),
(5, 6, 8);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

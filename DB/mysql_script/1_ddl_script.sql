--
-- Host: localhost
-- Generation Time: Dec 04, 2014 at 09:05 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

SET time_zone = "+00:00";


--
-- Database: `mohStarter`
--
-- Drop might not work in phpmyadmin - run from console or remove disable drop from config
-- Drop data base if exists mohStarter;

CREATE DATABASE IF NOT EXISTS `mohStarter` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `mohStarter`;


-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;

CREATE TABLE IF NOT EXISTS `Address` (
`Id` int(11) NOT NULL,
  `street1` varchar(255) DEFAULT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;


--
-- Table structure for table `Appointment`
--

DROP TABLE IF EXISTS `Appointment`;

CREATE TABLE IF NOT EXISTS `Appointment` (
`Id` int(11) NOT NULL,
  `appointment_date` datetime DEFAULT NULL,
  `appointment_end_hour` int(11) DEFAULT NULL,
  `appointment_end_min` int(11) DEFAULT NULL,
  `doc_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `lanes` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;


--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;

CREATE TABLE IF NOT EXISTS `Department` (
`dept_id` int(11) NOT NULL,
  `dept_desc` varchar(255) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;


--
-- Table structure for table `Doctor`
--

DROP TABLE IF EXISTS `Doctor`;

CREATE TABLE IF NOT EXISTS `Doctor` (
`doc_id` int(11) NOT NULL,
  `doc_spec` varchar(255) DEFAULT NULL,
  `reg_number` int(11) DEFAULT NULL,
  `dept_dept_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `accepting_apts` bit(1) DEFAULT NULL,
  `lanes` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;


--
-- Table structure for table `medicine_inventory`
--

DROP TABLE IF EXISTS `medicine_inventory`;

CREATE TABLE IF NOT EXISTS `medicine_inventory` (
`Id` int(11) NOT NULL,
  `available_quantity` int(11) DEFAULT NULL,
  `medicine_name` varchar(255) DEFAULT NULL,
  `thresold_quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;


--
-- Table structure for table `PatientRecord`
--

DROP TABLE IF EXISTS `PatientRecord`;

CREATE TABLE IF NOT EXISTS `PatientRecord` (
`Id` int(11) NOT NULL,
  `advise_admit` bit(1)  NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `attach1` varchar(255),
  `attach2` varchar(255),
  `prescription` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;


--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;

CREATE TABLE IF NOT EXISTS `User` (
`Id` int(11) NOT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_code` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` ENUM('MALE','FEMALE') DEFAULT NULL,
   `accept_terms_condition` bit(1) DEFAULT 1  
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;




--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
 ADD PRIMARY KEY (`Id`), ADD KEY `FK1ED033D4C9467BA0` (`user_id`);


--
-- Indexes for table `Appointment`
--
ALTER TABLE `Appointment`
 ADD PRIMARY KEY (`Id`), ADD KEY `FKB7F037F11121AE7` (`doc_id`), ADD KEY `FKB7F037FC9467BA0` (`user_id`);


--
-- Indexes for table `Department`
--
ALTER TABLE `Department`
 ADD PRIMARY KEY (`dept_id`), ADD UNIQUE KEY `dept_name` (`dept_name`);


--
-- Indexes for table `Doctor`
--
ALTER TABLE `Doctor`
 ADD PRIMARY KEY (`doc_id`), ADD UNIQUE KEY `reg_number` (`reg_number`), ADD KEY `FK7A547D3FC9467BA0` (`user_id`), ADD KEY `FK7A547D3FC2B399F3` (`dept_dept_id`);


--
-- Indexes for table `medicine_inventory`
--
ALTER TABLE `medicine_inventory`
 ADD PRIMARY KEY (`Id`);


--
-- Indexes for table `PatientRecord`
--
ALTER TABLE `PatientRecord`
 ADD PRIMARY KEY (`Id`);


--
-- Indexes for table `User`
--
ALTER TABLE `User`
 ADD PRIMARY KEY (`Id`), ADD UNIQUE KEY `email` (`email`), ADD UNIQUE KEY `mobile` (`mobile`);


--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Address`
--
ALTER TABLE `Address`
MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Appointment`
--
ALTER TABLE `Appointment`
MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Department`
--
ALTER TABLE `Department`
MODIFY `dept_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Doctor`
--
ALTER TABLE `Doctor`
MODIFY `doc_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `medicine_inventory`
--
ALTER TABLE `medicine_inventory`
MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `PatientRecord`
--
ALTER TABLE `PatientRecord`
MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Address`
--
ALTER TABLE `Address`
ADD CONSTRAINT `FK1ED033D4C9467BA0` FOREIGN KEY (`user_id`) REFERENCES `User` (`Id`);


--
-- Constraints for table `Appointment`
--
ALTER TABLE `Appointment`
ADD CONSTRAINT `FKB7F037F11121AE7` FOREIGN KEY (`doc_id`) REFERENCES `Doctor` (`doc_id`),
ADD CONSTRAINT `FKB7F037FC9467BA0` FOREIGN KEY (`user_id`) REFERENCES `User` (`Id`);


--
-- Constraints for table `Doctor`
--
ALTER TABLE `Doctor`
ADD CONSTRAINT `FK7A547D3FC2B399F3` FOREIGN KEY (`dept_dept_id`) REFERENCES `Department` (`dept_id`),
ADD CONSTRAINT `FK7A547D3FC9467BA0` FOREIGN KEY (`user_id`) REFERENCES `User` (`Id`);

ALTER TABLE Appointment ADD COLUMN `slot_status` varchar(255);

CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(245) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_role (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE privilege (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(245) NOT NULL,
  pgroup varchar(10) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE role_privilege (
  id int(11) NOT NULL AUTO_INCREMENT,
  role_id int(11) NOT NULL,
  privilege_id int(11) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE app_settings (
  `index_no` int(11) NOT NULL,
  `main_id` varchar(255) DEFAULT NULL,
  `sub_id` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `param_name` varchar(255) DEFAULT NULL,
  `param_value` varchar(255) DEFAULT NULL,
   PRIMARY KEY (index_no)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2025 at 11:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carepoint`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Admin_ID` int(11) NOT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Admin_ID`, `Username`, `Password`, `Email`) VALUES
(1, 'janith', '111', 'janith@gmail.com'),
(2, 'mithy', '0', 'm@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `Appointment_ID` int(11) NOT NULL,
  `Patient_ID` int(11) DEFAULT NULL,
  `Availability_ID` int(11) DEFAULT NULL,
  `Total_Charge` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `availability`
--

CREATE TABLE `availability` (
  `Availability_ID` int(11) NOT NULL,
  `Doctor_ID` int(11) DEFAULT NULL,
  `Hospital_ID` int(11) DEFAULT NULL,
  `Available_Date` date DEFAULT NULL,
  `Available_Time` time DEFAULT NULL,
  `Is_Booked_Status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `availability`
--

INSERT INTO `availability` (`Availability_ID`, `Doctor_ID`, `Hospital_ID`, `Available_Date`, `Available_Time`, `Is_Booked_Status`) VALUES
(1, 1, 1, '2025-01-02', '08:00:00', 'Available'),
(2, 1, 1, '2025-01-02', '09:00:00', 'Available'),
(3, 1, 6, '2025-01-03', '10:00:00', 'Available'),
(4, 1, 2, '2025-01-04', '08:00:00', 'Available'),
(5, 1, 2, '2025-01-05', '09:00:00', 'Available'),
(6, 1, 2, '2025-01-05', '10:00:00', 'Available'),
(7, 1, 3, '2025-01-06', '11:00:00', 'Available'),
(8, 1, 3, '2025-01-06', '12:00:00', 'Available'),
(9, 1, 4, '2025-01-07', '08:00:00', 'Available'),
(10, 1, 4, '2025-01-07', '09:00:00', 'Available'),
(11, 1, 5, '2025-01-08', '10:00:00', 'Available'),
(12, 2, 1, '2025-01-02', '11:00:00', 'Available'),
(13, 2, 1, '2025-01-02', '12:00:00', 'Available'),
(14, 2, 2, '2025-01-03', '13:00:00', 'Available'),
(15, 2, 2, '2025-01-04', '08:00:00', 'Available'),
(16, 2, 3, '2025-01-05', '09:00:00', 'Available'),
(17, 2, 3, '2025-01-05', '10:00:00', 'Available'),
(18, 2, 4, '2025-01-06', '11:00:00', 'Available'),
(19, 2, 4, '2025-01-06', '12:00:00', 'Available'),
(20, 2, 5, '2025-01-07', '08:00:00', 'Available'),
(21, 2, 5, '2025-01-08', '09:00:00', 'Available'),
(22, 3, 1, '2025-01-02', '08:30:00', 'Available'),
(23, 3, 1, '2025-01-02', '09:30:00', 'Available'),
(24, 3, 1, '2025-01-03', '10:30:00', 'Available'),
(25, 3, 2, '2025-01-04', '11:30:00', 'Available'),
(26, 3, 2, '2025-01-04', '12:30:00', 'Available'),
(27, 3, 3, '2025-01-05', '13:30:00', 'Available'),
(28, 3, 3, '2025-01-05', '14:30:00', 'Available'),
(29, 3, 4, '2025-01-06', '15:30:00', 'Available'),
(30, 3, 4, '2025-01-06', '16:30:00', 'Available'),
(31, 3, 5, '2025-01-07', '08:30:00', 'Available'),
(32, 4, 1, '2025-01-03', '10:00:00', 'Available'),
(33, 4, 1, '2025-01-03', '11:00:00', 'Available'),
(34, 4, 2, '2025-01-04', '12:00:00', 'Available'),
(35, 4, 2, '2025-01-05', '13:00:00', 'Available'),
(36, 4, 3, '2025-01-06', '14:00:00', 'Available'),
(37, 4, 3, '2025-01-07', '15:00:00', 'Available'),
(38, 4, 4, '2025-01-08', '08:00:00', 'Available'),
(39, 4, 4, '2025-01-08', '09:00:00', 'Available'),
(40, 4, 5, '2025-01-09', '10:00:00', 'Available'),
(41, 5, 1, '2025-01-02', '08:00:00', 'Available'),
(42, 5, 1, '2025-01-03', '09:00:00', 'Available'),
(43, 5, 2, '2025-01-04', '10:00:00', 'Available'),
(44, 5, 2, '2025-01-05', '11:00:00', 'Available'),
(45, 5, 3, '2025-01-06', '12:00:00', 'Available'),
(46, 5, 3, '2025-01-07', '13:00:00', 'Available'),
(47, 5, 4, '2025-01-08', '14:00:00', 'Available'),
(48, 5, 4, '2025-01-09', '15:00:00', 'Available'),
(49, 5, 5, '2025-01-10', '16:00:00', 'Available'),
(50, 6, 1, '2025-01-02', '09:00:00', 'Available'),
(51, 6, 1, '2025-01-03', '10:00:00', 'Available'),
(52, 6, 2, '2025-01-04', '11:00:00', 'Available'),
(53, 6, 2, '2025-01-05', '12:00:00', 'Available'),
(54, 6, 3, '2025-01-06', '13:00:00', 'Available'),
(55, 6, 3, '2025-01-07', '14:00:00', 'Available'),
(56, 6, 4, '2025-01-08', '15:00:00', 'Available'),
(57, 6, 4, '2025-01-09', '16:00:00', 'Available'),
(58, 6, 5, '2025-01-10', '08:00:00', 'Available'),
(59, 7, 7, '2025-02-21', '08:00:10', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Doctor_ID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Specialization` varchar(100) DEFAULT NULL,
  `Contact_Info` varchar(255) DEFAULT NULL,
  `Doctor_Charge` decimal(10,2) DEFAULT NULL,
  `Hospital_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Doctor_ID`, `Name`, `Specialization`, `Contact_Info`, `Doctor_Charge`, `Hospital_ID`) VALUES
(1, 'Dr. Alice Smith', 'Cardiology', 'alice.smith@gmail.com', 150.00, NULL),
(2, 'Dr. Bob Jones', 'Neurology', 'bob.jones@gmail.com', 200.00, NULL),
(3, 'Dr. Carol Lee', 'Pediatrics', 'carol.lee@gmail.com', 120.00, NULL),
(4, 'Dr. David White', 'Orthopedics', 'david.white@gmail.com', 180.00, NULL),
(5, 'Dr. Emma Green', 'Dermatology', 'emma.green@gmail.com', 160.00, NULL),
(6, 'Dr. Frank Brown', 'General Surgery', 'frank.brown@gmail.com', 250.00, NULL),
(7, 'chanaka1', 'hello', 'cha@gmail.com', 3000.00, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `Hospital_ID` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `Contact_Info` varchar(255) DEFAULT NULL,
  `Hospital_Charge` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`Hospital_ID`, `Name`, `Location`, `Contact_Info`, `Hospital_Charge`) VALUES
(1, 'City Hospital', '123 Main St, Cityville', 'contact@gmail.com', 50.00),
(2, 'Metro Hospital', '456 Elm St, Metropolis', 'info@gmail.com', 60.00),
(3, 'River Hospital', '789 River Rd, Riverside', 'support@gmail.com', 55.00),
(4, 'Hill Hospital', '101 Hilltop Ave, Hilltown', 'services@gmail.com', 65.00),
(5, 'Valley Hospital', '202 Valley St, Valleyview', 'valley@gmail.com', 70.00),
(6, 'New Life Hospital', '789 Sunset Blvd, Sunnyside', 'info@gmail.com', 75.00),
(7, 'city hospital1', 'kandy', 'city@gmail.com', 1000.00);

-- --------------------------------------------------------

--
-- Table structure for table `ongoingtable`
--

CREATE TABLE `ongoingtable` (
  `Patient_ID` int(11) NOT NULL,
  `Number` varchar(50) NOT NULL,
  `Doctor` varchar(100) DEFAULT NULL,
  `Hospital` varchar(100) DEFAULT NULL,
  `Specialization` varchar(100) DEFAULT NULL,
  `Date` varchar(20) DEFAULT NULL,
  `Time` varchar(20) DEFAULT NULL,
  `Charge` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `Patient_ID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` varchar(50) NOT NULL,
  `Phone_Number` varchar(15) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`Patient_ID`, `Name`, `Age`, `Gender`, `Phone_Number`, `Email`, `Password`, `Address`) VALUES
(1, 'Janith', 21, 'Male', '0778370543', 'janith@gmail.com', '123', '123 Main St'),
(2, 'Jane Smith', 28, 'Female', '9876543210', 'jane.smith@gmail.com', 'password123', '456 Elm St'),
(3, 'Alice Brown', 25, 'Female', '1112223334', 'alice.brown@gmail.com', 'password123', '789 Maple St'),
(4, 'Bob White', 35, 'Male', '2223334445', 'bob.white@gmail.com', 'password123', '321 Oak St'),
(5, 'Emily Davis', 40, 'Female', '3334445556', 'emily.davis@gmail.com', 'password123', '654 Pine St'),
(6, 'Chris Green', 29, 'Male', '4445556667', 'chris.green@gmail.com', 'password123', '987 Cedar St'),
(7, 'Sophia Lee', 32, 'Female', '5556667778', 'sophia.lee@gmail.com', 'password123', '159 Birch St'),
(8, 'Michael Brown', 31, 'Male', '6667778889', 'michael.brown@gmail.com', 'password123', '951 Spruce St'),
(9, 'Olivia Johnson', 27, 'Female', '7778889990', 'olivia.johnson@gmail.com', 'password123', '753 Cherry St'),
(10, 'David Clark', 33, 'Male', '8889990001', 'david.clark@gmail.com', 'password123', '357 Walnut St'),
(12, 'Mithinsha', 22, 'Female', '0776720242', 'mithinsha@gmail.com', '444', 'kandy, srilanka'),
(13, 'gdgzg', 44, 'Male', '24244', 'tyujty', 'yjytjy', 'ewtaeg'),
(14, 'lol', 22, 'Male', '245', 'dsd', '555', 'dsad'),
(15, 'hihihihi', 1, 'Male', '0214', 'hi@gmail.com', '333', 'svsvsv'),
(17, 'ghagykjdkdjdkd', 25, 'Male', '125698745', 'hghadgh@gmail.com', '124578', 'hjgKJSjJhjshJKhcjkSHCJKh'),
(18, 'CITY', 4, 'Male', '457852', 'city@gmail.com', '12', 'dsadgAHGDHGa'),
(19, 'dhsjkhdj', 54, 'Male', '02588', 'sdhuh@gmail.com', '1', 'jdhajskhjksahjk'),
(20, 'Paboda', 22, 'Male', '4587', 'paboda@gmail.com', '0', 'dghsafgf'),
(21, 'Senura', 75, 'Male', '0757857845', 'senura@gmail.com', '0', 'Dambadeniya'),
(22, 'User', 21, 'Male', '0776720242', 'user1@gmail.com', '0', 'Colombo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Admin_ID`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`Appointment_ID`),
  ADD KEY `Patient_ID` (`Patient_ID`),
  ADD KEY `Availability_ID` (`Availability_ID`);

--
-- Indexes for table `availability`
--
ALTER TABLE `availability`
  ADD PRIMARY KEY (`Availability_ID`),
  ADD KEY `Doctor_ID` (`Doctor_ID`),
  ADD KEY `Hospital_ID` (`Hospital_ID`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`Doctor_ID`),
  ADD KEY `Hospital_ID` (`Hospital_ID`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`Hospital_ID`);

--
-- Indexes for table `ongoingtable`
--
ALTER TABLE `ongoingtable`
  ADD PRIMARY KEY (`Patient_ID`,`Number`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`Patient_ID`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Email_2` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `Admin_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `Appointment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `availability`
--
ALTER TABLE `availability`
  MODIFY `Availability_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `Doctor_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `Hospital_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `Patient_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`Availability_ID`) REFERENCES `availability` (`Availability_ID`) ON DELETE CASCADE;

--
-- Constraints for table `availability`
--
ALTER TABLE `availability`
  ADD CONSTRAINT `availability_ibfk_1` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctor` (`Doctor_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `availability_ibfk_2` FOREIGN KEY (`Hospital_ID`) REFERENCES `hospital` (`Hospital_ID`) ON DELETE CASCADE;

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`Hospital_ID`) REFERENCES `hospital` (`Hospital_ID`) ON DELETE CASCADE;

--
-- Constraints for table `ongoingtable`
--
ALTER TABLE `ongoingtable`
  ADD CONSTRAINT `ongoingtable_ibfk_1` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

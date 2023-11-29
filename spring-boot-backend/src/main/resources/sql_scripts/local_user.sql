-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2023 at 12:45 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `local_user`
--

CREATE TABLE `local_user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `passwrd` varchar(1000) NOT NULL,
  `username` text NOT NULL,
  `fk_addresses_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `local_user`
--

INSERT INTO `local_user` (`id`, `email`, `first_name`, `passwrd`, `username`, `fk_addresses_id`) VALUES
(2, 'xxx@gmail.com', 'ewa', '3443a', 'waea', 2),
(3, 'xxx@gmail.com', 'uyuyguy', 'aseifjeoij56', 'aseiufe', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `local_user`
--
ALTER TABLE `local_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_f121vy4ijiyb7lqtjmba3eks9` (`fk_addresses_id`),
  ADD UNIQUE KEY `UK_93d93k106ik2383youkc9bixl` (`username`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `local_user`
--
ALTER TABLE `local_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `local_user`
--
ALTER TABLE `local_user`
  ADD CONSTRAINT `FK92gew46hc036bx257xkihf52r` FOREIGN KEY (`fk_addresses_id`) REFERENCES `user_address` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

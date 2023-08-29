-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2023 at 06:07 PM
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
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('ashot', 'ROLE_ADMIN'),
('ashot', 'ROLE_CUSTOMER'),
('jason', 'ROLE_CUSTOMER'),
('susan', 'ROLE_CUSTOMER');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` bigint(20) NOT NULL,
  `imagedata` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `imagedata`, `product_id`) VALUES
(1, 'opeth-damnation.png', 4),
(2, 'guy.png', 3),
(3, 'album_image.png', 2),
(4, 'tool-Ænima.png', 1),
(5, 'ab67706c0000da84ed6492dab6294d6b1194abb4.jpg', 37),
(6, '1971.jpg', 14);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `quantity`) VALUES
(1, 23),
(2, 24),
(3, 2),
(4, 4),
(5, 45),
(49, 4),
(50, 2),
(51, 24),
(52, 23),
(53, 234626426),
(54, 45);

-- --------------------------------------------------------

--
-- Table structure for table `local_user`
--

CREATE TABLE `local_user` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `passwrd` varchar(1000) NOT NULL,
  `username` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `fk_user_id` bigint(20) NOT NULL,
  `payment_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment_details`
--

CREATE TABLE `payment_details` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `artist` varchar(255) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `genre` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `year` int(11) NOT NULL,
  `fk_inventory_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `artist`, `description`, `genre`, `name`, `price`, `year`, `fk_inventory_id`) VALUES
(1, 'Tool', 'Ænima is the second studio album by American rock band Tool.', 'Alternative Metal', 'Ænima', 45, 1996, 52),
(2, 'Tool', 'Lateralus is the third studio album by American rock band Tool.', 'Alternative Metal', 'Lateralus', 30, 2001, 51),
(3, 'Blind Guardian', 'Imaginations from the Other Side is the fifth studio album by German power metal band Blind Guardian.', 'Power Metal', 'Imaginations from the Other Side', 25, 1995, 50),
(4, 'Opeth', 'Damnation is the seventh studio album by Swedish progressive metal band Opeth.', 'Progressive Rock', 'Damnation', 30, 2003, 49),
(14, 'Black Sabbath', 'Master Of Reality is the third studio album by British band Black Sabbath', 'Heavy Metal', 'Master Of Reality ', 55, 1971, 54),
(37, 'ARaraewrarewarewarewarewarewarearewarerewar', 'stersetrsetrsterters', 'ssersetrstersters', 'wqQWWQRwqWQwqrRWQRWQRAWWERAARARARREWA', 2662626, 1999, 53);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('ashot', '{noop}5646', 1),
('jason', '{noop}1234', 1),
('susan', '{noop}sussy', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_address`
--

CREATE TABLE `user_address` (
  `id` bigint(20) NOT NULL,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(80) NOT NULL,
  `postal_code` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_payment`
--

CREATE TABLE `user_payment` (
  `id` bigint(20) NOT NULL,
  `account_number` bigint(20) NOT NULL,
  `type` varchar(15) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jvinkc7xcd0x0pk49c1me6hb6` (`product_id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `local_user`
--
ALTER TABLE `local_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_93d93k106ik2383youkc9bixl` (`username`) USING HASH;

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_k8iuy7qvocndeisnqjms9c82d` (`payment_id`),
  ADD KEY `FK22470nsqgceouhmgf1tpgxui7` (`fk_user_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfaco7kgw6uoepp39m74cy7j6o` (`order_id`),
  ADD KEY `FKlf6f9q956mt144wiv6p1yko16` (`product_id`);

--
-- Indexes for table `payment_details`
--
ALTER TABLE `payment_details`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_of2hvjrt3h42uja5aibie81tp` (`order_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_product_name` (`name`),
  ADD UNIQUE KEY `UK_6usmrien8b8s6utl0sjuil6lg` (`fk_inventory_id`);

--
-- Indexes for table `user_address`
--
ALTER TABLE `user_address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1h2ccyqwrv3l9sdgdiceutii1` (`user_id`);

--
-- Indexes for table `user_payment`
--
ALTER TABLE `user_payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkugnevoqb5xiqktiy0grou3rj` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `local_user`
--
ALTER TABLE `local_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment_details`
--
ALTER TABLE `payment_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `user_address`
--
ALTER TABLE `user_address`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_payment`
--
ALTER TABLE `user_payment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `FK8sfun6tj1hqb85ed52o8mkqyh` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `FK22470nsqgceouhmgf1tpgxui7` FOREIGN KEY (`fk_user_id`) REFERENCES `local_user` (`id`),
  ADD CONSTRAINT `FKe2t35c1i6f93nhvxbru3wy4s5` FOREIGN KEY (`payment_id`) REFERENCES `payment_details` (`id`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKfaco7kgw6uoepp39m74cy7j6o` FOREIGN KEY (`order_id`) REFERENCES `order_details` (`id`),
  ADD CONSTRAINT `FKlf6f9q956mt144wiv6p1yko16` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `payment_details`
--
ALTER TABLE `payment_details`
  ADD CONSTRAINT `FKqnay946png0id4ie8oxe6ec65` FOREIGN KEY (`order_id`) REFERENCES `order_details` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FKmurxufobr1g1qojpqkrqduk6n` FOREIGN KEY (`fk_inventory_id`) REFERENCES `inventory` (`id`);

--
-- Constraints for table `user_address`
--
ALTER TABLE `user_address`
  ADD CONSTRAINT `FK1h2ccyqwrv3l9sdgdiceutii1` FOREIGN KEY (`user_id`) REFERENCES `local_user` (`id`);

--
-- Constraints for table `user_payment`
--
ALTER TABLE `user_payment`
  ADD CONSTRAINT `FKkugnevoqb5xiqktiy0grou3rj` FOREIGN KEY (`user_id`) REFERENCES `local_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 07-Jul-2018 às 22:55
-- Versão do servidor: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdcadastro`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `controlef_saida`
--

CREATE TABLE `controlef_saida` (
  `id` int(100) NOT NULL,
  `grad_posto` varchar(50) NOT NULL,
  `nome_guerra` varchar(100) NOT NULL,
  `sessao` varchar(50) NOT NULL,
  `data` varchar(50) NOT NULL,
  `hora_entrada` varchar(50) NOT NULL,
  `hora_saida` varchar(50) DEFAULT NULL,
  `motivo_entrada` varchar(50) NOT NULL,
  `imagem` varbinary(10000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `controlef_saida`
--
ALTER TABLE `controlef_saida`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `controlef_saida`
--
ALTER TABLE `controlef_saida`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

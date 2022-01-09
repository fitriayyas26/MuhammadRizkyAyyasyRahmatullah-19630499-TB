-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 09 Jan 2022 pada 13.08
-- Versi server: 10.4.13-MariaDB
-- Versi PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uaspbo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `jabatan`
--

CREATE TABLE `jabatan` (
  `ID` varchar(4) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `gajih` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jabatan`
--

INSERT INTO `jabatan` (`ID`, `jabatan`, `gajih`) VALUES
('D022', 'Staf manager', 2000000),
('D087', 'Direktur', 5000000),
('D089', 'Staf Direktur 1', 2500000),
('D098', 'Manager', 3500000),
('D476', 'Staf Administrasi', 2000000),
('D490', 'Admin', 2500000),
('D586', 'Direktur 1', 2000000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `nik` varchar(16) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jabatan` varchar(30) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `telpon` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`nik`, `nama`, `jabatan`, `alamat`, `telpon`, `email`) VALUES
('6213456787654321', 'Budi ', 'Direktur', 'Banjarbaru', '0812', 'budi@gmail.com'),
('6306024308000003', 'Fitri Aida', 'Manager', 'Jln kamboja', '082117202493', 'fitriayyas78@gmail.com'),
('6306541329860000', 'Rizky Ayyas', 'Direktur', 'Kandangan', '082109865543', 'rizkyayyasy@gmail.com'),
('6307541329860010', 'Fahmi AH', 'Staf Manager', 'Jln Guntung manggis', '082165342210', 'fahmiah@gmail.com'),
('63123456789', 'Dudung Wahyudi', 'Admin', 'Banjarbaru', '0821', 'dudung@gmail.com'),
('6507541329860077', 'Roni Wijaya', 'Staf Administrasi', 'Jln Padang batung', '087765342210', 'roniwijaya@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `nama` varchar(100) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `id` varchar(4) NOT NULL,
  `gajih` int(15) NOT NULL,
  `lembur` longtext NOT NULL,
  `uanglembur` int(15) NOT NULL,
  `total` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`nama`, `jabatan`, `id`, `gajih`, `lembur`, `uanglembur`, `total`) VALUES
('ayyas', 'Manager', 'D098', 3500000, '5 jam', 250000, 3750000),
('Dudung', 'Admin', 'D490', 2500000, '5 jam', 250000, 2750000),
('elias', 'Direktur', 'D087', 5000000, '3 jam', 150000, 5150000),
('Fariz', 'Staf Manager', 'D022', 2000000, '4 jam', 200000, 2200000),
('fitri', 'Staf Administrasi', 'D087', 5000000, '1 jam', 50000, 5050000),
('Nurul', 'Direktur', 'D087', 5000000, '2 jam', 100000, 5100000),
('sukma', 'Staf Manager', 'D022', 2000000, '5 jam', 250000, 2250000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`ID`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`nik`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`nama`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

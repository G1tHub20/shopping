-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2022-10-06 04:10:52
-- サーバのバージョン： 8.0.26
-- PHP のバージョン: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `shopping_new`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `history`
--

CREATE TABLE `history` (
  `id` int NOT NULL COMMENT '注文ID',
  `user_id` int NOT NULL COMMENT 'ユーザーID※外部キー',
  `item_id` int NOT NULL COMMENT '商品ID※外部キー',
  `item_price` int UNSIGNED NOT NULL COMMENT '価格（注文時点）',
  `order_num` int UNSIGNED NOT NULL COMMENT '注文数',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注文日時'
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- テーブルのデータのダンプ `history`
--

INSERT INTO `history` (`id`, `user_id`, `item_id`, `item_price`, `order_num`, `order_date`) VALUES
(1, 2, 1, 2900, 2, '2022-05-01 11:50:55'),
(2, 2, 2, 2000, 9, '2022-05-02 17:30:46'),
(3, 2, 2, 2000, 2, '2022-05-10 14:17:47'),
(4, 2, 2, 2000, 5, '2022-05-10 14:44:16'),
(5, 2, 1, 2900, 5, '2022-05-10 14:47:33'),
(6, 2, 2, 2000, 5, '2022-05-10 14:56:51'),
(7, 2, 2, 2000, 5, '2022-05-10 14:57:36'),
(8, 2, 2, 2000, 5, '2022-05-10 17:09:09'),
(9, 2, 2, 2000, 1, '2022-05-10 17:52:08'),
(10, 2, 1, 2900, 1, '2022-05-10 17:52:08'),
(11, 2, 1, 2900, 2, '2022-05-10 19:55:24'),
(12, 3, 2, 2000, 2, '2022-05-10 20:06:15'),
(13, 3, 1, 2900, 1, '2022-05-10 20:06:15'),
(14, 3, 2, 2000, 1, '2022-05-10 20:07:32'),
(15, 3, 1, 2900, 1, '2022-05-10 20:15:21'),
(38, 13, 7, 30000, 1, '2022-05-12 20:06:45'),
(39, 13, 7, 30000, 1, '2022-05-12 20:09:07'),
(40, 13, 7, 30000, 1, '2022-05-12 20:11:05'),
(41, 13, 7, 30000, 1, '2022-05-12 20:15:40'),
(42, 14, 7, 30000, 1, '2022-05-12 20:18:25'),
(43, 2, 7, 30000, 2, '2022-10-03 13:55:32'),
(44, 2, 7, 30000, 1, '2022-10-04 16:03:22'),
(45, 2, 4, 50000, 2, '2022-10-04 16:06:07'),
(46, 2, 7, 30000, 2, '2022-10-06 10:00:06'),
(47, 2, 2, 1800, 1, '2022-10-06 10:00:06'),
(48, 12, 6, 4000, 1, '2022-10-06 10:01:28');

-- --------------------------------------------------------

--
-- テーブルの構造 `item`
--

CREATE TABLE `item` (
  `id` int(4) UNSIGNED ZEROFILL NOT NULL COMMENT '商品ID　※ゼロ埋め',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名',
  `type` varchar(3) NOT NULL DEFAULT '1' COMMENT 'カテゴリ',
  `price` int UNSIGNED NOT NULL COMMENT '価格',
  `stock` int UNSIGNED NOT NULL DEFAULT '0' COMMENT '在庫数',
  `image` varchar(30) DEFAULT '''none.jpg''' COMMENT '商品画像のファイル名',
  `state` int NOT NULL DEFAULT '1' COMMENT '販売状態',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成（追加）日'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- テーブルのデータのダンプ `item`
--

INSERT INTO `item` (`id`, `name`, `type`, `price`, `stock`, `image`, `state`, `created_at`) VALUES
(0001, 'シンプルネクタイ', 'tie', 2900, 5, 'tie0001.jpg', 1, '2022-05-01 11:17:31'),
(0002, 'ワンカラーネクタイ', 'tie', 1800, 1, 'tie0002.jpg', 1, '2022-05-01 11:20:06'),
(0003, '白ネクタイ', 'tie', 1500, 5, 'tie0003.jpg', 0, '2022-05-12 13:17:00'),
(0004, 'ブラウン長財布', 'wal', 50000, 5, 'wal0001.jpg', 1, '2022-05-12 13:18:00'),
(0005, 'スリム二つ折り財布', 'wal', 17000, 5, 'wal0003.jpg', 1, '2022-05-12 13:19:00'),
(0006, 'シンプルアナログ時計', 'wat', 4000, 5, 'wat0001.jpg', 1, '2022-05-12 13:21:00'),
(0007, '多機能スマートウォッチ', 'wat', 30000, 5, 'wat0003.jpg', 1, '2022-05-12 13:22:00');

-- --------------------------------------------------------

--
-- テーブルの構造 `user`
--

CREATE TABLE `user` (
  `id` int(4) UNSIGNED ZEROFILL NOT NULL COMMENT 'ユーザーID',
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ユーザー名',
  `pass` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'パスワード'
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- テーブルのデータのダンプ `user`
--

INSERT INTO `user` (`id`, `userName`, `pass`) VALUES
(0001, 'administrator', '441295077183251181168118214218252251407020126131146147'),
(0002, 'user0001', '712302345518119523175169173208162107102272116628238'),
(0003, 'user0002', '692510597211154211511814470482531587216423225023073'),
(0012, 'user0003', '6249129253136186446824641791922303716325424620819534'),
(0036, 'user2001', '83268551617718717325114524391823146183221251153');

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `item_id` (`item_id`);

--
-- テーブルのインデックス `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`);

--
-- テーブルのインデックス `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_name` (`userName`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `history`
--
ALTER TABLE `history`
  MODIFY `id` int NOT NULL AUTO_INCREMENT COMMENT '注文ID', AUTO_INCREMENT=49;

--
-- テーブルの AUTO_INCREMENT `item`
--
ALTER TABLE `item`
  MODIFY `id` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '商品ID　※ゼロ埋め', AUTO_INCREMENT=8;

--
-- テーブルの AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'ユーザーID', AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

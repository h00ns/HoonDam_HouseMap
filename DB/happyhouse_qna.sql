CREATE TABLE `qna` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  `writer` varchar(45) NOT NULL,
  `isprivate` varchar(45) NOT NULL DEFAULT 'false',
  `answer` varchar(45) DEFAULT NULL,
  `writetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `completed` varchar(45) DEFAULT 'false',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
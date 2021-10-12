CREATE TABLE `comment` (
  `idcomment` int NOT NULL AUTO_INCREMENT,
  `aptno` int NOT NULL,
  `writer` varchar(45) NOT NULL,
  `content` varchar(45) NOT NULL,
  PRIMARY KEY (`idcomment`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
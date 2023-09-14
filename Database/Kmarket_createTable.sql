
CREATE SCHEMA IF NOT EXISTS `Kmarket` DEFAULT CHARACTER SET utf8 ;
USE `Kmarket` ;

-- -----------------------------------------------------
-- Table `Kmarket`.`km_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_member` (
  `uid` VARCHAR(20) NOT NULL,
  `pass` VARCHAR(255) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `gender` TINYINT NOT NULL,
  `hp` CHAR(13) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `type` TINYINT NOT NULL,
  `point` INT NULL DEFAULT 0,
  `level` TINYINT NULL DEFAULT 1,
  `zip` VARCHAR(10) NULL,
  `addr1` VARCHAR(255) NULL,
  `addr2` VARCHAR(255) NULL,
  `company` VARCHAR(20) NULL,
  `ceo` VARCHAR(20) NULL,
  `bizRegNum` CHAR(12) NULL,
  `comRegNum` VARCHAR(100) NULL,
  `tel` VARCHAR(20) NULL,
  `manager` VARCHAR(20) NULL,
  `managerHp` CHAR(13) NULL,
  `fax` VARCHAR(20) NULL,
  `regip` VARCHAR(100) NOT NULL,
  `wdate` DATETIME NULL,
  `rdate` DATETIME NOT NULL,
  `etc1` INT NULL,
  `etc2` INT NULL,
  `etc3` VARCHAR(10) NULL,
  `etc4` VARCHAR(20) NULL,
  `etc5` VARCHAR(30) NULL,
  PRIMARY KEY (`uid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_member_terms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_member_terms` (
  `terms` TEXT NOT NULL,
  `privacy` TEXT NOT NULL,
  `location` TEXT NOT NULL,
  `finance` TEXT NOT NULL,
  `tax` TEXT NOT NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_member_point`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_member_point` (
  `pointNo` VARCHAR(45) NOT NULL,
  `uid` VARCHAR(20) NOT NULL,
  `ordNo` INT NOT NULL,
  `point` INT NOT NULL,
  `pointDate` DATETIME NOT NULL,
  INDEX `fk_km_member_point_km_member_idx` (`uid` ASC) VISIBLE,
  PRIMARY KEY (`pointNo`),
  CONSTRAINT `fk_km_member_point_km_member`
    FOREIGN KEY (`uid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_cate1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_cate1` (
  `cate1` TINYINT NOT NULL,
  `c1Name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cate1`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_cate2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_cate2` (
  `cate1` TINYINT NOT NULL,
  `cate2` INT NOT NULL,
  `c2Name` VARCHAR(20) NOT NULL,
  INDEX `fk_km_product_cate2_km_product_cate11_idx` (`cate1` ASC) VISIBLE,
  PRIMARY KEY (`cate1`, `cate2`),
  CONSTRAINT `fk_km_product_cate2_km_product_cate11`
    FOREIGN KEY (`cate1`)
    REFERENCES `Kmarket`.`km_product_cate1` (`cate1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product` (
  `prodNo` INT NOT NULL AUTO_INCREMENT,
  `prodCate1` TINYINT NOT NULL,
  `prodCate2` INT NOT NULL,
  `prodName` VARCHAR(100) NOT NULL,
  `descript` VARCHAR(100) NOT NULL,
  `company` VARCHAR(100) NOT NULL,
  `seller` VARCHAR(20) NOT NULL,
  `price` INT NOT NULL,
  `discount` TINYINT NULL DEFAULT 0,
  `point` INT NULL DEFAULT 0,
  `stock` INT NULL DEFAULT 0,
  `sold` INT NULL DEFAULT 0,
  `delivery` INT NULL DEFAULT 0,
  `hit` INT NULL DEFAULT 0,
  `score` TINYINT NULL DEFAULT 0,
  `review` INT NULL DEFAULT 0,
  `thumb1` VARCHAR(255) NOT NULL,
  `thumb2` VARCHAR(255) NOT NULL,
  `thumb3` VARCHAR(255) NOT NULL,
  `detail` VARCHAR(255) NOT NULL,
  `status` VARCHAR(20) NULL DEFAULT '새상품',
  `duty` VARCHAR(20) NULL DEFAULT '과세상품',
  `receipt` VARCHAR(20)  NULL DEFAULT '신용카드 전표',
  `bizType` VARCHAR(20) NULL DEFAULT '사업자 판매자',
  `origin` VARCHAR(20) NULL DEFAULT '상세설명참고',
  `ip` VARCHAR(20) NOT NULL,
  `wdate` DATETIME NULL,
  `rdate` DATETIME NOT NULL,
  `etc1` INT NULL,
  `etc3` VARCHAR(10) NULL,
  `etc4` VARCHAR(20) NULL,
  `etc5` VARCHAR(30) NULL,
  PRIMARY KEY (`prodNo`),
  INDEX `fk_km_product_km_product_cate21_idx` (`prodCate1` ASC, `prodCate2` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_km_product_cate21`
    FOREIGN KEY (`prodCate1` , `prodCate2`)
    REFERENCES `Kmarket`.`km_product_cate2` (`cate1` , `cate2`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_cart` (
  `cartNo` INT NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cartNo`),
  INDEX `fk_km_product_cart_km_member1_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_cart_km_member1`
    FOREIGN KEY (`uid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_cart_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_cart_item` (
  `cartNo` INT NOT NULL,
  `uid` VARCHAR(20) NOT NULL,
  `prodNo` INT NOT NULL,
  `count` INT NOT NULL,
  `price` INT NOT NULL,
  `discount` INT NOT NULL,
  `point` INT NOT NULL,
  `delivery` INT NOT NULL,
  `total` INT NOT NULL,
  `rdate` DATETIME NOT NULL,
  INDEX `fk_table1_km_product_cart1_idx` (`cartNo` ASC) VISIBLE,
  INDEX `fk_km_product_cart_item_km_member1_idx` (`uid` ASC) VISIBLE,
  INDEX `fk_km_product_cart_item_km_product1_idx` (`prodNo` ASC) VISIBLE,
  CONSTRAINT `fk_table1_km_product_cart1`
    FOREIGN KEY (`cartNo`)
    REFERENCES `Kmarket`.`km_product_cart` (`cartNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_product_cart_item_km_member1`
    FOREIGN KEY (`uid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_product_cart_item_km_product1`
    FOREIGN KEY (`prodNo`)
    REFERENCES `Kmarket`.`km_product` (`prodNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_order` (
  `ordNo` INT NOT NULL AUTO_INCREMENT,
  `ordUid` VARCHAR(20) NOT NULL,
  `ordCount` INT NULL DEFAULT 0,
  `ordPrice` INT NULL DEFAULT 0,
  `ordDiscount` INT NULL DEFAULT 0,
  `ordDelivery` INT NULL DEFAULT 0,
  `savePoint` INT NULL DEFAULT 0,
  `usedPoint` INT NULL DEFAULT 0,
  `ordTotPrice` INT NULL DEFAULT 0,
  `recipName` VARCHAR(20) NOT NULL,
  `recipHp` CHAR(13) NOT NULL,
  `recipZip` CHAR(5) NOT NULL,
  `recipAddr1` VARCHAR(255) NOT NULL,
  `recipAddr2` VARCHAR(255) NOT NULL,
  `ordPayment` TINYINT NOT NULL,
  `ordComplete` TINYINT NOT NULL,
  `ordDate` DATETIME NOT NULL,
  INDEX `fk_km_product_order_km_member1_idx` (`ordUid` ASC) VISIBLE,
  PRIMARY KEY (`ordNo`),
  CONSTRAINT `fk_km_product_order_km_member1`
    FOREIGN KEY (`ordUid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_order_item` (
  `ordNo` INT NOT NULL,
  `prodNo` INT NOT NULL,
  `count` INT NOT NULL,
  `price` INT NOT NULL,
  `discount` TINYINT NOT NULL,
  `point` INT NOT NULL,
  `delivery` INT NOT NULL,
  `total` INT NOT NULL,
  INDEX `fk_km_product_order_item_km_product_order1_idx` (`ordNo` ASC) VISIBLE,
  INDEX `fk_km_product_order_item_km_product1_idx` (`prodNo` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_order_item_km_product_order1`
    FOREIGN KEY (`ordNo`)
    REFERENCES `Kmarket`.`km_product_order` (`ordNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_product_order_item_km_product1`
    FOREIGN KEY (`prodNo`)
    REFERENCES `Kmarket`.`km_product` (`prodNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_review` (
  `revNo` INT NOT NULL AUTO_INCREMENT,
  `prodNo` INT NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `uid` VARCHAR(20) NOT NULL,
  `rating` TINYINT NOT NULL,
  `regip` VARCHAR(100) NOT NULL,
  `rdate` DATETIME NOT NULL,
  PRIMARY KEY (`revNo`),
  INDEX `fk_km_product_review_km_product1_idx` (`prodNo` ASC) VISIBLE,
  INDEX `fk_km_product_review_km_member1_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_review_km_product1`
    FOREIGN KEY (`prodNo`)
    REFERENCES `Kmarket`.`km_product` (`prodNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_product_review_km_member1`
    FOREIGN KEY (`uid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

# cs 게시판 추가 


-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_cate1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_cate1` (
  `cate1` INT NOT NULL,
  `c1Name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cate1`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_cate2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_cate2` (
  `cate1` INT NOT NULL,
  `cate2` INT NOT NULL,
  `c2Name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cate2`, `cate1`),
  INDEX `fk_km_cs_cate2_km_cs_cate11_idx` (`cate1` ASC) VISIBLE,
  CONSTRAINT `fk_km_cs_cate2_km_cs_cate11`
    FOREIGN KEY (`cate1`)
    REFERENCES `Kmarket`.`km_cs_cate1` (`cate1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_qna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_qna` (
  `qnaNo` INT NOT NULL AUTO_INCREMENT,
  `cate1` INT NOT NULL,
  `cate2` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `file1` VARCHAR(255) NULL,
  `file2` VARCHAR(255) NULL,
  `file3` VARCHAR(255) NULL,
  `file4` VARCHAR(255) NULL,
  `writer` VARCHAR(20) NOT NULL,
  `ordNo` INT NULL,
  `prodNo` INT NULL,
  `parent` INT NULL,
  `answerComplete` TINYINT NULL,
  `regip` VARCHAR(100) NOT NULL,
  `rdate` DATETIME NOT NULL,
  INDEX `fk_table1_km_cs_cate21_idx` (`cate2` ASC, `cate1` ASC) VISIBLE,
  PRIMARY KEY (`qnaNo`),
  INDEX `fk_table1_km_member1_idx` (`writer` ASC) VISIBLE,
  INDEX `fk_table1_km_product_order1_idx` (`ordNo` ASC) VISIBLE,
  CONSTRAINT `fk_table1_km_cs_cate21`
    FOREIGN KEY (`cate2` , `cate1`)
    REFERENCES `Kmarket`.`km_cs_cate2` (`cate2` , `cate1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_km_member1`
    FOREIGN KEY (`writer`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_km_product_order1`
    FOREIGN KEY (`ordNo`)
    REFERENCES `Kmarket`.`km_product_order` (`ordNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_faq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_faq` (
  `faqNo` INT NOT NULL AUTO_INCREMENT,
  `cate1` INT NOT NULL,
  `cate2` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `relatedFaq` INT NULL,
  `writer` VARCHAR(20) NOT NULL,
  `regip` VARCHAR(100) NOT NULL,
  `rdate` DATETIME NOT NULL,
  PRIMARY KEY (`faqNo`),
  INDEX `fk_km_cs_faq_km_cs_cate21_idx` (`cate2` ASC, `cate1` ASC) VISIBLE,
  INDEX `fk_km_cs_faq_km_cs_faq1_idx` (`relatedFaq` ASC) VISIBLE,
  INDEX `fk_km_cs_faq_km_member1_idx` (`writer` ASC) VISIBLE,
  CONSTRAINT `fk_km_cs_faq_km_cs_cate21`
    FOREIGN KEY (`cate2` , `cate1`)
    REFERENCES `Kmarket`.`km_cs_cate2` (`cate2` , `cate1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_cs_faq_km_cs_faq1`
    FOREIGN KEY (`relatedFaq`)
    REFERENCES `Kmarket`.`km_cs_faq` (`faqNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_cs_faq_km_member1`
    FOREIGN KEY (`writer`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_faq_rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_faq_rate` (
  `faqNo` INT NOT NULL,
  `uid` VARCHAR(20) NOT NULL,
  `rate` TINYINT NOT NULL,
  INDEX `fk_table1_km_cs_faq1_idx` (`faqNo` ASC) VISIBLE,
  PRIMARY KEY (`faqNo`, `uid`),
  INDEX `fk_table1_km_member2_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_table1_km_cs_faq1`
    FOREIGN KEY (`faqNo`)
    REFERENCES `Kmarket`.`km_cs_faq` (`faqNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_km_member2`
    FOREIGN KEY (`uid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_notice` (
  `noticeNo` INT NOT NULL AUTO_INCREMENT,
  `cate1` INT NOT NULL,
  `cate2` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `writer` VARCHAR(20) NOT NULL,
  `regip` VARCHAR(100) NOT NULL,
  `rdate` DATETIME NOT NULL,
  PRIMARY KEY (`noticeNo`),
  INDEX `fk_km_cs_notice_km_cs_cate21_idx` (`cate2` ASC, `cate1` ASC) VISIBLE,
  INDEX `fk_km_cs_notice_km_member1_idx` (`writer` ASC) VISIBLE,
  CONSTRAINT `fk_km_cs_notice_km_cs_cate21`
    FOREIGN KEY (`cate2` , `cate1`)
    REFERENCES `Kmarket`.`km_cs_cate2` (`cate2` , `cate1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_cs_notice_km_member1`
    FOREIGN KEY (`writer`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


ALTER TABLE `km_product` auto_increment = 1000000;
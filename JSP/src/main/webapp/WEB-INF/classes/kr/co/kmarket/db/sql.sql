SELECT * FROM Kmarket.km_product WHERE prodCate1=? ORDER BY prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY sold DESC ,prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY sold ASC ,prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY price DESC, prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY price ASC,prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY score DESC, prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY score ASC, prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY review DESC, prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY review ASC, prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY rdate DESC, prodNo DESC LIMIT ?, 10;
SELECT * FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ? ORDER BY rdate ASC, prodNo DESC LIMIT ?, 10;
SELECT a.*, avg(b.rating) as rating FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE prodCate1=? and prodCate2 = ? and stock>0 group by a.prodNo ORDER BY prodNo DESC LIMIT ?, 10;


SELECT COUNT(prodNo) FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ?;

ALTER TABLE `km_product` auto_increment = 1000000;

SELECT * FROM km_product_review where prodNo=?;
SELECT COUNT(revNo) FROM Kmarket.km_product_review WHERE prodNo = ?;
SELECT a.*, AVG(b.rating) as rating FROM `km_product` as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE a.`prodNo` =?
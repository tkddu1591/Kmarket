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
SELECT a.*, avg(b.rating) as rating,c1.c1Name,c2.c2Name as rating FROM Kmarket.km_product as a
    LEFT JOIN km_product_review as b on a.prodNo = b.prodNo
    LEFT JOIN Kmarket.km_product_cate2 c2 on a.prodCate1 = c2.cate1 and a.prodCate2 = c2.cate2
    LEFT JOIN Kmarket.km_product_cate1 c1 on c1.cate1 = a.prodCate1
        WHERE prodCate1=? and prodCate2 = ? and stock>0 group by a.prodNo ORDER BY prodNo DESC LIMIT ?, 10;

SELECT c1Name from km_product_cate1 where cate1 = ?;

SELECT COUNT(prodNo) FROM Kmarket.km_product WHERE prodCate1=? and prodCate2 = ?;

ALTER TABLE `km_product` auto_increment = 1000000;

SELECT * FROM km_product_review where prodNo=? ORDER BY revNo DESC LIMIT ?,5;
SELECT COUNT(revNo) FROM Kmarket.km_product_review WHERE prodNo = ?;
SELECT a.*, AVG(b.rating) as rating FROM `km_product` as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE a.`prodNo` =?;

INSERT INTO `km_product_cart` SET uid = ?, prodNo =?, count=?, price =?, discount =?, point =?, delivery =?, total = ?, rdate=?;

SELECT a.*, kp.prodName as prodName,kp.descript as descript  FROM `km_product_cart` as a join Kmarket.km_product kp on kp.prodNo = a.prodNo WHERE a.uid=?;

select c1.*, c2.cate2, c2Name from km_product_cate1 as c1 join km_product_cate2 as c2 on c1.cate1 = c2.cate1;

SELECT a.*, avg(b.rating) as rating FROM Kmarket.km_product as a LEFT JOIN km_product_review as b on a.prodNo = b.prodNo WHERE prodCate1=? and prodCate2 = ? and stock>0 group by a.prodNo ORDER BY  prodNo DESC LIMIT ?, 10;

SELECT COUNT(a.prodNo) FROM km_product_cart as a WHERE prodNo = ? ;
UPDATE `km_product_cart` SET count = count+?, total = price*count + delivery WHERE prodNo =?;
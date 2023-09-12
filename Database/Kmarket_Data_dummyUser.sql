use Kmarket;
#관리자 회원 : type 9, level 9
INSERT INTO `km_member` SET
					`uid` = 'admin',
					`pass` = SHA2('admin',256),
					`name` = '관리자',
					`gender` = 2,
					`hp` = '010-1234-1234',
					`email` = 'admin@admin.com',
					`type` = 9,
					`level`= 9,
					`regip` = '0.0.0.1',
					`rdate` = NOW();
					
#일반 회원 : type 1, level 1
INSERT INTO `km_member` SET
					`uid` = 'user',
					`pass` = SHA2('user',256),
					`name` = '김유저',
					`gender` = 1,
					`hp` = '010-1234-5678',
					`email` = 'user@use.com',
					`type` = 1,
					`level`= 1,
					`zip`= '04607',
					`addr1`= '제주특별자치도 제주시 테헤란로 46',
					`addr2`= '206호',
					`regip` = '0.0.0.1',
					`rdate` = NOW();
					
										
#판매자 회원 : type 2, level 5
INSERT INTO `km_member` SET
					`uid` = 'seller',
					`pass` = SHA2('seller',256),
					`name` = '김상인',
					`gender` = 1,
					`hp` = '010-5678-5678',
					`email` = 'seller@seller.com',
					`type` = 2,
					`level`= 5,
					`zip`= '34553',
					`addr1`= '제주특별자치도 서귀포시 목동로 46',
					`addr2`= '556호',
					`company`= '(주)회사이름',
					`ceo`= '김대표',
					`bizRegNum`= '220-81-83676',
					`comRegNum`= '강남 10630호',
					`tel`= '064-1111-1111',
					`manager`= '김담당자',
					`managerHp`= '010-1111-1111',
					`fax`= '02-589-8844',
					`regip` = '0.0.0.1',
					`rdate` = NOW();
					
					
			
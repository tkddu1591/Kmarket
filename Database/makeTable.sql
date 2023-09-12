create table if not exists km_member
(
    uid       varchar(20)       not null
        primary key,
    pass      varchar(255)      not null,
    name      varchar(20)       not null,
    gender    tinyint           not null comment '1: 남, 2: 여',
    hp        char(13)          not null,
    email     varchar(100)      not null,
    type      tinyint           not null comment '1: 일반회원, 2: 판매회원',
    point     int     default 0 null,
    level     tinyint default 1 null,
    zip       varchar(10)       null,
    addr1     varchar(255)      null,
    addr2     varchar(255)      null,
    company   varchar(20)       null,
    ceo       varchar(20)       null,
    bizRegNum char(12)          null,
    comRegNum varchar(100)      null,
    tel       varchar(20)       null,
    manager   varchar(20)       null,
    managerHP char(13)          null,
    fax       varchar(20)       null,
    regIp     varchar(100)      not null,
    wdate     datetime          null,
    rdate     datetime          not null,
    etc1      int               null,
    etc2      int               null,
    etc3      varchar(10)       null,
    etc4      varchar(20)       null,
    etc5      varchar(30)       null
);

create table if not exists km_member_terms
(
    terms    text not null,
    privacy  text not null,
    location text not null,
    finance  text not null,
    tax      text not null
);

create table if not exists km_product
(
    prodNo    int auto_increment
        primary key,
    prodCate1 tinyint           not null,
    prodCate2 tinyint           not null,
    prodName  varchar(100)      not null,
    descript  varchar(100)      not null,
    company   varchar(100)      not null,
    seller    varchar(20)       null,
    price     int               not null,
    discount  tinyint default 0 null,
    point     int     default 0 null,
    stock     int     default 0 null,
    sold      int     default 0 null,
    delivery  int     default 0 null,
    hit       int     default 0 null,
    score     tinyint default 0 null,
    review    int     default 0 null,
    thumb1    varchar(255)      not null,
    thumb2    varchar(255)      not null,
    thumb3    varchar(255)      not null,
    detail    varchar(255)      not null,
    status    varchar(20)       null,
    duty      varchar(20)       null,
    receipt   varchar(20)       null,
    bizType   varchar(20)       null,
    origin    varchar(20)       null,
    ip        varchar(20)       not null,
    rdate     datetime          not null,
    etc1      int               null,
    etc2      int               null,
    etc3      varchar(10)       null,
    etc4      varchar(20)       null,
    etc5      varchar(30)       null
);

create table if not exists km_product_cart
(
    cartNo   int auto_increment
        primary key,
    uid      varchar(20) not null,
    prodNo   int         not null,
    count    int         not null,
    price    int         not null,
    discount int         not null,
    point    int         not null,
    delivery int         not null,
    total    int         not null,
    rdate    datetime    not null,
    constraint km_product_cart_km_member_uid_fk
        foreign key (uid) references km_member (uid),
    constraint km_product_cart_km_product_prodNo_fk
        foreign key (prodNo) references km_product (prodNo)
);

create table if not exists km_product_cate1
(
    cate1  tinyint     not null comment '2자리 숫자'
        primary key,
    c1Name varchar(20) not null
);

create table if not exists km_product_cate2
(
    cate1  tinyint     not null comment '2자리 숫자',
    cate2  int         not null comment '2자리 숫자',
    c2Name varchar(20) not null,
    constraint km_product_cate2_km_product_cate1_cate1_fk
        foreign key (cate1) references km_product_cate1 (cate1)
);

create table if not exists km_product_order
(
    ordNo       int auto_increment
        primary key,
    ordUid      varchar(20)   not null,
    ordCount    int default 0 null,
    ordPrice    int default 0 not null,
    ordDiscount int default 0 null,
    ordDelivery int default 0 null,
    savePoint   int default 0 null,
    usedPoint   int default 0 null,
    ordTotPrice int default 0 null,
    recipName   varchar(20)   not null,
    recipHp     char(13)      not null,
    recipZip    char(5)       not null,
    recipAddr1  varchar(255)  not null,
    recipAddr2  varchar(255)  not null,
    ordPayment  tinyint       not null,
    ordComplete tinyint       not null,
    ordDate     datetime      null,
    constraint km_product_order_km_member_uid_fk
        foreign key (ordUid) references km_member (uid)
);

create table if not exists km_member_point
(
    pointNo   int auto_increment
        primary key,
    uid       varchar(20) not null,
    ordNo     int         not null,
    point     int         not null,
    pointDate datetime    not null,
    constraint km_member_point_km_member_uid_fk
        foreign key (uid) references km_member (uid),
    constraint km_member_point_km_product_order_ordNo_fk
        foreign key (ordNo) references km_product_order (ordNo)
);

create table if not exists km_product_order_item
(
    ordNo    int     not null,
    prodNo   int     not null,
    count    int     not null,
    price    int     not null,
    discount tinyint not null,
    point    int     not null,
    delivery int     not null,
    total    int     not null,
    constraint km_product_order_item_km_product_order_ordNo_fk
        foreign key (ordNo) references km_product_order (ordNo),
    constraint km_product_order_item_km_product_prodNo_fk
        foreign key (prodNo) references km_product (prodNo)
);

create table if not exists km_product_review
(
    revNo   int auto_increment
        primary key,
    prodNo  int          not null,
    content varchar(255) not null,
    uid     varchar(20)  not null,
    rating  tinyint      not null,
    regip   varchar(100) not null,
    rdate   datetime     not null,
    constraint km_product_review_km_member_uid_fk
        foreign key (uid) references km_member (uid),
    constraint km_product_review_km_product_prodNo_fk
        foreign key (prodNo) references km_product (prodNo)
);


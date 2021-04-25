-- 商家
create table business
(
    id int PRIMARY KEY,
    name varchar(64) not null comment '商家名称',
    remark varchar(255) default '' comment '备注',
    register_time datetime not null comment '注册时间',
    expire_time datetime not null comment '到期时间',
    type int not null comment '店铺经营类型',
    concat varchar(255) not null comment '联系人'
    mobile varchar(11) not null comment '联系电话',
    enable int default 0 comment '禁用启用'
    create_time datetime not null,
    last_modify_time datetime not null
)


-- 店铺
create table shop
(
    id int PRIMARY KEY,
    name varchar(64) not null comment '店铺名称',
    url varchar(255) not null comment '店铺地址',
    register_address varchar(255) not null comment '注册地'
    business_id int not null comment '商家',
    description varchar(255) default '' comment '店铺描述',
    sales double default 0 comment '销量/付款人数',
    level int not null comment '店铺等级'
    create_time datetime not null,
    last_modify_time datetime not null
)

-- 商品表
create table products
(
    id          int  PRIMARY KEY,
    name        varchar(64)  not null comment '商品名称',
    price       DOUBLE comment '价格',
    image_url varchar(255) not null comment '商品图片地址',
    enable      int comment '是否上架',
    shop_id     int not null comment '店铺id'
    category_id int comment '商品类型',
    create_time datetime  not null,
    last_modify_time datetime  not null
);

-- 商品明细
create table product_details(
    id int PRIMARY KEY,
    product_id int not null comment '商品id',
    description varchar(512) default '' comment '商品描述',
    create_time datetime  not null,
    last_modify_time datetime  not null
);

--库存
create table stock(
    id int primary key,
    product_id id not null,
    shop_id id not null,
    stock int not null,
    create_time datetime  not null,
    last_modify_time datetime  not null
);



-- 订单表
create table `order`
(
    id          varchar(32) null,
    original_price  double not null comment '原价',
    price  double not null comment '最终价格',
    status       tinyint     null comment '订单状态',
    user_id     varchar(32) null,
    create_time datetime  not null,
    last_modify_time datetime  not null
);

-- 订单详情
create table order_details(
    id int primary key,
    product_details int not null,
    payment_id  varchar(32) null comment '支付详情id',
    delivery_id varchar(32) null comment '配送详情id',
    coupon_id int not null comment '优惠券id',
    user_id int not null comment '用户id',
    create_time datetime  not null,
    last_modify_time datetime  not null
);

--资金流向表

-- 退货

--
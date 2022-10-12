create database EcommerceNashTech
GO

Use EcommerceNashTech
GO
CREATE TABLE tbl_Role(
	id int Identity(1,1) Primary key,
	typeRole varchar(30),
	isActive bit
)
GO
CREATE TABLE tbl_User(
	id int Identity(1,1) Primary key,
	fullName nvarchar(50),
	email varchar(50) unique,
	roleId int,
	address varchar(50),
	phone varchar(10) unique,
	isActive bit,
	Constraint FK_RoleUser Foreign key (roleId)
	References tbl_Role(id)
)
GO
CREATE TABLE tbl_Category(
	id int Identity(1,1) primary key,
	typeCategory nvarchar(30),
	isActive bit
)
GO
-- size, color
CREATE TABLE tbl_variation(
	id int Identity(1,1) Primary key,
	category_id int,
	name varchar(50),
	Constraint FK_CateVariantion Foreign key (category_id)
	References tbl_Category(id)
)
GO
-- size: xxl, color: red
CREATE table tbl_variation_option(
	id int Identity(1,1) Primary key,
	variation_id int,
	value varchar (30),
	 Foreign key (variation_id)
	References tbl_variation(id)
)
GO
--shirt: nike, adidas show product page
CREATE TABLE tbl_Product(
	id int Identity(1,1) primary key,
	category_id int,
	name varchar(100),
	description varchar(100),
	product_image varchar(100),
	isActive bit,
	 Foreign key (category_id)
	References tbl_Category(id)
)
GO

CREATE TABLE tbl_Product_Item(
	id int Identity(1,1) primary key,
	product_id int,
	quantity int,
	price float,
	product_image varchar(100),
	isActice bit,
	Foreign key (product_id)
	References tbl_Product(id),
)
GO
CREATE TABLE tbl_Product_Configuration(
	product_item_id int,
	variation_option_id int,
	Foreign key (product_item_id)
	References tbl_Product_Item(id),
	Foreign key (variation_option_id)
	References tbl_variation_option(id)
)
GO
CREATE table tbl_shopping_cart(
	id int Identity(1,1) primary key,
	userid int,
	Foreign key (userid)
	References tbl_User(id)
)

GO
CREATE TABLE tbl_Cart_Item(
	id int Identity(1,1) primary key,
	cartid int,
	product_item_id int,
	quantity int,
	isActive bit,
	Foreign key (cartid)
	References tbl_shopping_cart(id),
	Foreign key (product_item_id)
	References tbl_Product_Item(id)
)
GO
create table tbl_order_status(
	id int Identity(1,1) primary key,
	status varchar(30)
)
GO
CREATE TABLE tbl_shop_Order(
	id int Identity(1,1) Primary key,
	orderDate datetime,
	shipping_address varchar(100),
	total float,
	userId int,
	status int,
	Foreign key (userId)
	References tbl_User(id),
	Foreign key (status)
	References tbl_order_status(id)
)
GO
CREATE TABLE tbl_order_history(
	id int Identity(1,1) primary key,
	productItemId int,
	orderId int,
	quantity int,
	prirce float,
	Foreign key (productItemId)
	References tbl_Product_Item(id),
	Foreign key (orderId)
	References tbl_shop_Order(id)
)
GO
CREATE TABLE tbl_Rating(
	id int Identity(1,1) primary key,
	userid int,
	ordered_productId int,
	rating_value int,
	comment varchar(100),
	Foreign key(userid)
	References tbl_User(id),
	Foreign key(ordered_productId)
	References tbl_order_history(id),
	isActice bit
)


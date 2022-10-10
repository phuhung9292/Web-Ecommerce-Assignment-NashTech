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

CREATE TABLE tbl_Product(
	id int Identity(1,1) primary key,
	productName nvarchar(30),
	categoryId int,
	description varchar(100),
	importDate datetime,
	quantity int,
	price float,
	isActice bit,
	Constraint FK_CategoryProduct Foreign key (categoryId)
	References tbl_Category(id),
)
GO
CREATE TABLE tbl_Cart(
	id int Identity(1,1) primary key,
	userId int,
	productId int,
	price float,
	addDate datetime,
	quantity int,
	isActive bit,
	Constraint FK_ProductCart Foreign key (productId)
	References tbl_Product(id),
	Constraint FK_UserCart Foreign key (userId)
	References tbl_User(id)
)
GO
CREATE TABLE tbl_Order(
	id int Identity(1,1) Primary key,
	orderDate datetime,
	total float,
	cartId int,
	isActice int,
	Constraint FK_CartOrder Foreign key (cartId)
	References tbl_Cart(id)
)
GO
CREATE TABLE tbl_Rating(
	id int Identity(1,1) primary key,
	productId int,
	orderId int,
	Constraint FK_ProductRating Foreign key(productId)
	References tbl_Product(id),
	Constraint FK_OrderRating Foreign key(orderId)
	References tbl_Order(id),
	isActice bit
)


CREATE TABLE tbl_ImageProduct(
	id int Identity(1,1) primary key,
	productId int, 
	img varchar(100),
	isActive bit,
	Constraint FK_ProductImage Foreign key (productId)
	References tbl_Product(id)
)
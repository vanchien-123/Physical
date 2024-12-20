USE [physical]
GO
/****** Object:  Schema [PHYSICAL]    Script Date: 11/17/2024 1:00:13 PM ******/
CREATE SCHEMA [PHYSICAL]
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[ID] [int] NOT NULL,
	[Username] [varchar](50) NULL,
	[Password] [varchar](50) NULL,
 CONSTRAINT [PK_admin_id] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Birthday] [date] NULL,
	[Address] [nvarchar](max) NOT NULL,
	[Phone] [varchar](10) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Sex] [nvarchar](10) NOT NULL,
	[Username] [varchar](10) NOT NULL,
	[Password] [varchar](max) NOT NULL,
	[CreateAt] [datetime] NOT NULL,
 CONSTRAINT [PK_cus_id] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[OrderID] [bigint] NOT NULL,
	[ProID] [bigint] NOT NULL,
	[ImportPrice] [float] NOT NULL,
	[SellPrice] [float] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Total] [float] NOT NULL,
 CONSTRAINT [PK_detail_id] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[OrderDate] [datetime] NULL,
	[CusID] [bigint] NOT NULL,
	[Total] [float] NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
	[Receiver] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](max) NOT NULL,
	[Phone] [varchar](10) NOT NULL,
	[Note] [nvarchar](50) NULL,
	[Payment] [nvarchar](50) NULL,
	[TransactionNo] [nchar](50) NULL,
	[TransactionDate] [datetime] NULL,
 CONSTRAINT [PK_orders_id] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[Image] [varchar](50) NOT NULL,
	[ImportPrice] [float] NOT NULL,
	[SellPrice] [float] NOT NULL,
	[Description] [ntext] NULL,
	[TypeID] [int] NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_pro_id] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TypeProduct]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TypeProduct](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_type_id] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Admin] ([ID], [Username], [Password]) VALUES (1, N'admin', N'1')
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([ID], [Name], [Birthday], [Address], [Phone], [Email], [Sex], [Username], [Password], [CreateAt]) VALUES (1, N'Alisha Wagner', NULL, N'PO Box 2741
Pikeville,Kentucky,United States
41502', N'0964547970', N'chien@gmail.com', N'Nam', N'lukasz13', N'$2a$12$m/o8kXkdQRN.KLXT/jDsSO4mou4PsenYxMXUj/X0OcVsDERq.er1q', CAST(N'2024-11-06T21:01:05.990' AS DateTime))
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (1, 2, 3, 50, 60, 2, 120)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (2, 2, 2, 43, 63, 1, 63)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (3, 3, 3, 50, 60, 2, 120)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (4, 3, 2, 43, 63, 3, 189)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (5, 4, 3, 50, 60, 1, 60)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (6, 5, 2, 43, 63, 1, 63)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (7, 6, 2, 43, 63, 1, 63)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (8, 7, 3, 50, 60, 1, 60)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (9, 8, 3, 50, 60, 1, 60)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (10, 9, 3, 50, 60, 1, 60)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (11, 10, 2, 43, 63, 1, 63)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (12, 11, 3, 50, 60, 1, 60)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (13, 11, 2, 43, 63, 1, 63)
INSERT [dbo].[OrderDetail] ([ID], [OrderID], [ProID], [ImportPrice], [SellPrice], [Quantity], [Total]) VALUES (14, 12, 3, 50000, 60000, 1, 60000)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (1, CAST(N'2024-11-13T01:26:29.223' AS DateTime), 1, 63, N'CANCELED', N'chien', N'123123', N'0987654321', N'', NULL, NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (2, CAST(N'2024-11-13T01:34:01.170' AS DateTime), 1, 183, N'PENDING', N'chien', N'3624 S ATLANTIC AVE DAYTONA BEACH FL 32118-7604 USA', N'0987654321', N'Hello', NULL, NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (3, CAST(N'2024-11-13T01:35:30.703' AS DateTime), 1, 309, N'TOSHIP', N'chien', N'3624 S ATLANTIC AVE DAYTONA BEACH FL 32118-7604 USA', N'0987654321', N'Hello', N'COD', NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (4, CAST(N'2024-11-14T20:50:22.870' AS DateTime), 1, 60, N'PENDING', N'chien', N'PO Box 2741 Pikeville,Kentucky,United States 41502', N'0987654321', N'Hi guy', NULL, NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (5, CAST(N'2024-11-14T20:52:40.613' AS DateTime), 1, 63, N'PENDING', N'chien', N'3624 S ATLANTIC AVE DAYTONA BEACH FL 32118-7604 USA', N'0987654321', N'Hello world!!', N'VNPAY', NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (6, CAST(N'2024-11-14T20:56:12.503' AS DateTime), 1, 63, N'PENDING', N'chien', N'3624 S ATLANTIC AVE DAYTONA BEACH FL 32118-7604 USA', N'0987654321', N'', N'COD', NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (7, CAST(N'2024-11-14T21:11:43.817' AS DateTime), 1, 60, N'PENDING', N'chien', N'PO Box 2741 Pikeville,Kentucky,United States 41502', N'0987654321', N'Hi', N'VNPAY', NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (8, CAST(N'2024-11-14T21:12:49.290' AS DateTime), 1, 60, N'PENDING', N'chien', N'123123', N'0987654321', N'', N'VNPAY', NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (9, CAST(N'2024-11-14T21:14:08.303' AS DateTime), 1, 60, N'PENDING', N'chien', N'123123', N'0987654321', N'', N'VNPAY', NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (10, CAST(N'2024-11-14T21:16:32.730' AS DateTime), 1, 63, N'PENDING', N'chien', N'123123', N'0987654321', N'', N'VNPAY', NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (11, CAST(N'2024-11-14T21:27:42.907' AS DateTime), 1, 123, N'PENDING', N'chien', N'3624 S ATLANTIC AVE DAYTONA BEACH FL 32118-7604 USA', N'0987654321', N'Hi', N'VNPAY', NULL, NULL)
INSERT [dbo].[Orders] ([ID], [OrderDate], [CusID], [Total], [Status], [Receiver], [Address], [Phone], [Note], [Payment], [TransactionNo], [TransactionDate]) VALUES (12, CAST(N'2024-11-14T21:29:20.400' AS DateTime), 1, 60000, N'PENDING', N'chien', N'3624 S ATLANTIC AVE DAYTONA BEACH FL 32118-7604 USA', N'0987654321', N'Hi', N'VNPAY', N'14673093                                          ', CAST(N'2024-11-14T21:29:30.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([ID], [Name], [Image], [ImportPrice], [SellPrice], [Description], [TypeID], [Status]) VALUES (2, N'Ergonomic Chair 2', N'274729222_496320868870531_801301537053093334_n.jpg', 43000, 63000, N'Lorem', 1, N'InStock')
INSERT [dbo].[Products] ([ID], [Name], [Image], [ImportPrice], [SellPrice], [Description], [TypeID], [Status]) VALUES (3, N'Nordic Chairs', N'359f2ddc-8b16-4b2b-8fd6-f26e788650b7.jpg', 50000, 60000, N'123123', 1, N'Instock')
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
SET IDENTITY_INSERT [dbo].[TypeProduct] ON 

INSERT [dbo].[TypeProduct] ([ID], [Name]) VALUES (1, N'Chair')
INSERT [dbo].[TypeProduct] ([ID], [Name]) VALUES (2, N'Table')
SET IDENTITY_INSERT [dbo].[TypeProduct] OFF
GO
ALTER TABLE [dbo].[Customer] ADD  CONSTRAINT [DF__customers__cus_c__3C69FB99]  DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[Orders] ADD  DEFAULT (getdate()) FOR [OrderDate]
GO
ALTER TABLE [dbo].[Orders] ADD  CONSTRAINT [DF_order_status]  DEFAULT (N'PENDING') FOR [Status]
GO
ALTER TABLE [dbo].[Products] ADD  DEFAULT (N'Instock') FOR [Status]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_order_id] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([ID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_order_id]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_pro_id] FOREIGN KEY([ProID])
REFERENCES [dbo].[Products] ([ID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_pro_id]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_cus_id] FOREIGN KEY([CusID])
REFERENCES [dbo].[Customer] ([ID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_cus_id]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_type_id] FOREIGN KEY([TypeID])
REFERENCES [dbo].[TypeProduct] ([ID])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_type_id]
GO
/****** Object:  StoredProcedure [dbo].[sp_find_cus_username]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_find_cus_username] @username varchar(10)
as
begin

select 
row_number() over (order by ID) 'stt', 
	* 
from 
	Customer
where 
	Username = @username
end
GO
/****** Object:  StoredProcedure [dbo].[sp_insert_order]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_insert_order] 
	@cus_id bigint,
	@order_total float,
	@order_receiver nvarchar(50),
	@order_delivery_address nvarchar(max),
	@order_phone_receiver varchar(10),
	@order_note nvarchar(50)
as
begin

set nocount on
insert into orders
(
CusID,
Total,
Receiver,
Address,
Phone,
Note
)
select 
	@cus_id,
	@order_total,
	@order_receiver,
	@order_delivery_address,
	@order_phone_receiver,
	@order_note

declare @result table(ID bigint)
	insert @result
	select SCOPE_IDENTITY()

	select * from @result


end
GO
/****** Object:  StoredProcedure [dbo].[sp_insert_type]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_insert_type] @name nvarchar(50)
as
insert into 
	TypeProduct(Name) 
values 
	(@name)
GO
/****** Object:  StoredProcedure [dbo].[sp_show_cus_by_name]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_show_cus_by_name] (@keyword nvarchar)
as
select 
	ROW_NUMBER() over (order by ID) stt, 
	* 
from 
	Customer
where 
	Name like N'%' +@keyword+'%'
GO
/****** Object:  StoredProcedure [dbo].[sp_show_customer]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_show_customer]
as 
select 
	row_number() over(order by ID) stt, 
	* 
from
	Customer
GO
/****** Object:  StoredProcedure [dbo].[sp_show_loyal_customer]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_show_loyal_customer]
as
begin

    SELECT
        row_number() over (order by COUNT(o.ID) desc) 'stt',
        c.ID,
        c.Name,
        COUNT(o.ID) AS total_orders,
        SUM(o.Total) AS total_amount_ordered
    FROM
        Customer c
            LEFT JOIN
        Orders o ON c.ID = o.CusID

    GROUP BY
        c.ID,
        c.Name
    ORDER BY
        total_orders DESC

end
GO
/****** Object:  StoredProcedure [dbo].[sp_show_loyal_customer_by_name]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_show_loyal_customer_by_name] @cus_name nvarchar(100)
as
begin

SELECT 
	row_number() over (order by COUNT(o.ID) desc) 'stt',
    c.ID,
    c.Name,
    COUNT(o.ID) AS total_orders,
	SUM(o.Total) AS total_amount_ordered
FROM
    Customer c
LEFT JOIN
    orders o ON c.ID = o.CusID
WHERE c.Name like N'%'+@cus_name+'%'
GROUP BY
    c.ID,
    c.Name
ORDER BY
    total_orders DESC

end
GO
/****** Object:  StoredProcedure [dbo].[sp_show_order]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

 CREATE  proc [dbo].[sp_show_order]
as
select 
	row_number() over (order by [status]) as 'stt', 
	* 
from 
	orders 
where 
	[status] = 'PENDING' or [status] = 'TOSHIP' or [status] = 'INTRANSIT' or [status] = 'DElIVERED' or [status] = 'COMPLETED' or [status] = 'CANCELED' or [status] = 'CANCELEDREQUEST'
order by 
	CASE 
        WHEN status = 'PENDING' THEN 1
        WHEN status = 'TOSHIP' THEN 2
        WHEN status = 'INTRANSIT' THEN 3
        WHEN status = 'DELIVERED' THEN 4
        WHEN status = 'CANCELED REQUEST' THEN 5
        WHEN status = 'CANCELED' THEN 6
        WHEN status = 'COMPLETED' THEN 7
        ELSE 8
    END,
    OrderDate DESC
GO
/****** Object:  StoredProcedure [dbo].[sp_show_order_by_cus_id]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_show_order_by_cus_id] @cus_id bigint
as
begin

select 
	row_number() over (order by orderdate desc) stt, 
	* 
from 
	orders 
where 
	CusID = @cus_id

end
GO
/****** Object:  StoredProcedure [dbo].[sp_show_order_by_id]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_show_order_by_id] (@id bigint)
as
select 
	ROW_NUMBER() over (order by orders.ID) as 'stt', 
	orders.ID, 
	products.Name, 
	products.ImportPrice, 
	products.SellPrice, 
	OrderDetail.Quantity, 
	OrderDetail.Total, 
	orders.Total
from 
	orders inner join [OrderDetail] 
on 
	orders.ID = [OrderDetail].OrderID inner join products 
on 
	OrderDetail.ProID = products.ID
where 
	orders.ID = @id
GO
/****** Object:  StoredProcedure [dbo].[sp_show_orders]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_show_orders]
as
select 
	row_number() over (order by orders.ID) as 'stt', 
	orders.ID, 
	customer.Name, 
	products.Name, 
	OrderDetail.Quantity, 
	Products.ImportPrice, 
	Products.SellPrice, 
	OrderDetail.Total, 
	Orders.Total, 
	Orders.Receiver, 
	Orders.Address, 
	Orders.Phone, 
	convert(varchar, Orders.OrderDate, 100) as 'OrderDate', 
	Orders.Status
from 
	Orders inner join Customer
on 
	Orders.CusID = Customer.ID inner join OrderDetail 
on 
	Orders.ID = OrderDetail.OrderID inner join Products 
on 
	OrderDetail.ProID = Products.ID 
GO
/****** Object:  StoredProcedure [dbo].[sp_show_pro_by_name]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_show_pro_by_name] (@keyword nvarchar)
as
select 
	ROW_NUMBER() over (order by ID) stt, 
	* 
from 
	Products 
where 
	trim(Name) like '%' + @keyword +'%'
GO
/****** Object:  StoredProcedure [dbo].[sp_show_product]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_show_product]
as
select 
	* 
from 
	Products p inner join TypeProduct t 
on 
	p.TypeID = t.ID 
order by 
	p.Status asc
GO
/****** Object:  StoredProcedure [dbo].[sp_show_product_by_id]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_show_product_by_id] @id bigint
as
select 
	* 
from 
	Products p inner join TypeProduct t 
on 
	p.TypeID = t.ID 
where 
	TypeID = @id
GO
/****** Object:  StoredProcedure [dbo].[sp_show_product_by_type]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_show_product_by_type] @id int
as
select 
	* 
from 
	Products p inner join TypeProduct t 
on 
	p.TypeID = t.ID 
where 
	p.TypeID = @id 
order by 
	p.Status asc
GO
/****** Object:  StoredProcedure [dbo].[sp_show_product_for_user]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_show_product_for_user]
as
select 
	* 
from 
	Products p inner join TypeProduct t 
on 
	p.TypeID = t.ID
where 
	p.Status = 'InStock'
GO
/****** Object:  StoredProcedure [dbo].[sp_show_type]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_show_type] 
as
select 
	row_number() over(order by ID) as 'stt', 
	* 
from 
	TypeProduct
GO
/****** Object:  StoredProcedure [dbo].[sp_top_selling_product]    Script Date: 11/17/2024 1:00:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_top_selling_product]
as
begin

SELECT 
	row_number() over(order by COALESCE(SUM(od.quantity), 0) DESC) STT,
	p.ID,
	p.Name,
	COALESCE(SUM(od.quantity), 0) AS total_quantity_sold,
	COALESCE(SUM(od.quantity * od.sellprice), 0) AS total_revenue
FROM 
	Products p
LEFT JOIN 
	OrderDetail od 
ON 
	p.ID = od.ProID
GROUP BY 
	p.ID, p.Name
ORDER BY 
	total_quantity_sold DESC;

end
GO

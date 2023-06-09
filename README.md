# 2022-database-e_market-backend
BUAA2022数据库课程作业e_market后端部分

## 一、实现环境


### 1.2 后端技术栈：

+ Spring Boot 2.1.1.RELEASE
+ mybatis-plus 3.4.1
+ Lombok

### 1.3 数据库：

+ MySQL

## 三、数据库基本表及约束

### 3.1用户信息表

| 用户信息表 |          |          |             |      |        |
| ---------- | -------- | -------- | ----------- | ---- | ------ |
| 编号       | 字段名   | 中文注释 | 字段类型    | 约束 | 初始值 |
| 1          | U_id     | 用户编号 | int         | 主键 | 自增   |
| 2          | U_acount | 账户名   | varchar(32) | 非空 | /      |
| 3          | U_pwd    | 密码     | varchar(32) | 非空 | /      |
| 4          | U_name   | 昵称     | varchar(32) | 非空 | /      |
| 5          | U_email  | 邮箱     | varchar(64) | /    | /      |
| 6          | U_phone  | 电话     | varchar(16) | /    | /      |
| 7          | U_gneder | 性别     | int         | /    | /      |

### 3.2店铺信息表

| 店铺信息表 |          |          |              |      |        |
| ---------- | -------- | -------- | ------------ | ---- | ------ |
| 编号       | 字段名   | 中文注释 | 字段类型     | 约束 | 初始值 |
| 1          | S_id     | 店铺编号 | int          | 主键 | 自增   |
| 2          | S_acount | 账户名   | varchar(32)  | 非空 | /      |
| 3          | S_pwd    | 密码     | varchar(32)  | 非空 | /      |
| 4          | S_name   | 店铺名称 | varchar(32)  | 非空 | /      |
| 5          | S_intro  | 描述     | varchar(256) | /    | /      |
| 6          | S_time   | 创建时间 | varchar(256) | /    | /      |

### 3.3订单信息表

| 订单信息表 |         |          |              |      |        |
| ---------- | ------- | -------- | ------------ | ---- | ------ |
| 编号       | 字段名  | 中文注释 | 字段类型     | 约束 | 初始值 |
| 1          | O_id    | 订单编号 | int          | 主键 | 自增   |
| 2          | O_addr  | 配送地址 | varchar(20)  | 非空 | /      |
| 3          | O_state | 订单状态 | varchar(255) | 非空 | /      |
| 4          | O_price | 消费金额 | double       | /    | /      |
| 5          | O_time  | 生成时间 | Varchar(255) | /    | /      |

### 3.4商品信息表

| 商品信息表 |         |          |              |      |        |
| ---------- | ------- | -------- | ------------ | ---- | ------ |
| 编号       | 字段名  | 中文注释 | 字段类型     | 约束 | 初始值 |
| 1          | G_id    | 商品编号 | int          | 主键 | 自增   |
| 2          | G_name  | 商品名   | varchar(20)  | 非空 | /      |
| 3          | G_price | 商品单价 | double       | 非空 | 非负   |
| 4          | G_pic   | 商品图片 | varchar(255) | /    | /      |

### 3.5快递公司信息表

| 快递公司信息表 |          |          |             |      |        |
| -------------- | -------- | -------- | ----------- | ---- | ------ |
| 编号           | 字段名   | 中文注释 | 字段类型    | 约束 | 初始值 |
| 1              | E_id     | 公司编号 | int         | 主键 | 自增   |
| 2              | E_acount | 账户名   | varchar(32) | 非空 | /      |
| 3              | E_pwd    | 密码     | varchar(32) | 非空 | /      |
| 4              | E_name   | 公司名   | varchar(32) | 非空 | /      |
| 5              | E_email  | 邮箱     | varchar(32) | /    | /      |
| 6              | E_phone  | 电话     | Varchar(16) | /    | /      |

### 3.6用户购物车表

| 用户购物车表 |        |          |          |           |        |
| ------------ | ------ | -------- | -------- | --------- | ------ |
| 编号         | 字段名 | 中文注释 | 字段类型 | 约束      | 初始值 |
| 1            | U_id   | 用户编号 | int      | 主键,外键 | /      |
| 2            | G_id   | 商品编号 | int      | 外键      | /      |
| 3            | Amount | 商品数量 | int      | 非空/非负 | /      |

### 3.7用户订单表

| 用户订单表 |        |          |          |           |        |
| ---------- | ------ | -------- | -------- | --------- | ------ |
| 编号       | 字段名 | 中文注释 | 字段类型 | 约束      | 初始值 |
| 1          | O_id   | 订单编号 | int      | 主键,外键 | /      |
| 2          | U_id   | 用户编号 | int      | 外键      | /      |

### 3.8店铺订单表

| 店铺订单表 |        |          |          |           |        |
| ---------- | ------ | -------- | -------- | --------- | ------ |
| 编号       | 字段名 | 中文注释 | 字段类型 | 约束      | 初始值 |
| 1          | O_id   | 订单编号 | int      | 外键      | /      |
| 2          | S_id   | 店铺编号 | int      | 外键      | /      |
| 3          | G_id   | 商品编号 | int      | 外键      | /      |
| 4          | Amount | 购买量   | int      | 非空/非负 | /      |

### 3.9订单配送表

| 订单配送表 |        |          |          |           |        |
| ---------- | ------ | -------- | -------- | --------- | ------ |
| 编号       | 字段名 | 中文注释 | 字段类型 | 约束      | 初始值 |
| 1          | O_id   | 订单编号 | int      | 主键,外键 | /      |
| 2          | E_id   | 快递公司 | int      | 外键      | /      |

### 3.10商品存货表

| 商品存货表 |        |          |          |           |        |
| ---------- | ------ | -------- | -------- | --------- | ------ |
| 编号       | 字段名 | 中文注释 | 字段类型 | 约束      | 初始值 |
| 1          | G_id   | 商品编号 | int      | 主键,外键 | /      |
| 2          | S_id   | 店铺编号 | int      | 外键      | /      |
| 3          | Amount | 存货量   | int      | 非空/非负 | /      |

### 3.11商品浏览表

| 商品浏览表 |        |          |              |      |        |
| ---------- | ------ | -------- | ------------ | ---- | ------ |
| 编号       | 字段名 | 中文注释 | 字段类型     | 约束 | 初始值 |
| 1          | U_id   | 用户编号 | int          | 外键 | /      |
| 2          | G_id   | 商品编号 | int          | 外键 | /      |
| 3          | Time   | 浏览时间 | Varchar(255) | /    | /      |

### 3.12收件地址表

| 收件地址表 |             |            |          |           |
| ---------- | ----------- | ---------- | -------- | --------- |
| 编号       | 名称        | 中文注释   | 字段类型 | 约束      |
| 1          | id          | 用户的编号 | int      | 外键/非空 |
| 2          | province    | 省         | string   | 非空      |
| 3          | detail_addr | 详细地址   | string   | 非空      |
| 4          | tel         | 联系电话   | string   | 非空      |
| 5          | rev_name    | 收件人姓名 | string   | 非空      |
| 6          | note        | 备注       | string   |           |

### 3.13用户收藏表

| 用户收藏表 |        |          |          |           |
| ---------- | ------ | -------- | -------- | --------- |
| 编号       | 字段名 | 中文注释 | 字段类型 | 约束      |
| 1          | U_id   | 用户编号 | int      | 主键,外键 |
| 2          | G_id   | 商品编号 | int      | 外键      |

### 3.14管理员信息表

| 管理员信息表 |          |          |             |      |        |
| ------------ | -------- | -------- | ----------- | ---- | ------ |
| 编号         | 字段名   | 中文注释 | 字段类型    | 约束 | 初始值 |
| 1            | id       | 公司编号 | int         | 主键 | 自增   |
| 2            | username | 账户名   | varchar(32) | 非空 | /      |
| 3            | E_pwd    | 密码     | varchar(32) | 非空 | /      |

### 3.15用户日志表

| 用户日志表 |           |          |        |           |
| ---------- | --------- | -------- | ------ | --------- |
| 编号       | 名称      | 中文注释 | 类型   | 约束      |
| 1          | id        | 日志编号 | int    | 主键/自增 |
| 2          | user_id   | 用户编号 | int    | 外键      |
| 3          | identity  | 用户类型 | string |           |
| 4          | log_type  | 日志类型 | string | 非空      |
| 5          | log_date  | 添加时间 | string | 非空      |
| 6          | log_value | 日志内容 | string | 非空      |

## 六、主要技术论述

### 6.2后端

后端主要使用`SpringBoot`框架+`MyBatis-Plus`操作数据库的组合进行前后端交互与管理数据库。

#### 6.2.1WEB框架

后端主要使用`SpringBoot`框架。`Spring Boot`是所有基于 `Spring` 开发的项目的起点。`Spring Boot`的设计是为了尽可能快的跑起来`Spring` 应用程序并且尽可能减少配置文件。其具有快速创建独立运行的`Spring`项目以及与主流框架集成；使用嵌入式`Servlet`容器，应用不需要打包成`war`；`starts`自动依赖与版本控制；无需大量配置，简化开发等优点。

#### 6.2.2数据库操作

操作数据库主要使用`MyBatis-Plus`。`MyBatis-Plus` (opens new window)（简称 MP）是一个 `MyBatis` (opens new window)的增强工具，在 `MyBatis` 的基础上只做增强不做改变，为简化开发、提高效率而生。`MyBatis`是一款优秀的数据持久层ORM框架，被广泛地应用于应用系统。`MyBatis`能够非常灵活地实现动态SQL，可以使用XML或注解来配置和映射原生信息，能够轻松地将Java的POJO（Plain Ordinary Java Object，普通的Java对象）与数据库中的表和字段进行映射关联。而`MyBatis-Plus`在`MyBatis`的基础上做了进一步的集成，通过`MyBatis-Plus`封装的函数可以直接使用常用的数据库操作，提高了开发效率。

后端还使用了 `Lombok` 。`Lombok` 是一个Java库，能自动插入编辑器并构建工具，简化Java开发。通过添加注解的方式，不需要为类编写 `setter/getter、equals、canEqual、hashCode、toString` 方法，同时可以自动化日志变量，使代码更加简洁。`MyBatis-Plus`也支持`Lombok`注解，配合使用效率更高。

#### 6.2.3开发流程

根据前端不同的请求编写接口，比如只更改商品的某个属性，或者更新商品的所有属性。还需要返回前端规定好的数据类型，比如返回商品的所有属性，需要连接属性表和属性名表。重要的是和前端同学交流好需求，按照需求返回查询的结果，更改数据库的数据项。后端主要实现了四类接口。

首先是商家接口的实现。商家的功能主要是对自己的商品进行增删改查，具体包括上架商品，编辑商品属性，更改商品价格，增加库存等。还包括查询与自己有关的订单并根据订单联系快递公司生成运输订单，即发货的过程。还有对自己商店信息的更新。

用户接口需要实现浏览查询商品，将商品加入购物车或收藏夹，生成订单，编辑自己的用户信息、查看历史订单等。

快递公司接口需要管理快递订单信息，在将货物送到某一个地方后，需要更新运送订单的当前状态。

用户管理的接口，需要获取用户的信息，支持用户的注册，更改密码，对用户的权限进行限制。

最后还有管理员的接口，以管理员权限查阅用户日志，分析不同商家信息。

## 九、源程序简要说明

### 9.2 后端部分

* `controller` 文件夹下文件主要用来管理后端业务。
* `service` 管理具体功能，在`service`中放置接口提供给controller使用，在`service/serviceImpl`中放置具体实现类，起到分离开发的作用
* `mapper` 主要完成和数据库相关的增删改查工作。
* `entity` 中包含了所有实体型。
* `dto` 文件指定统一接口封装格式，`dto/input`为输入的接口格式，`dto/output`为输出的接口格式。
* `util` 文件对正常返回和异常返回进行了格式化封装，用于返回响应码。

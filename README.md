#开发文档
## 目录说明
### 目录
<!-- TOC -->

- [1. web_project](#)
  - [1.1. src](#)
    - [1.1.1. controller](#)
      - [1.1.1.1 AccountServlet](#)
      - [1.1.1.2 CollectionServlet](#)
      - [1.1.1.3 LoginServlet](#)
      - [1.1.1.4 LogoutServlet](#)
    - [1.1.2. dao ](#)
      - [1.1.2.1 AccountDao](#)
      - [1.1.2.2 CollectionDao](#)
      - [1.1.2.3 ItemDao](#)
      - [1.1.2.4 JDBCUtil](#)
      - [1.1.2.5 Dao](#)
    - [1.1.3. service](#)
      - [1.1.3.1 AccountService](#)
      - [1.1.3.2 CollectionService](#)
      - [1.1.3.3 ItemService](#)
    - [1.1.4. bean](#)
      - [1.1.3.1 Collection](#)
      - [1.1.3.2 Item](#)
      - [1.1.3.3 User](#)
  - [1.2. web](#12-appgeo)
    - [1.2.1. rooter](#)
      - [1.2.1.1 rooter.admin.jsp](#)
      - [1.1.1.2 rooter.default.jsp](#)      
    - [1.2.2. templates](#)
      - [2.1.2.1 css](#)
      - [2.1.2.2 img](#)
      - [2.1.2.3 js](#)
      - [2.1.2.4 videos](#)
    - [1.2.3. WEB-INF](#)
    - [1.2.3. all.inc.jsp](#)
    - [1.2.1. collections.jsp](#)
    - [1.2.2. header.inc.jsp](#)
    - [1.2.3. index.jsp](#)
    - [1.2.3. item.jsp](#)
    - [1.2.1. login.jsp](#)
    - [1.2.2. message.inc.jsp](#)
    - [1.2.3. search.jsp](#)
    - [1.2.3. test.html](#)
<!-- /TOC -->
### dao包
#### JDBCUtil类
  建立jdbc连接的类。jdbcUrl为jdbc:mysql://111.231.218.101:3306/webproject，用户名为root，密码为126LiuJia$，驱动程序为com.mysql.jdbc.Driver。
存在方法返回连接。
#### Dao类
  通用Dao，抽象类。负责获得连接以及实现伪生命周期方法init和destroy。
#### ItemDao类
  继承自Dao，负责和artworks数据库表交互的类。可以通过展品id获得详细的展品信息，也可以通过用户的id获得他所收藏的所有展品。提供存储一个展品到数据库的功能，目前待完成。
#### Collection类
  继承自Dao，负责和collectiosn数据库表交互的类。可以通过用户的id获得他所收藏的所有展品的展品id。提供了一个将收藏存入到数据库表的功能save，提供了一个删除数据库表中一个收藏记录的功能delete。
#### Account类
   继承自Dao，负责和users数据库表交互的类。可以通过用户的id获得他的详细信息。提供存储一个用户信息到数据库表的功能，目前待完成。
## 通用组件
### 关于提示框
#### jsp
提示框的jsp为message.inc.jsp，采用了bootstrap的模态框控件。
#### js
提示框的js为message.inc.js，这里面定义了一个函数，showMessage(message,type=2)。第一个参数为提示内容，第二个参数为提醒类型。默认为2代表提醒或通知，0代表警告，1代表成功。
### 关于ajax请求
ajax请求的实现函数全部位于templates/js/api/下，这里使用了ajax的延迟对象来进行回调。index.js里面含有所有请求的url，和封装了ajax请求的方法axios。这个目录下其他的脚本文件分别是具体请求的配置。使用要求：请把所有请求的url放到index.js下的api里面，并且不要使用自己的ajax请求，调用函数axio(option)。
###


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
### bean包
数据载体的bean，目前有Collection(一个收藏记录），Item(一个展品），User(一个用户）。
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
### controller包
  控制处理请求的servlet类集合。
### service包
  向下控制和ben包以及dao包交互，向上被servlet调用。
### web/rooter目录
  负责控制路由
#### rooter.admin.jsp
  判断用户权限是否为管理员，内部已经调用rooter.default.jsp
#### rooter.default.jsp
  判断用户是否登录。
### web/templates/js
#### /api
  集合了后台的数据交互接口和ajax异步请求封装函数，建议所有的ajax异步请求全部写在这里面。index.js里面放置了后台接口的Url, 还放置了封装了ajax请求和函数axios(里面使用了jquery的延迟对象)。
#### message.inc.js
  通用组件提示框的控制脚本，已被加载到message.inc.jsp里面了。
#### header.inc.js
  通用导航栏的控制脚本，已被加载到header.inc.jsp中。
#### 其他
  其他的脚本为各个页面的控制脚本。
### web/xxx.jsp
#### all.inc.jsp
  这是每个页面都需要加载的jsp，目前里面包含header.inc.jsp和message.inc.jsp。
#### test.html
  这个是用来调试的html,主要是用来调整UI美观的。因为tomcat服务器启动耗时较长，不建议每次重启来调整UI。建议方法，在out目录下的test.html里面调试UI,可以直接在本地浏览器中查看效果。缺点是不能编译java代码。
## 通用组件
### 关于提示框
#### jsp
提示框的jsp为message.inc.jsp，采用了bootstrap的模态框控件。
#### js
提示框的js为message.inc.js，这里面定义了一个函数，showMessage(message,type=2)。第一个参数为提示内容，第二个参数为提醒类型。默认为2代表提醒或通知，0代表警告，1代表成功。
### 关于ajax请求
ajax请求的实现函数全部位于templates/js/api/下，这里使用了ajax的延迟对象来进行回调。index.js里面含有所有请求的url，和封装了ajax请求的方法axios。这个目录下其他的脚本文件分别是具体请求的配置。使用要求：请把所有请求的url放到index.js下的api里面，并且不要使用自己的ajax请求，调用函数axio(option)。
###


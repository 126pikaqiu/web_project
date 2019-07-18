#开发文档
## 目录说明

## 通用组件
### 关于提示框
#### jsp
提示框的jsp为message.inc.jsp，采用了bootstrap的模态框控件。
#### js
提示框的js为message.inc.js，这里面定义了一个函数，showMessage(message,type=2)。第一个参数为提示内容，第二个参数为提醒类型。默认为2代表提醒或通知，0代表警告，1代表成功。
### 关于ajax请求
ajax请求的实现函数全部位于templates/js/api/下，这里使用了ajax的延迟对象来进行回调。index.js里面含有所有请求的url，和封装了ajax请求的方法axios。这个目录下其他的脚本文件分别是具体请求的配置。使用要求：请把所有请求的url放到index.js下的api里面，并且不要使用自己的ajax请求，调用函数axio(option)。
###


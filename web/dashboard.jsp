<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="Bookmark" href="dashboard/favicon.ico" >
<link rel="Shortcut Icon" href="dashboard/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="dashboard/lib/html5shiv.js"></script>
<script type="text/javascript" src="dashboard/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="dashboard/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="dashboard/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="dashboard/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="dashboard/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="dashboard/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="dashboard/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>在线博物馆</title>
</head>
<body>

<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-user">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 个人中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="dashboard/user-show.html" data-title="个人信息" href="javascript:;">个人信息</a></li>
					<li><a data-href="user-modify.html" data-title="修改个人信息" href="javascript:;">修改个人信息</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i> 好友管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="member-list.html" data-title="好友列表" href="javascript:;" >好友列表</a></li>
					<li><a data-href="feedback-list.html" data-title="好友留言" href="javascript:void(0)">好友留言</a></li>
					<li><a data-href="member-list.html" data-title="添加好友" href="javascript:;">添加好友</a></li>
					<li><a data-href="member-list.html" data-title="好友添加请求" href="javascript:;">好友添加请求</a></li>
				</ul>
		</dd>
	</dl>
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="member-list.html" data-title="会员列表" href="javascript:;">会员列表</a></li>
					<li><a data-href="admin-list.html" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
					<li><a data-href="admin-role.html" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
					<li><a data-href="admin-permission.html" data-title="权限管理" href="javascript:void(0)">权限管理</a></li>
					<li><a data-href="product-list.html" data-title="产品管理" href="javascript:void(0)">展品管理</a></li>
			</ul>
		</dd>
	</dl>
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="个人信息" data-href="welcome.html">个人信息</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="dashboard/welcome.html"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="dashboard/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="dashboard/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="dashboard/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="dashboard/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="dashboard/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}


</script>
</body>
</html>
$ctx = "/security/console_";

$(function() {
	
	// 动态菜单数据     
	var sysJson = [ {
		text : '系统管理',
		children : [ {
			iconCls : 'icon_home',
			text : '首页',
			attributes : {
			//menucode  :'home'
			}
		}, {
			text : '用户管理',
			attributes : {
				menucode : 'userInfo'
			}
		}, {

			text : '菜单管理',
			attributes : {

			}
		}, {

			text : '角色管理',
			attributes : {

			}
		}, {

			text : '权限分配',
			attributes : {

			}
		} ]
	} ];
	
	var platformJson = [ {
		text : '平台管理',
		state : 'closed',
		children : [ {
			text : '资源管理',
			attributes : {
			//menucode  :'home'
			}
		}, {
			text : '订单管理',
			attributes : {}
		}, {

			text : '账单管理',
			attributes : {

			}
		}, {

			text : '产品管理',
			attributes : {

			}
		} ]
	} ];

	// 实例化树形菜单(系统菜单)    
	$("#sys_menu").tree({
		data : sysJson,
		lines : true,
		onClick : function(node) {
			if (node.attributes) {
				Open(node.text, node.attributes.menucode);
			}
		},
		onLoadSuccess : function(node, data) {
			$("#sys_menu li:eq(1)").find("div").addClass("tree-node-selected"); //设置第一个节点高亮  
			var n = $("#sys_menu").tree("getSelected");
			if (n != null) {
				$("#sys_menu").tree("select", n.target); //相当于默认点击了一下第一个节点，执行onSelect方法  
			}
		}
	});
	
	// 实例化树形菜单  (平台菜单)  
	$("#platform_menu").tree({
		data : platformJson,
		lines : true,
		onClick : function(node) {
			if (node.attributes) {
				Open(node.text, node.attributes.menucode);
			}
		},
		onLoadSuccess : function(node, data) {
			/* $("#platform_menu li:eq(1)").find("div").addClass("tree-node-selected");   //设置第一个节点高亮  
			var n = $("#tree_menu").tree("getSelected");  
			if(n!=null){  
			     $("#platform_menu").tree("select",n.target);    //相当于默认点击了一下第一个节点，执行onSelect方法  
			}   */
		}
	});
	
	// 在右边center区域打开菜单，新增tab     
	function Open(text, menucode) {
		if ($('#tabs').tabs('exists', text)) {
			$('#tabs').tabs('select', text);
		} else {
			$('#tabs').tabs(
					'add',
					{
						title : text,
						closable : true,
						content : menucode != null ? newIframe($ctx + menucode,
								menucode) : text,
					/* tools: [{
					    iconCls: 'icon-mini-refresh',
					    handler: function () { 
					    	document.getElementById('menucode').contentWindow.location.reload(true);
					    }
					}] */
					});
		}
	}
	
	//创建一个ifame
	function newIframe(url, menucode) {
		var iframeStr = "<iframe id='" + menucode + "' src='" + url
				+ "' style='width:100%;height:99.47%;border:none;'></iframe>";
		return iframeStr;
	}
	
	// 绑定tabs的右键菜单     
	$("#tabs").tabs({
		onContextMenu : function(e, title) {
			e.preventDefault();
			$('#tabsMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data("tabTitle", title);
		}
	});

	// 实例化menu的onClick事件     
	$("#tabsMenu").menu({
		onClick : function(item) {
			CloseTab(this, item.name);
		}
	});
	
	// 几个关闭事件的实现      
	function CloseTab(menu, type) {
		var curTabTitle = $(menu).data("tabTitle");
		var tabs = $("#tabs");
		if (type === "close") {
			tabs.tabs("close", curTabTitle);
			return;
		}
		var allTabs = tabs.tabs("tabs");
		var closeTabsTitle = [];

		$.each(allTabs, function() {
			var opt = $(this).panel("options");
			if (opt.closable && opt.title != curTabTitle && type === "Other") {
				closeTabsTitle.push(opt.title);
			} else if (opt.closable && type === "All") {
				closeTabsTitle.push(opt.title);
			}
		});

		for ( var i = 0; i < closeTabsTitle.length; i++) {
			tabs.tabs("close", closeTabsTitle[i]);
		}
	}
	
	
	
});


function loginout() {
	$.messager.confirm('退出平台', '确定要退出系统，不多待一会！', function(flag) {
		if (flag) {
			window.location.href = '/security/loginout';
		}
	});
};






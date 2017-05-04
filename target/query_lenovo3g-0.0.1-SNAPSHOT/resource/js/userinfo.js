//设置时间  
$('title', window.parent.document).text($('title').text());

//初始化方法
function initTable(params) {
	$('#userInfo_list')
			.datagrid(
					{
						url : '/security/loadInfo',
						title : '用户信息列表',
						//rownumbers : false,
						fitColumns : true,
						pagination : true,
						loadMsg : '数据加载中...',
						singleSelect : false,
						fit : true,
						toolbar : '#tb',
						height : 'auto',
						collapsible : true,//是否可折叠的 
						//sortName:'createDate',
						//sortOrder:'desc',
						queryParams : params,
						pageList : [ 10, 15, 30, 50 ],//可以设置每页记录条数的列表  
						columns : [ [ {
							field : 'id',
							checkbox : true
						}, {
							field : 'username',
							title : '姓名',
							width : 40,
							align : 'center'
						}, {
							field : 'account',
							title : '账号',
							width : 40,
							align : 'center'
						}, {
							field : 'password',
							title : '密码',
							width : 50,
							align : 'center'
						}, {
							field : 'descrit',
							title : '职责描述',
							width : 100,
							align : 'center',
							formatter : function(val) {
								if (val == null || val == '') {
									return '暂无职责描述';
								} else {
									return val;
								}
							}
						}, {
							field : 'sex',
							title : '性别',
							width : 30,
							align : 'center',
							sortable : true,
							formatter : function formatSex(value) {
								if (value == null || value == '') {
									return '';
								} else if (value == 1) {
									return '男';
								} else if (value == 0) {
									return '女';
								}
							}
						}, {
							field : 'createDate',
							title : '创建时间',
							width : 60,
							align : 'center',
							sortable : true,
							formatter : function(value) {
								if (value == null || value == '') {
									return '暂无修改记录';
								}
								return value.substring(0, 19);
								//return  new Date(value).format('yyyy-MM-dd hh:mm:ss');
							}
						}, {
							field : 'updateDate',
							title : '修改时间',
							width : 60,
							sortable : true,
							align : 'center',
							formatter : function(value) {
								if (value == null || value == '') {
									return '暂无修改记录';
								}
								return value.substring(0, 19);
								//return  new Date(value).format('yyyy-MM-dd hh:mm:ss');
							}
						}, {
							field : 'status',
							title : '用户状态',
							width : 50,
							sortable : true,
							align : 'center',
							formatter : function(value) {
								if (value == 1) {
									return '<font color=blue>正常</font>';
								} else {
									return '<font color=red>禁用</font>';
								}
							}
						}, {
							title : '操作',
							field : '_opt',//不对应数据库或json字段，取的名字
							width : 40,
							align : 'center',
							formatter : formatOperate
						} ] ],
						loadFilter : function(data) {
							return data;
						},
						onLoadSuccess : function(data) {
							if (data.rows.length == 0) {
								$('.datagrid-body')
										.html(
												'<div style="width:100%;text-align:center;border-bottom:1px;">暂无查询到您需要的数据</div>');
							}
							$('.editcls').linkbutton({
								plain : true
							/* ,iconCls:'icon-edit' */});
						},
						onSortColumn : function(sort, order) {
							//alert("sort:"+sort+",order："+order+"");
						},
						pageSize : 15,
						onBeforeRefresh : function() {
							$(this).pagination('loading');
							$(this).pagination('loaded');
						}
					});
};


//初始化查询出参数
function initQuery() {
	var queryParams = {};
	if ($.trim($('#beginDate').datebox('getValue')) != '') {
		queryParams.beginDate = $.trim($('#beginDate').datebox('getValue'));
	}
	if ($.trim($('#finishDate').datebox('getValue')) != '') {
		queryParams.finishDate = $.trim($('#finishDate').datebox('getValue'));
	}
	if ($.trim($('#username').val()) != '') {
		queryParams.username = $.trim($('#username').val());
	}
	if ($.trim($('#account').val()) != '') {
		queryParams.account = $.trim($('#account').val());
	}
	if ($.trim($('#status').combobox('getValue')) != '') {
		queryParams.status = $.trim($('#status').combobox('getValue'));
	}
	return queryParams;
};

initTable({}); //页面首次加载

//查询
function queryUser() {
	initTable(initQuery());
};

//刷新DataGrid
function reloadDataGrid() {
	$('#userInfo_list').datagrid('load').datagrid('clearSelections');
};

//操作
function formatOperate(value, rec, index) {
	var btn = null;
	if (rec.status == 1) {
		btn = '<a class="editcls" style="color:red" href="javaScript:void(0);" onclick="setStatus('
				+ index + ')">禁用</a>';
	} else {
		btn = '<a class="editcls" style="color:blue" href="javaScript:void(0);" onclick="setStatus('
				+ index + ')">启用</a>';
	}
	return btn;
};

//禁用启用
function setStatus(index) {
	$('#userInfo_list').datagrid('selectRow', index);
	var row = $('#userInfo_list').datagrid('getSelected');
	if (row) {
		var str = (row.status == 0) ? '启用' : '禁用';
		var account = row.account;
		$.messager.confirm('确认操作', '你确定要' + str + '账号为<font color="red">'
				+ account + '</font>的用户?', function(flag) {
			if (flag) {
				$.getJSON('/security/setStatus', {
					id : row.id,
					status : row.status == 0 ? 1 : 0
				}, function(resposne) {
					if (resposne.status == 0) {
						$.messager.show({
							title : '操作提示',
							msg : '账号为' + account + str + '成功',
							timeout : 3000,
							showType : 'slide'
						});
						reloadDataGrid();
					} else {
						$.messager
								.alert('操作结果', '操作失败' + resposne.msg, 'error');
					}
				});
			}
		});
	}
}

//打开弹框
function openDialog(type, title) {
	$('#dialog').window({
		title : title
	});
	if (type == 'add') {
		$('#userFrom .easyui-textbox').textbox('setValue', '');
		$('#userFrom input[name=id]').val('');
		//$('#userFrom .easyui-combobox').textbox('setValue',''); 
		$('#dialog').window('open');
	} else if (type == 'edit') {
		var records = $('#userInfo_list').datagrid('getSelections');
		if (records.length == 1) {
			$('#userFrom').form('load', records[0]);
			
			$('#dialog').window('open');
			
			$("#password").textbox("clear");
			
			$("#accountid").textbox("readonly",true);
		} else {
			$.messager.alert('提示', '请先选择一个数据进行修改！', 'warning');
		}
	}
};

//删除用户
function delUserInfo() {
	var records = $('#userInfo_list').datagrid('getSelections');
	if (records.length > 0) {
		$.messager.confirm('确认操作', '您确定要删除选中的<font color=red><b>'
				+ records.length + '</b>条</font>数据吗？', function(flag) {
			if (flag) {
				var ids = [];
				for ( var i = 0; i < records.length; i++) {
					ids.push(records[i].id);
				}
				$.getJSON('/security/delete', $.param({ids : ids}, true), function(response) {
					if (response.status == 0) {
						$.messager.show({
							title : '操作提示',
							msg : '删除成功',
							timeout : 3000,
							showType : 'slide'
						});
						reloadDataGrid();
					} else {
						$.messager.alert('操作结果', response.msg, 'error');
					}
				});
			}
		});
	} else {
		$.messager.alert('提示', '请先选择至少一个数据进行删除！', 'warning');
	}
};

//保存或者修改数据
function saveOrUpdate() {

	if ($('input[name=username]').val() == ''
			|| $('input[name=username]').val() == null) {
		$.messager.alert('验证框', '姓名不能为空', 'warning');
		return;
	}
	if ($('input[name=account]').val() == ''
			|| $('input[name=account]').val() == null) {
		$.messager.alert('验证框', '账号不能为空', 'warning');
		return;
	}
	if ($('input[name=password]').val() == ''
			|| $('input[name=password]').val() == null) {
		$.messager.alert('验证框', '密码不能为空', 'warning');
		return;
	}
	if ($('#statusbox').combobox('getValue') == ''
			|| $('#statusbox').combobox('getValue') == null) {
		$.messager.alert('验证框', '用户状态不能为空', 'warning');
		return;
	} 
	$.getJSON('/security/edit', $('#userFrom').serialize(), function(response) {
		if (response.status == 0) {
			$.messager.show({
				title : '操作提示',
				msg : response.msg,
				timeout : 3000,
				showType : 'slide'
			});
			$('#dialog').window('close');
			reloadDataGrid();
		} else {
			$.messager.alert('操作结果', response.msg, 'error');
		}
		
		
		$("#accountid").textbox("readonly",false);
	});
};

//刷新页面
function reloadPage() {
	$('#userInfo_list').datagrid('reload').datagrid('clearSelections');
};

function myformatter(date) {
	return date.format('yyyy-MM-dd');
}

function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
















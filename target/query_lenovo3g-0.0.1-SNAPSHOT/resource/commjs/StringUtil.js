//判断字符串是否空
function isNotBlank(str){
	str=$.trim(str);
	if(str==null || str==''){
		return false;
	}else{
		return true;
	}
};

function isBlank(str){
	str=$.trim(str);
	if(str==null || str==''){
		return true;
	}else{
		return false;
	}
};
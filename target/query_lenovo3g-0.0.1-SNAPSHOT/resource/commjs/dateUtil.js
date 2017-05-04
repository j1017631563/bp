/* *
 *
 * 日期的操作 JS
 * 
 * */ 
//全局变量
var DateObj = new Date();
/**
 * 给当前日期 加 或者 减
 * @param {Object} 整数
 */
function DateAddOrDel(obj){
	DateObj.setDate(DateObj.getDate() + obj)
	return DateObj.toLocaleDateString(); 
};
/**
 * 日期转换
 * @param {Object} eg:'yyyy-mm-dd'
 */
Date.prototype.format = function(format){  
	var o = {  
	"M+" : this.getMonth()+1, //month  
	"d+" : this.getDate(), //day  
	"h+" : this.getHours(), //hour  
	"m+" : this.getMinutes(), //minute  
	"s+" : this.getSeconds(), //second  
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter  
	"S" : this.getMilliseconds() //millisecond  
	}  
  
	if(/(y+)/.test(format)) {  
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));  
	}  
  
	for(var k in o) {  
		if(new RegExp("("+ k +")").test(format)) {  
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? 
		o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}  
	}  
	return format;  
};
  
/*
时间格式化工具
把Long类型的yyyy-MM-dd日期还原yyyy-MM-dd hh:mm:ss格式日期 
*/
function dateFormatUtil(longTypeDate){
	var dateTypeDate = "";
	var date = new Date();
	date.setTime(longTypeDate);
	dateTypeDate += date.getFullYear();   //年
	dateTypeDate += "-" + getMonth(date); //月 
	dateTypeDate += "-" + getDay(date);   //日
	dateTypeDate += " " + getHH(date);    //时
	dateTypeDate += ":" + getMM(date);    //分
	dateTypeDate += ":" + getSS(date);    //秒
	return dateTypeDate;
};

//返回 01-12 的月份值 
function getMonth(date){
	var month = "";
	month = date.getMonth() + 1; //getMonth()得到的月份是0-11
	if(month<10){
		month = "0" + month;
	}
	return month;
};

//返回01-30的日期
function getDay(date){
	var day = "";
	day = date.getDate();
	if(day<10){
		day = "0" + day;
	}
	return day;
};
function getHH(date){
	var hh = "";
	hh = date.getHours();
	if(hh<10){
		hh = "0" + hh;
	}
	return hh;
};
function getMM(date){
	var mm = "";
	mm = date.getMinutes();
	if(mm<10){
		mm = "0" + mm;
	}
	return mm;
};
function getSS(date){
	var ss = "";
	ss = date.getSeconds(); 
	if(ss<10){
		ss = "0" + ss;
	}
	return ss;
};

var pid=null;

//cookie
(function(e){typeof define=="function"&&define.amd?define(["jquery"],e):typeof exports=="object"?e(require("jquery")):e(jQuery)})(function(e){function n(e){return u.raw?e:encodeURIComponent(e)}function r(e){return u.raw?e:decodeURIComponent(e)}function i(e){return n(u.json?JSON.stringify(e):String(e))}function s(e){e.indexOf('"')===0&&(e=e.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,"\\"));try{return e=decodeURIComponent(e.replace(t," ")),u.json?JSON.parse(e):e}catch(n){}}function o(t,n){var r=u.raw?t:s(t);return e.isFunction(n)?n(r):r}var t=/\+/g,u=e.cookie=function(t,s,a){if(s!==undefined&&!e.isFunction(s)){a=e.extend({},u.defaults,a);if(typeof a.expires=="number"){var f=a.expires,l=a.expires=new Date;l.setTime(+l+f*864e5)}return document.cookie=[n(t),"=",i(s),a.expires?"; expires="+a.expires.toUTCString():"",a.path?"; path="+a.path:"",a.domain?"; domain="+a.domain:"",a.secure?"; secure":""].join("")}var c=t?undefined:{},h=document.cookie?document.cookie.split("; "):[];for(var p=0,d=h.length;p<d;p++){var v=h[p].split("="),m=r(v.shift()),g=v.join("=");if(t&&t===m){c=o(g,s);break}!t&&(g=o(g))!==undefined&&(c[m]=g)}return c};u.defaults={},e.removeCookie=function(t,n){return e.cookie(t)===undefined?!1:(e.cookie(t,"",e.extend({},n,{expires:-1})),!e.cookie(t))}})

//Float Div
jQuery.fn.floatdiv=function(location){var isIE6=false;var Sys={};var ua=navigator.userAgent.toLowerCase();var s;(s=ua.match(/msie ([\d.]+)/))?Sys.ie=s[1]:0;if(Sys.ie&&Sys.ie=="6.0"){isIE6=true;}var windowWidth,windowHeight;if(self.innerHeight){windowWidth=self.innerWidth;windowHeight=self.innerHeight;}else if(document.documentElement&&document.documentElement.clientHeight){windowWidth=document.documentElement.clientWidth;windowHeight=document.documentElement.clientHeight;}else if(document.body){windowWidth=document.body.clientWidth;windowHeight=document.body.clientHeight;}return this.each(function(){var loc;var wrap=$("<div></div>");var top=-1;if(location==undefined||location.constructor==String){switch(location){case("rightbottom"):loc={right:"0px",bottom:"0px"};break;case("leftbottom"):loc={left:"0px",bottom:"0px"};break;case("lefttop"):loc={left:"0px",top:"0px"};top=0;break;case("righttop"):loc={right:"0px",top:"0px"};top=0;break;case("middletop"):loc={left:windowWidth/2-$(this).width()/2+"px",top:"0px"};top=0;break;case("middlebottom"):loc={left:windowWidth/2-$(this).width()/2+"px",bottom:"0px"};break;case("leftmiddle"):loc={left:"0px",top:windowHeight/2-$(this).height()/2+"px"};top=windowHeight/2-$(this).height()/2;break;case("rightmiddle"):loc={right:"0px",top:windowHeight/2-$(this).height()/2+"px"};top=windowHeight/2-$(this).height()/2;break;case("middle"):var l=0;var t=0;l=windowWidth/2-$(this).width()/2;t=windowHeight/2-$(this).height()/2;top=t;loc={left:l+"px",top:t+"px"};break;default:location="rightbottom";loc={right:"0px",bottom:"0px"};break;}}else{loc=location;alert(loc.bottom);var str=loc.top;if(typeof(str)!='undefined'){str=str.replace("px","");top=str;}}if(isIE6){if(top>=0){wrap=$("<div style=\"top:expression(documentElement.scrollTop+"+top+");\"></div>");}else{wrap=$("<div style=\"top:expression(documentElement.scrollTop+documentElement.clientHeight-this.offsetHeight);\"></div>");}}$("body").append(wrap);wrap.css(loc).css({position:"fixed",z_index:"1999"});if(isIE6){wrap.css("position","absolute");$("body").css("background-attachment","fixed").css("background-color","#fff");}$(this).appendTo(wrap);});};


//$_GET
function get(par){var local_url=document.location.href;var get=local_url.indexOf(par+"=");if(get==-1){return '';}var get_par=local_url.slice(par.length+get+1);var nextPar=get_par.indexOf("&");if(nextPar!=-1){get_par=get_par.slice(0,nextPar);}return decodeURI(get_par.replace("#",""));}



function query(sql){
	$.ajax({
	type:"POST",
	cache: false,
	async:false,
	url:"ajax.php?action=query",
	dataType:"html",
	data:{query:sql},
	error:function(){alert("数据错误，操作被取消！");},
	success:function(data)
	{
	    if(data=="Error"){alert("系统错误，操作被取消！");return;}
        alert(data);
	}
	});
}


function update(table,set,where)
{
	var strURL="action=update&table="+table+"&set="+escape(set)+"&where="+escape(where);
	//window.clipboardData.setData("Text",getRootPath()+"/ajax.php?"+strURL);      //方便IE调试
	$.ajax({
	type:"GET",
	async:false,
	cache: false,
	url:"/ajax.php",
	dataType:"html",
	data:strURL,
	error:function(){alert("数据错误，操作被取消！");},
	success:function(data)
	{
	    if(data=="Error"){alert("系统错误，操作被取消！");return;}
		if(data.substring(0,5)=="error"){alert(data);}
	}
	});	
}


function delete_table(tablename,id)
{
	//if(!confirm('是否要删除？')){return;}
	var strURL="action=delete&table="+tablename+"&id="+id;
	$.ajax({
	type:"GET",
	cache: false,
	url:"/ajax.php",
	dataType:"html",
	data:strURL,
	error:function(){alert("数据错误，操作被取消！");},
	success:function(data)
	{
		if(data=="Error"){alert("系统错误，操作被取消！");return;}
	}
	});
}


function get_data(table,where)
{
	v="";
	var strURL="action=select&table="+table+"&where="+escape(where);
	$.ajax({
	type:"GET",
	cache: false,
	async:false,
	url:"/ajax.php",
	dataType:"html",
	data:strURL,
	error:function(){alert("数据错误，操作被取消！");},
	success:function(data)
	{
	    if(data=="Error"){alert("系统错误，操作被取消！");return;}
		v=data;
	}
	});
	return v;
}



function insert(table,field,values)
{
	v="";
	var strURL="action=insert&table="+table+"&field="+field+"&values="+escape(values);
	//window.clipboardData.setData("Text",getRootPath()+"/ajax.php?"+strURL);      //方便IE调试
	$.ajax({
	type:"GET",
	cache: false,
	async:false,
	url:"/ajax.php",
	dataType:"html",
	data:strURL,
	error:function(){alert("数据错误，操作被取消！");},
	success:function(data)
	{
	    if(data=="Error"){alert("系统错误，操作被取消！");return;}
		v=data;
		if(data.substring(0,5)=="error"){alert(data);}
	}
	});
	return v;
}


$().ready(function(e) {

$(".left_menu_item a").each(function(index, element) {
	var url=location.pathname.replace(/(.+)[\\/]/,'');
    if($(this).attr('href')==url | $(this).attr('href').replace('.php','_add.php')==url)
	{
		$(this).addClass('active');
	}
});
});
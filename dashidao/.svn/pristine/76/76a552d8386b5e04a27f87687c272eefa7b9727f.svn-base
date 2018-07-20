
var YunJi = {
	appUA:"",//app传过来的UserAgent,
	initRem:function(){
		/*rem控制不同屏幕显示大小*/
		window.onresize=function(){
			document.documentElement.style.fontSize = window.innerWidth/7.5 + 'px';
		};
		document.addEventListener('DOMContentLoaded',function(){
			document.documentElement.style.fontSize = window.innerWidth/7.5 + 'px';
		},false);
		document.documentElement.style.fontSize = window.innerWidth/7.5 + 'px';
		/*end*/

        $('.slides').css('height',$('body').width()+'px');
	},
	getUrlParams: function(name) {//获取url参数
		var ps = decodeURI(location.search);
		if (ps == '') return '';
		var params = ps.substring(1).split('&');
		var param = [];
		for (var i = 0; i < params.length; i++) {
			var temp = params[i].split('=');
			param[temp[0]] = temp[1];
		}
		return param[name];
	},
	getSurplus:function(t){//倒计时用
		var d=0;
	    var h=0;
	    var m=0;
	    var s=0;
		   var str = "";
	    if(t>=0){
	      d=Math.floor(t/1000/60/60/24);
	      h=Math.floor(t/1000/60/60%24);
	      m=Math.floor(t/1000/60%60);	
	      s=Math.floor(t/1000%60);
	    }
//		if(d>0){
			str+= d + "天";
//		}
		str+= h + "时" + m + "分"+s + "秒"
		return str;
	},
	getSurplu:function(t){//倒计时用 不显示秒
				var d=0;
		    var h=0;
		    var m=0;
			var str = "";
		    if(t>=0){
		      d=Math.floor(t/1000/60/60/24);
		      h=Math.floor(t/1000/60/60%24);
		      m=Math.floor(t/1000/60%60);	
		    }
			str+= d + "天" + h + "时" + m + "分";
			return str;
		},
	getLimitTime:function(t){
		var month = 0;
		var d=0;
		var h=0;
		var m=0;
		var str = "";
	/*	if(t>=0){
			d=Math.floor(t/1000/60/60/24);
			h=Math.floor(t/1000/60/60%24);
			m=Math.floor(t/1000/60%60);
			/!*s=Math.floor(t/1000%60);*!/
		}*/
		var newTime = new Date(t);
		month = newTime.getMonth()+1;
		d= newTime.getDate();
		h = newTime.getHours();
		m = newTime.getMinutes();
		str+= month + "月" + d + "日  "+h + "点" + m + "分";
		return str;
	},
	isSupportSticky:function() {
		var prefixTestList = ['', '-webkit-', '-ms-', '-moz-', '-o-'];
	    var stickyText = '';
	    for (var i = 0; i < prefixTestList.length; i++ ) {
	        stickyText += 'position:' + prefixTestList[i] + 'sticky;';
	    }
	    // 创建一个dom来检查
	    var div = document.createElement('div');
	    var body = document.body;
	    div.style.cssText = 'display:none;' + stickyText;
	    body.appendChild(div);
	    var isSupport = /sticky/i.test(window.getComputedStyle(div).position);
	    body.removeChild(div);
	    div = null;
	    return isSupport;
	},
//	basePath:"//t.yunjibuyer.com/yunjibuyer"
	basePath:"//"+window.location.host+"/yunjibuyer",
	title:"",
	desc:"",
	imgUrl:"",
	shareLink:""
}
var appUA = navigator.userAgent;//app的useragent信息
if(appUA && appUA.indexOf('{"appVersion') != -1){
	YunJi.appUA = JSON.parse(appUA.substring(appUA.indexOf('{')));
}

YunJi.initRem();//初始化计算rem数值


/**	
 * new Date(time).format('yyyy-MM-dd hh:mm:ss')
 * @param {Object} fmt
 */
Date.prototype.format = function(fmt){
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}


//提示框，待优化，旧代码
var showHtml,
	html = "<div class=\"mod-spinner\">" + "        <div class=\"mod-overlay\"></div>" + "        <div class=\"mod-spinner__inner\">{#icon#}<p class=\"mod-spinner__text\">{#text#}</p></div>" + "    </div>",
	iconConfig = {
		"loading": '<p class="mod-spinner__spinner"></p>',
		"warn": '<p class="mod-spinner__info-icon"></p>',
		"success": '<p class="mod-spinner__success-icon"></p>'
	};

function showBubble(config) {
	if (showHtml) {
		return;
	}
	// 不传递的情况下，使用loading的状态
	var option = {
			icon: "loading",
			text: "正在加载...",
			autoHide: true,
			showTime: 1000
		},
		iconHtml;
	$.extend(option, config);
	iconHtml = iconConfig[option.icon];
	// 找不到对应的icon
	if (!iconHtml) {
		return;
	}
	// 替换html结构，转换成zepto对象
	showHtml = $(html.replace(/{#icon#}/, iconHtml).replace(/{#text#}/, option.text));
	$(document.body).append(showHtml);
	$(window).trigger("bubble:show");
	if (option.autoHide) {
		setTimeout(function() {
			closeBubble();
		}, option.showTime);
	}
}

function closeBubble() {
	if (showHtml) {
		showHtml.fadeOut(1000);
		showHtml = null;
		$(window).trigger("bubble:close");
	}
};
/**
 * sku弹窗js
 * @create by 张欢
 * @date 2016-12-08
 */
var ERR_CODE = "0"; //0代表请求成功的code

//注册alert提示的组件
Vue.component('alert-component', {
	template: '#alert',
	data:function(){
		return {
			showAlertFlag: false, //是否显示alert标志位
			alertTip: "", //alert提示语句
			btnText: "" //alert按钮文字
		};
	},
	methods: {
		/**
		 * 显示alert提示
		 * @param tip 提示语句
		 * @param btnText 按钮文字
		 * @param callback 回调函数
		 */
		showAlert: function(tip,btnText,callback){
			this.showAlertFlag = true;
			this.alertTip = tip;
			this.btnText = btnText;
			if(callback){
				callback();
			}
		},
		/**
		 * 隐藏alert提示
		 */
		hideAlert: function(){
			this.showAlertFlag = false;
			this.alertTip = "";
			this.btnText = "";
		}
	}
});

//注册toast提示的组件
Vue.component('toast-component', {
    template: '#toast',
    data:function(){
        return {
            showToastFlag: false, //是否显示toast标志位
            toastTip: "", //toast提示语句
            toastIconClass: "" //toast提示的不同状态，根据状态显示不同的图标
        };
    },
    methods: {
        /**
         * 显示toast提示
         * @param tip 提示语句
         * @param duration 显示的时长
         * @param icon 返回的图标状态；当icon=success：成功，当icon=warning:警告，当icon=""不显示图标
         * @param callback 回调函数
         */
        showToast: function(tip,duration,icon,callback){
            this.showToastFlag = true;
            this.toastTip = tip;
            this.toastIconClass = icon || "";
            var that = this;
            this.$nextTick(function(){
                $(".toast").css({marginLeft: - Math.round($(".toast").outerWidth() / 2) + 'px'});
                setTimeout(function() {
                    if(callback) {
                        callback();
                    }
                    that.hideToast();
                }, duration || 2000);
            }.bind(this));
        },
        /**
         * 隐藏toast提示
         */
        hideToast: function(){
            this.showToastFlag = false;
            this.toastTip = "";
            this.toastIconClass = "";
        }
    }
});


// 注册sku组件，此方式为全局注册方式
Vue.component('sku-component', {
	template: '#sku',
	data:function() {
        return {
            isSharePage: isSharePage,
            windowHeight:0, //窗口的高度
            showSkuBigImg:false, //sku大图展示
            isFirstLoad: true, //是否是第一次加载页面，true为第一次
            currSelectInfo:{
                currSkuImg: '',//当前所选sku的对应图片
                currStock: 0, //当前所选规格对应的库存
                currTypeText: '',//当前所选的规格
                currStockPrice: 0 //当前所选规格对应的价格
            },
            defaultSelectInfo:{
                defaultSkuImg: '',//默认所选sku的对应图片
                defaultStock: 0, //默认所选规格对应的库存
                defaultTypeText: '',//默认所选的规格
                defaultStockPrice: 0 //默认所选规格对应的价格
            },
            buyNumber: 1, //购买的数量
            activityTime: 0, //活动倒计时
            //bankRatePrice: 0, //税率价格 （规格对应云集价 X 购买数量 X 税率）
            itemCache: [], //当前的商品sku对象信息
            skuList: [], //商品SKU属性列表
            secondSkuTypeList:[],//二级规格的sku数组
            isAllAvailable:true,//二级规格标题是否全部显示,true显示
            availSkuList: [] //可用的skuList
        };
	},
	props: {
		skuObj:{ //父组件传过来的sku对象
			type: Object
		}
	},
	computed:{
        /**
         * 跨境商品：显示税率
         * 根据购买数量，改变税率
         */
        bankRatePrice: function(){
            //税率价格 （规格对应云集价 X 购买数量 X 税率）
            var price = (this.currSelectInfo.currStockPrice * this.buyNumber).toFixed(2);
            var bankRatePrice;
            //未选中规格时，显示“请选择规格”
            if(this.getCurrAllSelectSkuType().count > 0){
                bankRatePrice = (Math.ceil(price * (this.itemCache.bankRate / 10000) * 100) / 100).toFixed(2);
                bankRatePrice = "¥" + bankRatePrice;
            }else{
                bankRatePrice = "请选择规格";
            }
            return bankRatePrice;
        }
	},
	methods: {
		/**
		 * 显示sku弹窗
		 */
		openSkuDialog: function(){
            //小屏幕手机兼容最大高度
            if($(window).height() < 568){
                $("#sku-list").removeClass("sku-height").addClass("sku-new-height");
            }
            this.skuObj.skuIfShowFlag = true;
            this.$nextTick(function () {
                $('html,body,.content,.home-content,.detail-wrap').addClass('overhidden');
        	});
		},
		/**
		 * 点击隐藏sku弹窗
		 */
		hideSkuDialog: function(){
            $('html,body,.content,.home-content,.detail-wrap').removeClass('overhidden');
            this.skuObj.skuIfShowFlag = false;
            //刷新购物车的数量
            // if(this.skuObj.pageName == "home"){
            //     //App.vue中的方法
            //     this.$root.$children[0].refreshCartNum();
            // }else{
            //     this.$parent.newRefreshCart();
            // }
		},
        /**
         * 减商品
         * 库存为0，已下架商品不可点击
         */
        minusShopNum: function(event){
            if($(event.target).hasClass("disabled")  || this.skuObj.disabled == 1){
                return;
            }else{
                if(this.buyNumber <= 1){
                    this.buyNumber = 1;
                }else{
                    this.buyNumber--;
                }
            }

        },
        /**
         * 加商品
         * ①、库存为0，已下架商品不可点击
         * ②、超过50件以及限购的数量
         * ③、购买的数量超过当前规格的库存
         * 提示“超出购买数量"
         */
        addShopNum: function(){
            if($(event.target).hasClass("disabled") || this.skuObj.disabled == 1){
                return;
            }else{
                //如果toast提示展示，等toast消失才能继续响应事件，防止重复点击
                if(this.$parent.$refs.toast.showToastFlag){
                    return;
                }
                if(this.buyNumber > 50 ||  (this.itemCache.itemPurchaseMax != 0 && this.buyNumber > this.itemCache.itemPurchaseMax) || this.buyNumber >= this.currSelectInfo.currStock){
                    this.$parent.$refs.toast.showToast("超出购买数量");
                    return;
                }else{
                    this.buyNumber++
                }
            }
        },
		/**
		 * 底部按钮
		 * 点击底部按钮购买商品前，判断是否选择了规格，并且根据不同按钮类型，执行不同购买方法
		 * @param type = add     加入购物车
		 * 		  type = buyNow  立即购买
		 */
		validateSkuLen: function(type){
            //如果toast提示展示，等toast消失才能继续响应事件，防止重复点击
            if(this.$parent.$refs.toast.showToastFlag){
                return;
            }
            //sku选中的个数
            var count = this.getCurrAllSelectSkuType().count;
            //sku类型个数
            var len = this.getCurrAllSelectSkuType().len;
            if(count == 0){
                this.$parent.$refs.toast.showToast("请选择规格");
                return;
            }
            else if(count < len && count > 0){
                var str = "";
                $(".sku-ul-list:not('.hook')").each(function(){
                    console.log($(this))
                    str += $(this).prev("p").text() + ",";
                });
                str = str.substring(0,str.length-1);
                this.$parent.$refs.toast.showToast("请选择"+str);
                return;
            }
            else {
                switch (type) {
                    case "add": //加入购物车
                        this.addCart();
                        break;
                    case "buyNow":  //立即购买
                        this.buyNow();
                        break;
                }
            }
		},
		/**
		 *  立即购买按钮
		 */
		buyNow: function(){
			/**
			 * appCont
			 * 0:H5,   1:卖家版,    2:买家版，    3:数字专柜
			 * 其中（0,3展示页面一致，只是后台传值时区分清楚）
			 */
            var appCont = YunJi.getUrlParams('appCont');
            appCont = parseInt(appCont) || 0;
            var that = this;
            //跨境商品 itemChannel为3、4、5、6
            if (this.itemCache.itemChannel >= 3) {
                var afterPrice = parseFloat(that.currSelectInfo.currStockPrice); //当前sku规格对应的价格
                var buyNum = that.buyNumber; //当前购买数量
                switch (that.itemCache.itemChannel) {
                    case 3:
                    case 6:
                        if (1 <= buyNum && 2000 < (afterPrice * buyNum)) {
                            this.$parent.$refs.alert.showAlert('<p>根据海关政策,</p><p>单笔订单<b>不能超过2000元</b>哦</p><p>可分开下单~</p>','确定', '');
                            return;
                        }
                        break;
                    case 4:  //海外直邮，保留原来逻辑
                        if (1 < buyNum && 1000 < (afterPrice * buyNum)) {
                            this.$parent.$refs.alert.showAlert('<p>根据海关政策,购买保税商品</p><p>单个订单金额<b>不能大于1000元</b></p><p>请减少数量再购买吧。</p>','确定', '');
                            return;
                        }
                        break;
                    case 5:
                        if (1 <= buyNum && 800 < (afterPrice * buyNum)) {
                            this.$parent.$refs.alert.showAlert('<p>为了减少退运风险,</p><p>单笔订单<b>不要超过800</b>,</p><p>请分开下单哦~</p>', '确定', '');
                            return;
                        }
                        break;
                }
            }
            var params = this.skuObj.itemId + ":" + this.getAvailSkuObj(this.getCurrAllSelectSkuType().skuType).outItemId + ":" + this.buyNumber + "&appCont=" + appCont + "&shopId=" + this.skuObj.shopId + "&discoverId=" + discoverId;

            var reto = {
                recruitId:recruitUserId,
                token:token
            }
            localStorage.setItem("recruitId_token",JSON.stringify(reto));

            if(ispackageItem==4){//店主续费
                if(appCont == 1){//卖家版
                    location.href = "yunji:purchaseForShopKeeper//buyNow/dealConfirm.xhtml?content="+params;
                }else{
                    location.href = "//"+buyerPath+"/dealConfirm.xhtml?content="+this.skuObj.itemId + ":" + this.getAvailSkuObj(this.getCurrAllSelectSkuType().skuType).outItemId+":1;&appCont=0&shopId="+ this.skuObj.shopId + "&discoverId=" + discoverId;
                }
            }else{
            	if(appCont == 1){//店主邀请码注册
            		location.href = "yunji://purchaseForRecruit/buying?url="+"processConfirm.xhtml?content="+this.skuObj.itemId + ":" + this.getAvailSkuObj(this.getCurrAllSelectSkuType().skuType).outItemId + ":" + this.buyNumber+"&shopId="+this.skuObj.shopId+"&token="+token+"&discoverId="+discoverId;
            	}else{
            		if(shoppe == 1){//数字专柜
                        location.href="recruit/ounter.xhtml?userId="+recruitUserId+"&shopId="+this.skuObj.shopId+"&content="+this.skuObj.itemId+":"+this.getAvailSkuObj(this.getCurrAllSelectSkuType().skuType).outItemId+":"+this.buyNumber;
                    }else{
                        if(notWeChat == 1){//qq微博注册店主
                            location.href="processConfirm.xhtml?content="+this.skuObj.itemId + ":" + this.getAvailSkuObj(this.getCurrAllSelectSkuType().skuType).outItemId + ":" + this.buyNumber+"&shopId="+this.skuObj.shopId+"&token="+token+"&discoverId="+discoverId;
                        }else{//微信注册店主
                            location.href="dealConfirm.xhtml?content="+this.skuObj.itemId + ":" + this.getAvailSkuObj(this.getCurrAllSelectSkuType().skuType).outItemId+":"+this.buyNumber+"&shopId="+this.skuObj.shopId+"&discoverId="+discoverId;
                        }
                    }
            	}
                
            }
		},
		/**
		 * 加入购物车按钮
		 */
		addCart:function(){
			/**
			 * appCont
			 * 0:H5,   1:卖家版,    2:买家版，    3:数字专柜
			 * 其中（0,3展示页面一致，只是后台传值时区分清楚）
			 */
			var appCont = YunJi.getUrlParams('appCont');
			appCont = parseInt(appCont) || 0;
			//商品加入购物车缓存
			this.addCartStorage();

			if(appCont == 1 && YunJi.appUA.appVersion && YunJi.appUA.appVersion >= 2){
				//加入购物车 确定按钮卖家版埋点,
				location.href = "yunji:purchaseForShopKeeper//report_itemdetail_cart";
			}
		},
		/**
		 * 商品加入购物车缓存
		 * 商品数据添加至localStorage缓存
		 */
		addCartStorage: function(){
			//如果toast提示展示，等toast消失才能继续响应事件，防止重复点击
			if(this.$parent.$refs.toast.showToastFlag){
				return;
			}

			var that = this;
			var buyerItem = {
				"itemId": this.skuObj.itemId,  //商品id
				"barCode": this.getAvailSkuObj(this.getCurrAllSelectSkuType().skuType).outItemId, //商品条码
				"buyNum": this.buyNumber, //购买数量
				"itemImg": this.itemCache.mainImg, //主图
				"itemName": this.itemCache.name, //名字
				"price": this.currSelectInfo.currStockPrice, //价格
				"itemModel": this.getCurrAllSelectSkuType().skuType, //净含量:50ml;颜色:灰色
				"checked":true //是否勾选
			};
			//console.log(buyerItem);

			//获取缓存中购物车列表
			var cartData = $.extend({
				list: []
			}, util.getStorage('cart_list'));

			var hasAdd = false;  //是否添加过缓存
			var purchaseLimit = false; //是否超过限购
			var stockLimit = false;  //是否超过最大库存

			cartData.list.forEach(function(obj){
				if (obj != null && (obj.barCode == buyerItem.barCode)) { //该商品已经添加至缓存
					//超过限购量
					if (that.itemCache.itemPurchaseMax != 0 && (parseInt(obj.buyNum) + parseInt(buyerItem.buyNum)) > that.itemCache.itemPurchaseMax) {
						purchaseLimit = true;
						return false;
					}
					//超过库存数
					if ((parseInt(obj.buyNum) + parseInt(buyerItem.buyNum)) > that.currSelectInfo.currStock) {
						stockLimit = true;
						return false;
					}
					//已添加至购物车的商品，直接修改商品数量
					obj.buyNum = parseInt(obj.buyNum) + parseInt(buyerItem.buyNum);
					hasAdd = true;
					obj.checked = true;
				} else { //商品未添加过至缓存
					//超过限购量
					if (that.itemCache.itemPurchaseMax != 0 && buyerItem.buyNum > that.itemCache.itemPurchaseMax) {
						purchaseLimit = true;
						return false;
					}
					//超过库存数
					if (buyerItem.buyNum > that.currSelectInfo.currStock) {
						stockLimit = true;
						return false;
					}
				}
			});
			if (purchaseLimit) {
				this.$parent.$refs.toast.showToast("限购" + that.itemCache.itemPurchaseMax + "件");
				return;
			}
			if (stockLimit) {
				this.$parent.$refs.toast.showToast("超过商品最大库存数");
				return;
			}

			//未添加购物车商品，添加至缓存
			if (!hasAdd) {
				cartData.list.unshift(buyerItem);
			}
			util.setStorage('cart_list', cartData);

			//底部购物车徽章badge数量改变
			$('.cart_number').text(cartData.list.length);
			if ($('.cart_number').text() != '') {
				$('.cart_number').addClass('badge1');
			}

			this.$parent.$refs.toast.showToast("加入购物车成功");
			this.hideSkuDialog(); //关闭sku弹窗
		},
		/**
		 * 如果当前选中的数据在可用的sku规格里面,返回当前可用sku规格对象
		 * 如果当前所有类型规格都选中了一个，并且不在可选的sku信息中，这期和产品沟通，当库存为0处理
		 * @param 当前所有sku选中的类型
         * @returns {{}}
         */
		getAvailSkuObj: function(val){
			var obj = {};
			if(val in this.availSkuList) { //选择的规格在可用的sku规格内
				obj = this.availSkuList[val];
			}else{ //选择的规格不在可用范围内
				var len = this.getCurrAllSelectSkuType().len; //len 有几种规格类型
				var count = this.getCurrAllSelectSkuType().count; //count 选中的规格的个数
				//只有所有类型的规格都选中，并且不在可用规格内，显示的库存为0，否则显示为之前的选中库存
				obj = {
					"skuSmallImg": this.currSelectInfo.currSkuImg,
					"stockCount": len == count ? 0 : this.currSelectInfo.currStock,
					"stockPrice": this.currSelectInfo.currStockPrice
				}
			}
			return  obj;
		},
		/**
		 * 点击当前sku事件，选择的sku规格,根据不同规格展示不同的商品信息
         * @param event 当前点击的对象
         */
		chooseSkuType: function(event){
			$(event.target).parent().addClass("hook"); //父级ul增加一个hook样式，仅供后面判断使用
			$(event.target).addClass("active").siblings().removeClass("active");
			this.resetSkuInfoBySelect(); //根据选择不同的sku规格，重新设置对应的sku信息
		},
		/**
		 * 根据选择不同的sku规格，重新设置对应的sku信息
		 */
		resetSkuInfoBySelect:function(){
			var that = this;
            this.setSecondSkuAvailable();
            that.$nextTick(function(){
                var firstLen = $(".firstSkuType li.active").length; //二级规格选中的个数
				var secondLen = $(".secondSkuType li.active").length; //二级规格选中的个数
				var secondLiLen = $(".secondSkuType li").length; //二级规格li的个数
				var currSelectSkuType = that.getCurrAllSelectSkuType().skuType; //获取当前所有sku选中的类型
				var currAvailSkuObj = that.getAvailSkuObj(currSelectSkuType); //根据获取当前所有sku选中的类型，获取当前可用sku规格对象
				var currSelectCount = that.getCurrAllSelectSkuType().count; //选中的规格的个数
				//console.log("currSelectCount="+currSelectCount+"==firstLen="+firstLen+"==secondLen="+secondLen+"===secondLiLen="+secondLiLen);
				//如果2级规格都匹配上，显示匹配对应的库存和按钮状态，否则显示页面初始化进来的默认库存和按钮状态;如果只有一级规格则，直接去匹配规格
                that.currSelectInfo = {
					currSkuImg: currSelectCount == 0 || currAvailSkuObj.skuSmallImg == "" || (firstLen == 1 && secondLen == 0 && secondLiLen > 0 && that.isAllAvailable) || (firstLen == 0 && secondLen == 1 && secondLiLen > 0 && that.isAllAvailable) ? that.defaultSelectInfo.defaultSkuImg : currAvailSkuObj.skuSmallImg, //当前所选规格对应图片
					currStock: currSelectCount == 0 || (firstLen == 1 && secondLen == 0 && secondLiLen > 0 && that.isAllAvailable) || (firstLen == 0 && secondLen == 1 && secondLiLen > 0 && that.isAllAvailable) ? that.defaultSelectInfo.defaultStock : currAvailSkuObj.stockCount, //当前所选规格对应的库存
					currStockPrice: currSelectCount == 0 || (firstLen == 1 && secondLen == 0 && secondLiLen > 0 && that.isAllAvailable) || (firstLen == 0 && secondLen == 1 && secondLiLen > 0 && that.isAllAvailable) ? that.defaultSelectInfo.defaultStockPrice : currAvailSkuObj.stockPrice,//当前所选规格对应的价格
					currTypeText: currSelectCount == 0 ? "" : that.getCurrAllSelectSkuType().skuText //当前所有sku选中的文字
				}
				//购买的数量，根据库存，库存为0变灰，并且按钮不可点击
                that.buyNumber = that.currSelectInfo.currStock == 0 ? 0 : 1;
			}.bind(that));
		},
        /**
         * 获取当前所有sku选中规格相关数据
         * skuType的类型，拼接为：”净含量:10ML;分类:日霜,净含量:10ML;分类:晚霜“格式
         * skuText所选规格文字，拼接为: “10ML 日霜”
         * count 选中的规格的个数
         * len 有几种规格类型
         */
        getCurrAllSelectSkuType:function(){
            var obj = {},str = '',text = '',count = 0,len = 0;
            $(".sku-ul-list li.active").each(function(){
                //str += $(this).data("skutype")+":"+$(this).text()+";"; //此写法会有缓存，sku的类别名会是上一个sku的类别名
                str += $(this).parent().prev("p").text()+":"+$(this).text()+";";
                text += $(this).text()+" ";
                count++;
            });
            obj = {
                "skuType": str.substring(0,str.length-1),
                "skuText": text,
                "count": count,
                "len": $(".sku-type-name").length
            }
            return obj;
        },
        /**
         * 当一级规格选中时，去匹配其下，对应的所有二级规格，符合规格的二级规格显示，否则隐藏
         * 拼接为：”净含量:10ML;分类:日霜,净含量:10ML;分类:晚霜“格式
         */
        setSecondSkuAvailable: function(){
        	var that = this;
            var len = $(".firstSkuType li.active").length;
            var secLen = $(".secondSkuType").length; //只能有二级规格，三级规格直接都是“暂无库存”
            if(secLen > 1){
                return;
            }
            if(len > 0){
                var firstActiveText = $(".firstSkuType li.active").parent().prev("p").text()+":"+$(".firstSkuType li.active").text()+";";
                var arr = [];
                $(".secondSkuType li").each(function(){
                    arr.push(firstActiveText + $(this).parent().prev("p").text()+":"+$(this).text());
                });
                arr.forEach(function(item,index){
                    if(item in that.availSkuList) { //选择的规格在可用的sku规格内
						Vue.set(that.secondSkuTypeList[index],"isAvailable",true); //符合的规格显示
					}else{
						Vue.set(that.secondSkuTypeList[index],"isAvailable",false); //不符合规格的隐藏对应的规格
					}
				});
                that.$nextTick(function(){
                    var hideCount = 0; //二级规格隐藏的li个数
                    var showCount = 0; //二级规格显示的li个数
					//切换一级规格时，去除二级规格之前选中的样式
					$(".secondSkuType li").each(function(){
						if($(this).css("display") == "none"){
							$(this).removeClass("active");
							hideCount++;
						}else{
							showCount++;
						}
					});
					//如果二级规格全部都不存在，则二级规格标题也隐藏，否则显示
					this.isAllAvailable = hideCount == that.secondSkuTypeList.length ? false : true;
						var activeLen = $(".secondSkuType li.active").length; //二级规格选中的个数
					if(hideCount == that.secondSkuTypeList.length || activeLen == 0){
						$(".secondSkuType").removeClass("hook"); //去除父级ul选中的hook样式，仅供后面提交时判断使用
					}
					if(showCount == 1){ //如果显示的只有一个二级规格时，默认选中
						$(".secondSkuType li:visible").eq(0).addClass("active");
						$(".secondSkuType").addClass("hook"); //增加父级ul选中的hook样式，仅供后面提交时判断使用
					}
				}.bind(that));
            }
        },
		/**
		 * ajax请求sku数据
		 */
		initSkuData:function(){
			var that = this;
            //请求数据前，取消所有选中样式
            $(".sku-ul-list li").removeClass("active");
            this.isAllAvailable = true;//二级规格标题是否全部显示,true显示
            this.isFirstLoad = false; //第一次加载标志位，true为第一次加载
			$.ajax({
                type:"GET",
                url:  contextPath+"/itemattr.xhtml?t="+new Date().getTime(),
                data:{
					itemId: that.skuObj.itemId,
					shopId: that.skuObj.shopId
				},
                dataType:"json",
				success: function (json) {
					console.info(json);
					closeBubble(); //关闭弹窗提示
					if (json.errorCode == ERR_CODE && json) {
						//由于后台目前可用sku的对应库存价格，都时真实价格的100倍，故需要前端处理
						var tempAvailSku = JSON.parse(json.availSku);
						for(var obj in tempAvailSku){
							tempAvailSku[obj].stockPrice = (tempAvailSku[obj].stockPrice/ 100).toFixed(2);
						};
						that.itemCache = json;  //当前的商品sku对象信息
						that.skuList = json.sku;  //商品SKU属性列表
						that.availSkuList = tempAvailSku; //可用的skuList
						if(that.skuList.length > 1){ //存在二级规格时
							var len = json.sku[1].pList.length;
							var tempList = [];
							for(var i = 0;i < len;i++){
								tempList.push({'isAvailable':true}); //代表是否是可用规格，默认全部可用
							}
							that.secondSkuTypeList = tempList;//二级规格的sku数组
						}
						//限时活动相关提示
						if(json.activityItemStatus == 1 && json.itemCategory == 1){
							this.activityTime = YunJi.getLimitTime(json.startTime);//YunJi.format(json.sku.startTime,'MM-dd hh:mm'); //活动开始时间
						}

						//vue默认是异步渲染，当修改data时，需要nextTick中回调去实现
						that.$nextTick(function(){
							//初始化展示默认的商品相关信息
							that.currSelectInfo = {
								currSkuImg: json.mainImg, //当前所选规格对应图片
								currStock: json.stock, //当前库存
								currStockPrice: parseFloat(json.price).toFixed(2),//当前所选规格对应的价格
								currTypeText: "" //当前所有sku选中的文字
							}
							//设置默认的商品相关信息
							that.defaultSelectInfo = {
								defaultSkuImg: json.mainImg,//默认所选sku的对应图片
								defaultStock: json.stock, //默认所选规格对应的库存
								defaultStockPrice: parseFloat(json.price).toFixed(2), //默认所选规格对应的价格
								defaultTypeText: ""//默认所选的规格
							}
							//只有一个一级规格时
							var firstLiLen = $(".firstSkuType li").length; //一级规格li的个数
							if(firstLiLen == 1){
								$(".firstSkuType li").eq(0).addClass("active");
								$(".firstSkuType").addClass("hook"); //增加父级ul选中的hook样式，仅供后面提交时判断使用
							}
							that.resetSkuInfoBySelect(); //根据选择不同的sku规格，重新设置对应的sku信息
						}.bind(that));

						//打开sku弹窗
						that.openSkuDialog();
					} else {
						that.isFirstLoad = true; //第一次加载标志位，true为第一次加载
						that.responseError(json.errorCode,json.errorMessage);
					}
				},
				error: function () {
					closeBubble(); //关闭弹窗提示
					that.ajaxError();
				}
			})
		},
		/**
		 * ajax请求错误
		 */
		ajaxError: function(){
			setTimeout(function() {
				showBubble({
					icon: "warn",
					text: "网络错误"
				});
			}, 250);
		},
		/**
		 * 后台响应错误
		 */
		responseError: function(errorCode,msg){
			if(errorCode == 32){
				showBubble({
					icon: "warn",
					text: msg
				});
				return;
			}
			showBubble({
				icon: "warn",
				text: "系统繁忙"
			});
		}
	}
});


/**
 * 缓存工具类
 * @create by 张欢
 * @date 2016-12-12
 */
function Util(){

}
Util.prototype = {
	setStorage: function(key, value) { //设置某个缓存
		key = key.replace(/\//g, '_');
		if (value === undefined) return;
		localStorage.setItem(key, JSON.stringify(value));
	},
	getStorage: function(key) {  //获取某个缓存
		key = key.replace(/\//g, '_');
		return JSON.parse(localStorage.getItem(key));
	},
	removeStorage: function(key) { //清除某个缓存
		key = key.replace(/\//g, '_');
		localStorage.removeItem(key);
	},
	clearStorage: function() {  //	清除所有缓存
		localStorage.clear();
	}
}
var util = new Util();



var vmAPP = new Vue({
    el: '#app-wrap',
    data: {
        skuRelatedInfo:{ //sku传入的相关参数
            giftText: '',// 底部按钮文案
            skuIfShowFlag: false, //是否显示sku的标志位
            clickBtnType: 'buyNow',//点击行为的类型
            itemId: itemId, //商品id
            shopId: shopId, //店铺id
            disabled: -1 //是否可用(0-是 1-否),1表示已下架
        }
    },
    created: function () {
        try{
            var appUA = navigator.userAgent; //app的UserAgent信息
            if (appUA && appUA.indexOf('{"appVersion') != -1) {
                appUA = JSON.parse(appUA.substring(appUA.indexOf('{')));
                if (appUA.appClient == 'ios') {
                    this.giftText =  '分享大礼包';
                } else {
                    this.giftText =  '分享开店礼包';
                }
            } else {
                this.giftText =  '分享开店礼包';
            }
        } catch (e){
            this.giftText =  '分享开店礼包';
        }
    },
    methods: {
        toggleSkuDialog: function(){
            this.skuRelatedInfo.disabled = isDisabled; //是否下架
            //父组件调用子组件中的方法，需要在子组件上加v-ref:sku
            //2.0版本及以上的vue.js，子组件的上的v-ref:sku需要修改成ref="sku"
            var isFirstLoad = this.$refs.sku.isFirstLoad;
            if(isFirstLoad){
                this.$refs.sku.initSkuData();  //第一次请求数据
            }else{
                this.$refs.sku.openSkuDialog(); //直接打开sku
            }
        },
        itemShare: function () {
            window.location.href = 'yunji:purchaseForShopKeeper//share';
        }
    }
});

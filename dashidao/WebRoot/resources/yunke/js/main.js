
// 全局名称空间
(function(win){
    win.CLICK = 'click';
    win.G = {};
    // 运行环境
    var ua = navigator.userAgent;
    // Base path
    G.base = location.href.match(/[^?#]*\//)[0];
    // winxin
    G.weixin = /MicroMessenger/.test(ua);
    // 是否调试
    G.debug = !!location.port || location.hostname === 'localhost' || location.protocol === 'file:';
    // 全局参数
    G.params = {};
})(window);


// 加载样式
(function(){
   // var MIN = G.debug ? '' : '.min';
    var MIN =  '';
    getCSS('//static.yunjiweidian.com/css/weidian/base'+ MIN + '.css');
    getCSS('//static.yunjiweidian.com/css/weidian/style'+ MIN + '.css');
    function getCSS(url) {
        var node = document.getElementById('seajsnode'),
            el = document.createElement('link');
        el.rel = 'stylesheet';
        el.href = url;
        node.parentNode.insertBefore(el, node);
    }
})();


// seajs配置
seajs.config({
    //base: './',
    debug: G.debug,
    base: '//static.yunjiweidian.com/js',
    paths: {
        'dist':'src'
    },
    //map: G.debug ? [['.min.js', '.js']] : null,
    alias: {
       // zepto: 'lib/zepto.js',
        jquery: 'common/jquery/2.1.3/jquery.min.js',
        tpl: 'common/tpl/0.3.1/tpl.js',
        fastclick: 'common/fastclick/1.0.3/fastclick.min.js',
        touch: 'common/touch/0.1.0/jquery.event.touch.min.js',
        swipeshow: 'common/swipeshow/0.10.9/jquery.swipeshow.min.js',
        swipe: 'common/swipe/2.0.0/swipe.js',
        util: 'dist/util.js'
    },
    map: [
          [ /^(.*\/src\/.*\.(?:css|js))(?:.*)$/i, '$1?t=201508121843' ]
        ],
    preload: ['jquery']
});
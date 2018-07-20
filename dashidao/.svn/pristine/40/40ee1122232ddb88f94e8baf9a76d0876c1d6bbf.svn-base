function indexFn() {
  require(['jquery', 'echarts'], function($, echarts) {
    console.log(echarts)
      $(function() {
          buildData();
      });

      function buildData() {
          //定义数据结构
          var datas = {
              'colors': ['#006699', '#4cabce', '#e5323e'],
              'xAxis': ['广告之前', '广告期间', ],
              'legend':[],
              'list': [{
                  'title': '日均销量',
                  'series': [[40, 70]],
                  'elid': 'first'
              },

              ]
              
              
          };
          for (var i = 0; i < datas['list'].length; i++) {
              canvasEcharts(datas, i);
          }
      }

      function canvasEcharts(json, index) {
          var obj = json['list'][index];
          var myChats = echarts.init(document.getElementById(obj['elid']));
          var option = {
              title: {
                  text: obj['title'],
                  // subtext: index==0?'数据来自投票结果而时时变化':''  //只有第一个要副标题
                  //主标题文本超链接
                  //link: 'http://www.xxxxxxxxxx',
              },
              color: ['#006699', '#4cabce', '#e5323e'],
              tooltip: {
                  trigger: 'axis',
                  axisPointer: {
                      type: 'shadow'
                  }
              },
              toolbox: {
                  //显示策略，可选为：true（显示） | false（隐藏），默认值为true
                  show: true,
                  //垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
                  y: 'center',
                  feature: {
                      //saveAsImage，保存图片（IE8-不支持）,图片类型默认为'png'
                      saveAsImage: { show: true }
                  }
              },
              legend: {
                  left: "70%",
                  data: ['好', '中', '差']
              },
              grid: {
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true,
              },
              xAxis: [{
                  min: 0,
                  //坐标轴类型，横轴默认为类目型'category'
                  type: 'category',
                  data: json['xAxis']
              }],
              yAxis: [{
                  min: 0,
                  max: 100,
                  //坐标轴类型，纵轴默认为数值型'value'
                  type: 'value'
              }],
              series: (function(){
                  var arr=[];
                  for (var i = 0; i < obj['series'].length; i++) {
                      var thisobj={
                          name: json['legend'][i],
                          type: 'bar',
                          barWidth: '40%',
                      };
                      thisobj.data=obj['series'][i];
                      arr.push(thisobj)
                  }
                  return arr
              })()
          };
          //为echarts对象加载数据
          myChats.setOption(option, true);
      }

  });
}


function indexFn1() {
  require(['jquery', 'echarts'], function($, echarts) {
    console.log(echarts)
      $(function() {
          buildData1();
      });

      function buildData1() {
          //定义数据结构
          var datas1 = {
              'colors': ['#006699', '#4cabce', '#e5323e'],
              'xAxis': ['活动之前', '活动期间', ],
              'legend':[],
              'list': [{
                  'title': '日均销量',
                  'series': [[40, 70]],
                  'elid': 'second'
              },

              ]
              
              
          };
          for (var i = 0; i < datas1['list'].length; i++) {
              canvasEcharts1(datas1, i);
          }
      }

      function canvasEcharts1(json, index) {
          var obj = json['list'][index];
          var myChats = echarts.init(document.getElementById(obj['elid']));
          var option = {
              title: {
                  text: obj['title'],
                  // subtext: index==0?'数据来自投票结果而时时变化':''  //只有第一个要副标题
                  //主标题文本超链接
                  //link: 'http://www.xxxxxxxxxx',
              },
              color: ['#006699', '#4cabce', '#e5323e'],
              tooltip: {
                  trigger: 'axis',
                  axisPointer: {
                      type: 'shadow'
                  }
              },
              toolbox: {
                  //显示策略，可选为：true（显示） | false（隐藏），默认值为true
                  show: true,
                  //垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
                  y: 'center',
                  feature: {
                      //saveAsImage，保存图片（IE8-不支持）,图片类型默认为'png'
                      saveAsImage: { show: true }
                  }
              },
              legend: {
                  left: "70%",
                  data: ['好', '中', '差']
              },
              grid: {
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true,
              },
              xAxis: [{
                  min: 0,
                  //坐标轴类型，横轴默认为类目型'category'
                  type: 'category',
                  data: json['xAxis']
              }],
              yAxis: [{
                  min: 0,
                  max: 100,
                  //坐标轴类型，纵轴默认为数值型'value'
                  type: 'value'
              }],
              series: (function(){
                  var arr=[];
                  for (var i = 0; i < obj['series'].length; i++) {
                      var thisobj={
                          name: json['legend'][i],
                          type: 'bar',
                          barWidth: '40%',
                      };
                      thisobj.data=obj['series'][i];
                      arr.push(thisobj)
                  }
                  return arr
              })()
          };
          //为echarts对象加载数据
          myChats.setOption(option, true);
      }

  });
}

require(['config'], indexFn1);
require(['config'], indexFn);
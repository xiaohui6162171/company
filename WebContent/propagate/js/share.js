 var url = encodeURIComponent(window.location.href, "UTF-8");
 function wechat(url) {
     $.ajax({
       url: '/game/wx/getjsconfig.html',
       type: 'get',
       dataType: 'json',
       data: {
         url: url.split('#')[0]
       },
       async:false,
       success: function(response) {
         wx.config({
           debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
           appId: response.jsconfig.appid, // 必填，公众号的唯一标识
           timestamp: response.jsconfig.timestamp, // 必填，生成签名的时间戳
           nonceStr: response.jsconfig.noncestr, // 必填，生成签名的随机串
           signature: response.jsconfig.signature, // 必填，签名，见附录1
           jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
         });
         wx.ready(function() {
           //获取“分享给朋友”按钮点击状态及自定义分享内容
             wx.onMenuShareAppMessage({
             title: '寻找璞凡之星，青年原创设计大赛！', // 分享标题
             desc: '摇滚创意 爆破青春 原创新星 强势对决', // 分享描述
             link: 'http://www.fengshendesign.com/game/propagate/index.html', // 分享链接
             imgUrl: 'http://www.fengshendesign.com/game/resource/img/share.png' // 分享图标
           });
           //获取“分享到朋友圈”按钮点击状态及自定义分享内容
           wx.onMenuShareTimeline({
             title: '寻找璞凡之星，青年原创设计大赛！', // 分享标题
             desc: '摇滚创意 爆破青春 原创新星 强势对决', // 分享描述
             link: 'http://www.fengshendesign.com/game/propagate/index.html', // 分享链接
             imgUrl: 'http://www.fengshendesign.com/game/resource/img/share.png' // 分享图标
           });
         });
         wx.error(function(res) {
           alert('wx.error: '+JSON.stringify(res));
         });
       },
       error: function(error) {
         wechat(url);
       }
     });
 }
 wechat(url);
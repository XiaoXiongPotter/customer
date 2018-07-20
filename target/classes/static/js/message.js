    function	getSockJS(){
    	var	SockJS;
    	$.ajax({
    		type: "GET", //GET或POST,
    		async:false, //默认设置为true，所有请求均为异步请求。
    		url: "/sockjs",
    		dataType: "text", //xml、html、script、jsonp、text
    		success: function(data) {
    			SockJS=data
    		}
    	})
    	console.log("获取到的SOCKJS"+SockJS);
    	return	SockJS;
    }
    function	getWeSocket(){
    	var	wesocket;
    	$.ajax({
    		type: "GET", //GET或POST,
    		async:false, //默认设置为true，所有请求均为异步请求。
    		url: "/wesocket",
    		dataType: "text", //xml、html、script、jsonp、text
    		success: function(data) {
    			wesocket=data
    		}
    	})
    	console.log("获取到的WEBSOCKET"+wesocket);
    	return	wesocket;
    }
    //发送消息并返回结果
    function	sendMsg(content){
    	var	res;
    	$.ajax({
			type: "POST", //GET或POST,
			async:false, //默认设置为true，所有请求均为异步请求。
			url: "/js/messageses/sendMsg",
			data: {
				formUser: room, 
				toUser : seat,
				postMessages : content
			},
			dataType: "json", //xml、html、script、jsonp、text
			success: function(data) {
				console.log("消息发送")
				console.log(data)
				res	=	data;
			},
			error:function(){alert('eoo')},
		});
    	return	res;
    }
    /* 生成当前时间 */
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    }
    function add0(m){return m<10?'0'+m:m }
    function format(shijianchuo)
    {
    //shijianchuo是整数，否则要parseInt转换
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
    }
	function	alertInfo(content){
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.open({
				  title: '提示',
				  content: content
				});  
			}); 
	}
	function	alertPrompt(content){
		layui.use('layer', function(){
			  var layer = layui.layer;
			  /*layer.open({
				  type: 3
				  ,title: '<i class="layui-icon layui-icon-speaker"></i>'
				  ,content: content
				  ,time:  3000
				  ,anim: 1
				  ,closeBtn: 0
				  ,offset: ['100px', '190px']
				});*/  
			  layer.msg('<p style="color: #FFB800;">'+content+'</p>', {icon: 6}); 
			});
	}
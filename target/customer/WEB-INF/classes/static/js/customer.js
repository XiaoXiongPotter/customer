	function	getSockJS(){
    	var	SockJS;
    	$.ajax({
    		type: "GET",
    		async:false,
    		url: "/sockjs",
    		dataType: "text",
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
    		type: "GET",
    		async:false,
    		url: "/wesocket",
    		dataType: "text",
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
			type: "POST",
			async:false,
			url: "/js/messageses/sendMsg",
			data: {
				formUser: room, 
				toUser : seat,
				postMessages : content
			},
			dataType: "json",
			success: function(data) {
				console.log("消息发送")
				console.log(data)
				res	=	data;
			},
			error:function(){alert('eoo')},
		});
    	return	res;
    }
    function	sendImg(images){
    	var	res;
    	$.ajax({
			type: "POST",
			async:false,
			url: "/js/messageses/sendImg",
			data: {
				formUser: room, 
				toUser : seat,
				postMessages : images
			},
			dataType: "json",
			success: function(data) {
				console.log("消息发送")
				console.log(data)
				res	=	data;
			},
			error:function(){alert('eoo')},
		});
    	return	res;
    }
    function	customerSendImg(images){
    	console.log(seat,tousername,images)
    	var	res;
    	$.ajax({
			type: "POST",
			async:false,
			url: "/js/messageses/sendImg",
			data: {
				formUser: seat, 
				toUser : tousername,
				postMessages : images
			},
			dataType: "json",
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
			  layer.msg('<p style="color: #FFB800;">'+content+'</p>', {icon: 6}); 
			});
	}
	//提示有转接任务
	function	alertTransfer(content){
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.confirm('您有一个转接请求，是否接入？', {
				  btn: ['接入','无视'] //按钮
				}, function(){
					$.ajax({
						type: "POST",
						async:false,
						url: "js/chatrooms/trans_ferAccess",
						data: {
							json:content
						},
						dataType: "json",
						success: function(data) {
							layer.msg('接入成功', {icon: 1});
							var	array	=	data.data;
							
							if(array.length>0){
								$(".chat-body").fadeToggle(1);
								PRINT(array);
								
							}
						},
						error:function(){layer.msg('接入失败', {icon: 1});},
					});
				  
				}, function(){
					$.ajax({
						type: "POST",
						async:false,
						url: "js/chatrooms/deny_Access",
						data: {
							json:content
						},
						dataType: "json",
						success: function(data) {
							if(data.header.status==1000){
								layer.msg('成功拒绝', {icon: 1});
							}
						},
						error:function(){layer.msg('拒绝失败', {icon: 1});},
					});
				  
				}); 
			});
	}
	//客服拒绝转接后的消息提醒
	function	alertDenyAccess(content){
		var data=JSON.parse(content);
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.msg('您的转接被'+data.customer+'拒绝了,请选择其他在线客服进行转接！', function(){
				//关闭后的操作
				  $(".chat-body").fadeToggle(1);
				});
			});
	}
	//下拉刷新
	const start = new Date(new Date(new Date().toLocaleDateString()).getTime());
	var	thatday	=	start.getTime();
	var day =  new Date().getDate();
	var _element = document.getElementById('printMsg'),
    _refreshText = document.querySelector('.show-sk'),
    _startPos = 0,
    _transitionHeight = 0;
    (function(window) {
        _element.addEventListener('touchstart', function(e) {
            //console.log('初始位置：', e.touches[0].pageY);
            _startPos = e.touches[0].pageY;
            _element.style.position = 'relative';
            _element.style.transition = 'transform 0s';
        }, false);

        _element.addEventListener('touchmove', function(e) {
            //console.log('当前位置：', e.touches[0].pageY);
            _transitionHeight = e.touches[0].pageY - _startPos;

            if (_transitionHeight > 0 && _transitionHeight < 60) {
                _refreshText.innerHTML = '<div class="sk-chasing-dots"><div class="sk-child sk-dot1"></div><div class="sk-child sk-dot2"></div></div>';
                _element.style.transform = 'translateY('+_transitionHeight+'px)';
                if (_transitionHeight > 55) {
                    _refreshText.innerHTML = '<div class="sk-double-bounce"><div class="sk-child sk-double-bounce1"></div><div class="sk-child sk-double-bounce2"></div></div>';
                }
            }
        }, false);
        _element.addEventListener('touchend', function(e) {
            _element.style.transition = 'transform 0.5s ease 1s';
            _element.style.transform = 'translateY(0px)';
            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
            refresh();
        }, false);
    })(window);
    
    function refresh(){
    	//这里是后台数据
    	$.ajax({
			type: "POST",
			async:false,
			url: "js/messageses/messageHistory",
			data: {
				userName: seat, 
				thatDay : thatday,
			},
			dataType: "json",
			success: function(data) {
				thatday	=	thatday-(86400000);
		    	console.log(day)
				var	array	=	data.data;
				console.log(array)
				if(array.length>0){
					PRINT(array)
				}
				else{ 
					_refreshText.innerHTML ='';
				}
			},
			error:function(){alert('eoo')},
		});
        
    }
    function	PRINT(array){
    	console.log(array)
			for(var	i=0;i<array.length;i++){
				if(array[i].toUser.substring(0,3)=='PFU'){
					tousername=array[i].toUser;
					console.log(tousername)
				}
				if(array[i].formUser.substring(0,3)==seat.substring(0,3)){
					if(array[i].messageType=='Text'){
						printFormUserMessage(array[i].postMessages,format(array[i].sendTime),array[i].formUser.substring(3))
						if(i==array.length-1){_refreshText.innerHTML ='';}
						else{
				            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
						}
					}else
					if(array[i].messageType=="Picture"){
						printFormUserImage(array[i].postMessages,format(array[i].sendTime),array[i].formUser.substring(3))
						if(i==array.length-1){_refreshText.innerHTML ='';}
						else{
				            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
						}
					}
					
				}else{
					if(array[i].messageType=='Text'){
						printToUserMessage(array[i].postMessages,format(array[i].sendTime),array[i].formUser.substring(3))
						if(i==array.length-1){_refreshText.innerHTML ='';}
						else{
				            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
						}
					}else
					if(array[i].messageType=="Picture"){
						printToUserImage(array[i].postMessages,format(array[i].sendTime),array[i].formUser.substring(3))
						if(i==array.length-1){_refreshText.innerHTML ='';}
						else{
				            _refreshText.innerHTML = '<div class="sk-three-bounce"><div class="sk-child sk-bounce1"></div><div class="sk-child sk-bounce2"></div><div class="sk-child sk-bounce3"></div></div>';
						}
					}
				}
			}
		
    }
	function	printFormUserMessage(str,date,formUserName) {
		var	mineMsg	=	'<li class="layim-chat-mine">'
					+	'<div class="layim-chat-user"> <img src="/layui/images/img/tu2.png">'
					+	'<cite><i>'
					+	date
					+	'</i>'
					+	formUserName
					+	'</cite> </div><div class="layim-chat-text">'
					+	str
					+	'</div>'
					+	'</li>';
		$("#printMsg ul").append(mineMsg);
	}
	function	printFormUserImage(str,date,formUserName){
		var	mineMsg	=	'<li class="layim-chat-mine">'
					+	'<div class="layim-chat-user"> <img src="/layui/images/img/tu2.png">'
					+	'<cite><i>'
					+	date
					+	'</i>'
					+	formUserName
					+	'</cite> </div><div class="layim-chat-text">'
					+	'<img src="'
					+	str  
					+	'"></div>'
					+	'</li>';
		$("#printMsg ul").append(mineMsg);
	}
	function	printToUserMessage(str,date,formUserName){
		var	userMsg	=	'<li>'
					+	'<div class="layim-chat-user"> <img src="/layui/images/img/tu2.png"> <cite class="layim-chat-user-cite">'
					+	formUserName
					+	'<i>'
					+	date
					+	'</i> </cite> </div>'
					+	'<div class="layim-chat-user-text">'
					+	str
					+	'</div></li>'
		$("#printMsg ul").append(userMsg);
	}
	function	printToUserImage(str,date,formUserName){
		var	image	=	'<li>'
					+	'<div class="layim-chat-user"> <img src="/layui/images/img/tu2.png"> <cite class="layim-chat-user-cite">'
					+	formUserName
					+	'<i>'
					+	date
					+	'</i> </cite> </div>'
					+	'<div class="layim-chat-user-text"><img src="'
					+	str
					+	'"></div></li>'
		$("#printMsg ul").append(image);
	}
	function	zhuanjie(customer){
		var	res;
    	$.ajax({
			type: "POST",
			async:false,
			url: "/js/chatrooms/can_transfer",
			data: {
				seat: seat, 
				customer : customer,
				petUser : petUser
			},
			dataType: "json",
			success: function(data) {
				console.log("消息发送")
				console.log(data)
				res	=	data;
			},
			error:function(){alert('eoo')},
		});
    	return	res;
	}
    function	get_login_user(){
    	console.log("用户信息："+petUser)
    	$.ajax({
    		type: "POST",
    		async:false,
    		url: "/js/userChat/get_login_user",
    		data:{
    			username:petUser
    		},
    		dataType: "json",
    		success: function(data) {
    			
    			console.log(data)
    			//photoUrl=data.data.photoUrl;
    			show_user(data.data);
    			photoUrl	=	data.data.photoUrl;
    		}
    	})
    }
    
    function	show_user(data){
    	var	str	=	'<div class="layui-colla-item">'
			+	'<h2 class="layui-colla-title" onclick="zhan('+data.id+')">'
			+	'<i class="layui-icon layui-colla-icon"><img	width="20px;"	height="16px;"	src="'
			+	data.photoUrl
	    	+	'"/></i>'
	    	+	'<span class="layui-badge layui-bg-cyan">'
	    	+	data.nickname
	    	+	'</span></h2><div class="layui-colla-content" id="'+data.id+'">'
	    	+	'<p>'
			+	'<br />"email:"'
			+	data.email
			+	'<br />"country:"'
			+	data.country.name
			+	'<br />注册时间：'
			+	data.registerDate
			+	'</p></div></div>'
    	$("#user").append(str);
    }
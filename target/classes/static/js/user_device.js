
	function	get_all_device(){
		$.ajax({
			type: "POST",
			async:true,
			url: "/js/device/get_all_device",
			data:{username:petUser},
			dataType: "json",
			success: function(data) {
				console.log(data)
				if(data.data!=null){
					var	array	=	data.data;
					if(array.length>0){
						for(var	i=0;i<array.length;i++){
							
							show_device(array[i].device)
						}
					}
				}
				
				
			},
			error:function(data){console.log(data)},
		});
	}
	
	function	show_device(device){
		var	str		=	'<div class="layui-colla-item">'
					+	'<h2 class="layui-colla-title" onclick="showDevice('+device.id+')">'
					/*+	'<i class="layui-icon layui-colla-icon"><img	width="20px;"	height="16px;"	src="'
					+	pet.portrait
			    	+	'"/></i>'*/
			    	+	'<span class="layui-badge layui-bg-cyan">'
			    	+	device.devName
			    	+	'</span></h2><div class="layui-colla-content" id="'+device.id+'">'
			    	+	'<p>'
			    	+	'<br />"deviceCode:"'
					+	device.deviceCode
					+	'"批次："'
					+	device.batchCode
					+	'"是否绑定："'
					+	device.bind
					+	'<br />"上线时间："'
					+	format(device.onlineTime)
					+	'"下线时间："'
					+	format(device.offlineTime)
					+	'<br />"是否在线："'
					+	device.online
					+	'<br />"宠物ID："'
					+	device.petId
					+	'<br />"生产商："'
					+	device.producter
					+	'<br />"产品类型："'
					+	device.type
					+	'<br />"是否支持微信："'
					+	device.wechat
					+	'</p></div></div>'
					
		var	string	=	'<div class="layui-col-xs12 layui-col-sm12"><div class="layuiadmin-card-text"><div class="layui-text-top">'
			+	'<i class="layui-icon">&#xe60c;</i><a href="#">'
			+	device.devName
			+	'</a></div><p class="layui-text-center">'
			+	'<br />"deviceCode:"'
			+	device.deviceCode
			+	'"批次："'
			+	device.batchCode
			+	'"是否绑定："'
			+	device.bind
			+	'<br />"上线时间："'
			+	format(device.onlineTime)
			+	'"下线时间："'
			+	format(device.offlineTime)
			+	'<br />"是否在线："'
			+	device.online
			+	'<br />"宠物ID："'
			+	device.petId
			+	'<br />"生产商："'
			+	device.producter
			+	'<br />"产品类型："'
			+	device.type
			+	'<br />"是否支持微信："'
			+	device.wechat
            +	'</p><p class="layui-text-bottom"><a href="http://www.layui.com/doc/modules/flow.html">区域：</a>'
            +	'<span>'
            +	device.domain
            +	'</span></p></div></div>'

	
		$("#device").append(str);
	}
	
	function	get_all_pet(){
		$.ajax({
			type: "POST",
			async:true,
			url: "/js/device/get_all_pet",
			data:{username:petUser},
			dataType: "json",
			success: function(data) {
				console.log(data)
				if(data.data!=null){
					var	array	=	data.data;
					if(array.length>0){
						for(var	i=0;i<array.length;i++){
							
							show_pet(array[i])
						}
					}
				}
				
				
			},
			error:function(data){console.log(data)},
		});
	}
	
	function	show_pet(pet){
		var	str	=	'<div class="layui-colla-item">'
				+	'<h2 class="layui-colla-title" onclick="zhan('+pet.id+')">'
				+	'<i class="layui-icon layui-colla-icon"><img	width="20px;"	height="16px;"	src="'
				+	pet.portrait
		    	+	'"/></i>'
		    	+	'<span class="layui-badge layui-bg-cyan">'
		    	+	pet.name
		    	+	'</span></h2><div class="layui-colla-content" id="'+pet.id+'">'
		    	+	'<p>'
		    	+	'"character:"'
				+	pet.character
				+	'<br />"color:"'
				+	pet.color
				+	'<br />"gender:"'
				+	pet.gender
				+	'<br />"height:"'
				+	pet.height
				+	'<br />"weight:"'
				+	pet.weight
				+	'<br />"name:"'
				+	pet.name
				+	'<br />"petType:"'
				+	pet.petType
				+	'出生日期：'
				+	format(pet.birthTime)
				+	'</p></div></div>'
		/*var	string	=	'<div class="layui-col-xs12 layui-col-sm6"><div class="layuiadmin-card-text"><div class="layui-text-top">'
				+	'<img	width="20px;"	height="20px;"	src="'
				+	pet.portrait
				+	'" /><a href="#">'
				+	pet.name
				+	'</a></div><p class="layui-text-center">'
				+	'"character:"'
				+	pet.character
				+	'<br />"color:"'
				+	pet.color
				+	'<br />"gender:"'
				+	pet.gender
				+	'<br />"height:"'
				+	pet.height
				+	'<br />"weight:"'
				+	pet.weight
				+	'<br />"name:"'
				+	pet.name
				+	'<br />"petType:"'
				+	pet.petType
                +	'</p><p class="layui-text-bottom"><a href="http://www.layui.com/doc/modules/flow.html">出生日期：</a>'
                +	'<span>'
                +	format(pet.birthTime)
                +	'</span></p></div></div>'*/
              
		$("#pet").append(str);
	}
	var	flag	=	false;
	function	zhan(e){
		if(!flag){
			$("#"+e).addClass("layui-show");
			flag=true
		}else{
			$("#"+e).removeClass("layui-show");
			flag=false
		}
		
	}
	var	deviceFlag	=	false;
	function	showDevice(e){
		if(!deviceFlag){
			$("#"+e).addClass("layui-show");
			deviceFlag=true
		}else{
			$("#"+e).removeClass("layui-show");
			deviceFlag=false
		}
	}
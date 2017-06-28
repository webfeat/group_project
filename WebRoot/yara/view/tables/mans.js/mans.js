(function($){
	var searchCondition = {};
	//初始化
	var init = function(){
		searchCondition['curentPage'] = 1;
		searchCondition['pageNumbers'] = 10;
		$.ajax({
			url:'/group_project/temp/TableServlet',
			type:"post",
			data:{curentPage:1,pageNumbers:10},
			dataType: "json",
			async:true,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(data){
				console.log(data);
				createTr(data);
			},
			error:function(error){
				alert(error);
			}
		});
	}
	//向表格添加行
	var createTr = function(data){
		var tds = [];
		if(data.state == false){
			alert("查询失败");
		}else{
			var datas = data.data;
			for(var row of datas){//遍历数据并加上数据
				tds[0] = '<tr class="gradeA odd"><th>'
					+ row[2] + '</th>';
				tds[1] ='<th>'
					+ row[1] + '</th>';
				tds[2] = '<th >'
					+ row[6] + '</th>';
				tds[3] = '<th>'
					+ row[3] + '</th>';
				tds[4] = '<th>'
					+ row[4] + '</th>';
				tds[5] = '<th>'
					+ transformTime(row[5]) + '</th>';
				var operate = '<td style="width:15%">'+
					'<a href="#" class="btn" data="'+ row[1] +'"><i class="glyphicon glyphicon-pencil" ></i>编辑</a>'
				 	+'</td></tr>';
				var c = tds[0] + tds[1] + tds[2] + tds[3] + tds[4] + tds[5] + tds[6] + operate ;
				$("tbody").append(c);
			}
		}
	}
	
	var transformTime = function(data){
		    var date = new Date(data);
		    console.log(data);
		    if(!data){
		    	return "";
		    }
		    return date.getFullYear().toString() +'-' + (date.getMonth() + 1).toString()+'-' + date.getDate().toString();
//		    return date;
	}
	
	var swichTabs = function(){
		if(window.tabs.indexOf('学生管理') >0){
			
		}else{
			
		}
	}
	
	
	init();
	//定义表格监听
    var table = document.getElementById("table");
    table.addEventListener('click',function(e){
    		if(e.target.nodeName == 'A'){
    			editStaff(e.target.attributes[2].nodeValue);
    		}
    });
   
    //表格上的编辑按钮
	 var editStaff = function(staffId){
		 $.ajax({
				url:'/group_project/temp/StaffServlet',
				type:"post",
				data:{staffId:staffId,operate:'edit'},
				dataType: "json",
				async:true,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					window.gotoHtml("/group_project/yara/view/staffhtml/staffEditForm.html",data);
				},
				error:function(error){
					alert(error);
				}
			});
	 }
	
	 var refresh = function(){
		 
		 $.ajax({
				url:'/group_project/temp/TableServlet',
				type:"post",
				data:{
					curentPage:$("jumpPage").val(),
					pageNumbers:10,
					searchKey:$("#searchCondition").val(),
					searchValue:$("pageNums").val(),
					jumpPage:$("jumpPage").val()
					},
				dataType: "json",
				async:true,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					console.log(data);
					createTr(data);
				},
				error:function(error){
					alert(error);
				}
			});
	 }
	 
	 
})(jQuery)
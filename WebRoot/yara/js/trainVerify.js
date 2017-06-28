(function($){
	var searchCondition = {};
	var init = function(){
		searchCondition['currentPage'] = 1;
		searchCondition['pageNumbers'] = 10;
		$.ajax({
			url:'/group_project/temp/TrainapplyVerify',
			type:"post",
			data:{curentPage:1,pageNumbers:10,operate:'table'},
			dataType: "json",
			async:true,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(list){
				createTr(list);
			},
			error:function(error){
				alert("an error");
			}
		});	
	}
	
	
	//向表格添加行
	var createTr = function(data){
		var arr = data.data;
		var tds = [];
		if(data.state == false){
			alert("查询失败");
		}else{
			console.log(arr);
			for(var row of arr){//遍历数据并加上数据
				var head = '<tr class="gradeA odd">';
				
				tds[0] = '<td>' + row[1] + '</td>';
				tds[1] ='<td>'+ row[2] + '</td>';
				tds[2] = '<td>' + row[6] + '</td>';
				tds[3] = '<td>' + row[3] + '</td>';
				tds[4] = '<td>' + row[4] + '</td>';
				tds[5] = '<td>' + row[5] + '</td>';
				tds[6] = '<td>' + row[7] + '</td>';
				tds[7] = '<td>' + row[8] + '</td>';
				tds[8] = '<td>' + row[9] + '</td>';
				tds[9] = '<td>' + row[10] + '</td>';
				tds[10] = '<td>' + row[11] + '</td>';
				
				var operate = '<td style="width:13%">'+
					'<a href="#" class="btn" data="'+ row[6] +'"><i class="glyphicon glyphicon-pencil"></i>审核</a>'
				 	+'</td></tr>';
				
				var c =head+ tds[0] + tds[1] + tds[2] + tds[3] + tds[4] + tds[5] + tds[6] + tds[7] + tds[8]+ tds[9]+ tds[10] + operate ;
				console.log(c);
				$("#table").append(c);
			}
		}
	}
	
	var swichTabs = function(){
		if(window.tabs.indexOf('机构管理') >0){
			
		}else{
			
		}
	}
	
	init();
	//定义表格监听
    var table = document.getElementById("table");
    table.addEventListener('click',function(e){
    		if(e.target.nodeName == 'A'){
    			console.log(e.target.attributes[2].nodeValue);
    			verifyTrain(e.target.attributes[2].nodeValue);
    		}
    });
   
    //表格上的编辑按钮
	 var verifyTrain = function(trainapplyid){
			 $.ajax({
				url:'/group_project/temp/TrainapplyVerify',
				type:"post",
				data:{trainapplyid:trainapplyid,operate:'find'},
				async:true,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					window.gotoHtml("/group_project/yara/view/tables/trainapplydeal.html",data);
				},
				error:function(error){
					alert(error);
				}
			 });
			 window.gotoHtml("/group_project/yara/view/tables/trainapplydeal.html",null);
				/*url:'/group_project/temp/TrainapplyVerify',
				type:"post",
				data:{trainid:trainid,operate:'edit'},
				dataType: "json",
				async:true,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					window.gotoHtml("/group_project/yara/view/trainhtml/trainVerify.html",data);
				},
				error:function(error){
					alert(error);
				}*/
	 }
	
	
	
})(jQuery)
(function($){
	//初始化表格
	var searchCondition = {};
	var init = function(){
		searchCondition['curentPage'] = 1;
		searchCondition['pageNumbers'] = 10;
		$.ajax({
			url:'/group_project/temp/StaffApplyServlet?operate=table',
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
	init();
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
					+ row[5] + '</th>';
				
				tds[2] = '<th >'
					+ row[4] + '</th>';
				
				tds[3] = '<th>'
					+ row[7] + '</th>';
				
				var operate = '<td style="width:15%">'+
					'<a href="#" class="btn" data="'+ row[6] +'"><i class="glyphicon glyphicon-pencil" ></i>审批</a>'
				 	+'</td></tr>';
				
				var c = tds[0] + tds[1] + tds[2] + tds[3]  + operate ;
				$("tbody").append(c);
			}
		}
	}
	
	var table = document.getElementById("table");
	table.addEventListener('click',function(e){
			if(e.target.nodeName == 'A'){
				console.log(e.target.attributes[2].nodeValue);
				editStaff(e.target.attributes[2].nodeValue);
			}
	});

	//表格上的编辑按钮
	 var editStaff = function(staffId){
		 $.ajax({
				url:'/group_project/temp/StaffApplyServlet',
				type:"post",
				data:{staffApplyId:staffId,operate:'find'},
				dataType: "json",
				async:true,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					window.gotoHtml("/group_project/yara/view/staffhtml/staffApplyDeal.html",data);
				},
				error:function(error){
					alert(error);
				}
			});
	 }

})(jQuery);
var deal = function(){
	$.get("/group_project/yara/view/tables/staffInfo.html",{},function(html){
				$("#content").empty();
				$("#content").append(html);
	});
}

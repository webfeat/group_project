var searchCondition = {};
	searchCondition['curentPage'] = 1;
	searchCondition['pageNumbers'] = 10;
	$.ajax({
		url:'/group_project/temp/TrainServlet?operate=findAll',
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

var createTr = function(data){
	var temp = data.data;
	var strs = "";
	for(var c of temp){
		strs += "<li class='list-group-item'>"+ c.trainname +"</li>";
	}
	$("#trainList").append(strs);
}

	var temp;
	var choosed = "";
	var sure = function(){
		$("#trainName").val(choosed);
		if(!choosed){
			$("#atrainName").text("机构名称");
		}else{
			$("#atrainName").text(choosed);
		}
	}
	
	document.getElementById("trainList").
	addEventListener("click", 
			function(e){
			if(e.target.nodeName == 'LI'){
				if(!temp){
					temp = e.target;
					e.target.className = "list-group-item cative-li";
					choosed = temp.textContent;
				}else{
					if(e.target.className.indexOf('cative-li') > 0){
						e.target.className = "list-group-item";
						choosed = "";
					}else{
						e.target.className = "e.target.className cative-li";
						choosed = e.target.textContent;
					}
				}
			}
		}, true);
	
	var submit = function(){
		$.ajax({
			url:'/group_project/temp/StaffApplyServlet?operate=add',
			type:"post",
			data:{
				trainName:$("#trainName").val(),
				phone:$("#phone").val(),
				email:$("#email").val(),
				password:$("#password").val(),
				userName:$("#userName").val()},
			dataType: "json",
			async:true,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(data){
				if(data.state == false){
					alert(data.errorMessage);
					return;
				}
				self.location='/group_project/yara/loginForm.html';
			},
			error:function(error){
				console.log(error);
			}
		});
	}
	
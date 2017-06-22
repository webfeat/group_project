$(function(){
	//页面加载完成之后执行
	pageInit();
});
function pageInit(){
		var ret;
		$.ajax({
	            method:"post",
	            data:{id:df},
	            url:"/group_project/temp/TableServlet",
	            success:function(data)
	            {
	            	
	            }
		} );
	}
	
	var initGrid = function(){
		var element = $("#list2");
		element.append();
	}
	
	var createTr = function(){
		
	}
	
	var createHeader = function(){
		
	}

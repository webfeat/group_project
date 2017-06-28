/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */

(function ($) {
    "use strict";
    var mainApp = {
        initFunction: function () {
            /*MENU 
            ------------------------------------*/
            $('#main-menu').metisMenu();
			
            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });
        },

        initialization: function () {
            mainApp.initFunction();

        }

    }
    // Initializing ///

    $(document).ready(function () {
        mainApp.initFunction(); 
		$("#sideNav").click(function(){
			if($(this).hasClass('closed')){
				$('.navbar-side').animate({left: '0px'});
				$(this).removeClass('closed');
				$('#page-wrapper').animate({'margin-left' : '260px'});
			}
			else{
			    $(this).addClass('closed');
				$('.navbar-side').animate({left: '-260px'});
				$('#page-wrapper').animate({'margin-left' : '0px'}); 
			}
		});
		
		$.get("/group_project/yara/view/homepage/welcome_manager.html",{},function(html){
			$("#content").append(html);
		});
		
		let app = document.getElementById('main-menu');
		// 给容器添加事件监听器
		app.addEventListener('click', function(e) {
            if(e.target.attributes[1] && e.target.attributes[1].nodeValue == 'parent'){
            	
            }else if(e.target.attributes[1] && e.target.attributes[1].nodeValue.indexOf('html') > 0){
            	$.get("/group_project/yara/view" + e.target.attributes[1].nodeValue,{},function(html){
            		$("#content").empty();
            		$("#content").append(html);
        		});
            }
        }, false);
    });
    
    var tab = function(tabName,state){
    	var bean = {};
    	bean['tabName'] = 1;
    }
    
    window.gotoHtml = function(page,data){
    	window.data = data;
    	$.get(page,{},function(html){
    		$("#content").empty();
    		$("#content").append(html);
		});
    }
    window.getLoginUer = function(){
    	$.ajax({url:'/group_project/temp/LoginUserAction',
			type:"post",
			data:{},
			dataType: "json",
			async:true,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(data){
				$("#loginUser").text("  " + data.loginUser.staffname + "  ");
				var loginUser =  JSON.stringify(data.loginUser);
				localStorage.setItem("loginUser",loginUser);
				localStorage.setItem("modules", data.modules);
				$("#main-menu").append(data.modules);
				$.get(data.modules,{},function(html){
		    		$("#main-menu").empty();
		    		$("#main-menu").append(html);
				});
			},
			error:function(error){
				alert("查询失败");
			}
    	});
    }
    window.getLoginUer();
    
    window.loginOut = function(){
    	$.ajax({url:'/group_project/temp/LoginOutServlet',
			type:"post",
			data:{},
			dataType: "json",
			async:true,
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			success:function(data){
				self.location='/group_project/yara/loginForm.html';
			},
			error:function(error){
				alert("查询失败");
			}
    	});
    	}
    	
    	var inModules = function(content){
    		var modules = localStorage.getItem("modules");
    		if(modules == null){
    			$("#main-menu").hide();
    		}else{
    			return modules.indexOf(content);
    		}
    	}
    	
}(jQuery));

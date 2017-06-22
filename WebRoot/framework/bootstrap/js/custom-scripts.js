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
		
		$.get("/group_project/yara/view/tables/staffapplys.html",{},function(html){
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
    
}(jQuery));

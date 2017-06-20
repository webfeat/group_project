$(function(){
	//页面加载完成之后执行
	pageInit();
});
	
	
	
	function pageInit(){
		  jQuery("#list2")
		      .jqGrid(
		          {
		            url : '/Test/temp/TestServlet',
		            datatype : "json",
		            colNames : [ 'Inv No', 'Date', 'Client', 'Amount','Tax', 'Total', 'Notes' ],
		            colModel : [
		                         {name : 'id',index : 'id',width : 55}, 
		                         {name : 'invdate',  index : 'invdate',width : 90}, 
		                         {name : 'name',index : 'name',width : 100}, 
		                         {name : 'amount',index : 'amount',width : 80,align : "right"}, 
		                         {name : 'tax',index : 'tax',width : 80,align : "right"}, 
		                         {name : 'total',index : 'total',width : 80,align : "right"}, 
		                         {name : 'note',index : 'note',width : 150,sortable : false} 
		                       ],
		            rowNum : 10,
		            rowList : [ 10, 20, 30 ],
		            pager : '#pager2',
		            sortname : 'id',
		            viewrecords : true,
		            sortorder : "desc",
		            loadComplete : function() {
		              var ret;
		              alert("这个方法是执行加载数据完成之后的回调方法。我们可以尝试在此之后更新第13行数据。");
		              ret = jQuery("#list2").jqGrid('getRowData', "13");
		              if (ret.id == "13") {
		                jQuery("#list2")
		                    .jqGrid(
		                        'setRowData',
		                        ret.id,
		                        {
		                          note : "<button onclick='save()'>保存</button>"
		                        })
		              }
		            }
		          });
		  jQuery("#sids").click(function() {
		    alert("Id's of Grid: \n" + jQuery("#list2").jqGrid('getDataIDs'));
		  });
		}
	
	var save = function(){
		alert("ddd");
	}
	//创建jqGrid组件
//	jQuery("#list2").jqGrid({
//            url : '/Test/temp/TestServlet',
//            datatype : "json",
//            colNames : [ 'Inv No', 'Date', 'Client', 'Amount','Tax', 'Total', 'Notes' ],
//            colModel : [ 
//                         {name : 'id',index : 'id',width : 55}, 
//                         {name : 'invdate',  index : 'invdate',width : 90}, 
//                         {name : 'name',index : 'name',width : 100}, 
//                         {name : 'amount',index : 'amount',width : 80,align : "right"}, 
//                         {name : 'tax',index : 'tax',width : 80,align : "right"}, 
//                         {name : 'total',index : 'total',width : 80,align : "right"}, 
//                         {name : 'note',index : 'note',width : 150,sortable : false} 
//                       ],
//            rowNum : 10,
//            rowList : [ 10, 20, 30 ],
//            pager : '#pager2',
//            sortname : 'id',
//            viewrecords : true,
//            sortorder : "desc",
//            loadComplete : function() {
//              var ret;
//              alert("这个方法是执行加载数据完成之后的回调方法。我们可以尝试在此之后更新第13行数据。");
//              ret = jQuery("list2").jqGrid('getRowData', "13");
//              if (ret.id == "13") {
//                jQuery("#list15")
//                    .jqGrid(
//                        'setRowData',
//                        ret.id,
//                        {
//                          note : "<font color='red'>Row 13 is updated!</font>"
//                        })
//              }
//            }
//          });
//	/*创建jqGrid的操作按钮容器*/
//	/*可以控制界面上增删改查的按钮是否显示*/
//	var save = function(){
//		alert("保存中。。。");
//	}

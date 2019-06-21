<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/indexChart.css">
	</head>
	<body>

<script src="${pageContext.request.contextPath}/statics/code/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/statics/code/modules/data.js"></script>
<script src="${pageContext.request.contextPath}/statics/code/modules/series-label.js"></script>
<script src="${pageContext.request.contextPath}/statics/code/modules/exporting.js"></script>
<script src="${pageContext.request.contextPath}/statics/code/modules/export-data.js"></script>

<div id="divTop">
	<div id="divLeft">
		<div class="box">
		  <ul>
		  </ul>
		</div>
	</div>
	<div id="divRight">
	</div>
</div>
<div id="container"></div>
<table id='datatable'>
	<thead>
		<tr> 
			<th></th>
			<th>订单量</th>
		</tr>  
	</thead>    
	<tbody id="databody"> 
	</tbody>
</table>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
window.onload=function(){  
     reportForIndexPage(); 
     reportForIndexPage2();
     //reportForIndexPage3();
}
 function reportForIndexPage(){
	$.ajax({
             type: "POST",
             url: "${pageContext.request.contextPath}/order_reportForIndexPage.action",
             data: {
             },
             dataType: "html",
             success: function(data){
             		$("#databody").html(data);
             		new Highcharts.Chart('container', {
								    chart: {
								        scrollablePlotArea: {
								            minWidth: 500
								        }
								    },
								    data: {
								    	table: 'datatable'
								    },
								    title: {
								        text: '网上商城订单量'
								    },
								    xAxis: {
								        tickWidth: 0,
								        gridLineWidth: 1,
								        reversed: true,
								         title: {
								            text: "时间"
								        },
								        labels: {
								        	format: '{value:%Y-%m-%d}',
								        	rotation: 15,
								            align: 'left',
								            x: -35,
								            y: 3
								        }
								    },
								    yAxis: [{ // left y axis
								        title: {
								            text: "订单量"
								        },
								        labels: {
								            align: 'left',
								            x: 3,
								            y: 16,
								            format: '{value:.,0f}'
								        },
								        showFirstLabel: false
								    }, { // right y axis
								        linkedTo: 0,
								        gridLineWidth: 0,
								        opposite: true,
								        title: {
								            text: "订单量"
								        },
								        labels: {
								            align: 'right',
								            x: -3,
								            y: 16,
								            format: '{value:.,0f}'
								        },
								        showFirstLabel: false
								    }],
								    legend: {
								        align: 'center',
								        verticalAlign: 'bottom',
								        borderWidth: 0
								    },
								    tooltip: {
								        shared: true,
								        crosshairs: true
								    },
								    series: [{
								        name: '订单量',
								        lineWidth: 2,
								        marker: {
								            radius: 3
								        }
								    }],
								    credits: {
								          enabled:false
									},
									exporting: {
								                enabled:false
								            }
								});
         }});
	} 
	
function reportForIndexPage2(){
	$.ajax({
             type: "POST",
             url: "${pageContext.request.contextPath}/order_reportForIndexPage2.action",
             data: {
             },
             dataType: "html",
             success: function(data){
             		$("#divLeft ul").html(data);
             }
             });
 }
 
 function reportForIndexPage3(){
	$.ajax({
             type: "POST",
             url: "${pageContext.request.contextPath}/product_reportForIndexPage3.action",
             data: {
             },
             dataType: "html",
             success: function(data){
             		//$("#main").html(data);
             }
             });
 }
</script>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<link href="${pageContext.request.contextPath }/admin/custom/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/admin/custom/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/left.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="mainDiv1">
<ul id="accordion" class="accordion">
		<li><div class="link">
		<i class="fa fa-globe"></i>用户管理<i class="fa fa-chevron-down"></i>
			</div>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/userAdmin_findAll.action?page=1"  target="rightFrame">用户管理</a></li>
			</ul>
		</li>
		<li><div class="link"><i class="fa fa-globe"></i>一级分类管理<i class="fa fa-chevron-down"></i></div>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath }/adminCategory_findAll.action" target="rightFrame">一级分类管理</a></li>
				<li><a href="${pageContext.request.contextPath }/admin/category/add.jsp" target="rightFrame">添加一级分类</a></li>
			</ul>
		</li>
		<li><div class="link"><i class="fa fa-globe"></i>二级分类管理<i class="fa fa-chevron-down"></i></div>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath }/adminCategorySecond_findAll.action?page=1" target="rightFrame" >二级分类管理</a></li>
				<li><a href="${pageContext.request.contextPath}/adminCategorySecond_addPage.action" target="rightFrame">添加二级分类</a></li>
			</ul>
		</li>
		<li><div class="link"><i class="fa fa-globe"></i>商品管理<i class="fa fa-chevron-down"></i></div>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=1" target="rightFrame" >商品管理</a></li>
				<li><a href="${pageContext.request.contextPath}/adminProduct_addPage.action" target="rightFrame">添加商品</a></li>
			</ul>
		</li>
		<li><div class="link"><i class="fa fa-globe"></i>订单管理<i class="fa fa-chevron-down"></i></div>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=1" target="rightFrame">订单管理</a></li>
			</ul>
		</li>
	</ul>
</div>
<script type="text/javascript">
$(function () {
    var Accordion = function (el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;
        var links = this.el.find('.link');
        links.on('click', {
            el: this.el,
            multiple: this.multiple
        }, this.dropdown);
    };
    Accordion.prototype.dropdown = function (e) {
        var $el = e.data.el;
        $this = $(this), $next = $this.next();
        $next.slideToggle();
        $this.parent().toggleClass('open');
        if (!e.data.multiple) {
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        }
        ;
    };
    var accordion = new Accordion($('#accordion'), false);
});
</script>
    </body>
</html>

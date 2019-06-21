package com.whpu.onlineShoppingMall.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;
import com.whpu.onlineShoppingMall.categorysecond.service.CategorySecondService;
import com.whpu.onlineShoppingMall.product.entity.Product;
import com.whpu.onlineShoppingMall.product.service.ProductService;
import com.whpu.onlineShoppingMall.utils.PageBean;

/**
 * 后台商品管理的Action
 * 
 * @author 传智.郭嘉
 * 
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	// 模型驱动使用的对象
	private Product product = new Product();
	// 接收page参数
	private Integer page;
	// 注入ProductService
	private ProductService productService;
	// 注入CategorySecondService
	private CategorySecondService categorySecondService;
	
	// 文件上传需要的三个属性:
	private File upload;//上传的文件（与表单中的一致）
	private String uploadFileName;//接收上传的文件名
	private String uploadContentType;//接收上传文件的类型

		
	public Product getModel() {
		return product;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 查询所有的商品:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findAll 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		// 将PageBean数据存入到值栈中.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "findAll";
	}

	/**
	 * 跳转到添加页面的方法:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: addPage 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String addPage() {
		// 查询所有的二级分类:
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将二级分类的数据显示到页面上
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addPageSuccess";
	}

	/**
	 * 保存商品的方法:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: save 
	* @return
	* @throws IOException 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String save() throws IOException {
		// 将提交的数据添加到数据库中.
		product.setPdate(new Date());
		// product.setImage(image);
		if(upload != null){
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/statics/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	/**
	 * 删除商品的方法:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: delete 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String delete() {
		// 根据id查询商品信息
		product = productService.findByPid(product.getPid());
		// 删除商品的图片:
		String path = product.getImage();
		if(path != null){
			String realpath = ServletActionContext.getServletContext().getRealPath(
					"/statics/" + path);
			File file = new File(realpath);
			file.delete();
		}
		
		// 删除数据库中商品记录:
		productService.delete(product);
		// 页面跳转
		return "deleteSuccess";
	}

	/**
	 * 编辑商品的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: edit 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String edit() {
		// 根据商品id查询商品信息
		product = productService.findByPid(product.getPid());
		// 查询所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转到编辑页面:
		return "editSuccess";
	}

	/**
	 * 修改商品的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: update 
	* @return
	* @throws IOException 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String update() throws IOException {
		// 将信息修改到数据库
		product.setPdate(new Date());
		
		// 上传:
		if(upload != null ){
			String delPath = ServletActionContext.getServletContext().getRealPath(
					"/statics/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/statics/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		// 页面跳转
		return "updateSuccess";
	}
	
	/**
	 * 关键字查询
	* @date: 2019-4-22 
	* @author: yangtz
	* @title: searchByKeywords 
	* @throws IOException 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void searchByKeywords() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String keywords = ServletActionContext.getRequest().getParameter("keywords");
		System.out.println("关键字"+keywords);
		// 调用Service进行查询.
		PageBean<Product> pageBean = productService
				.findByPage(keywords);
		
		String contentHtml = "";
		if(pageBean.getList()!=null&&pageBean.getList().size()>0){
			contentHtml +="<tr                                                                                       ";
			contentHtml +="	style='FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3'>  ";
			contentHtml +="	<td align='center' width='5%'>                                                        ";
			contentHtml +="		序号                                                                              ";
			contentHtml +="	</td>                                                                                 ";
			contentHtml +="	<td align='center' width='10%'>                                                       ";
			contentHtml +="		商品图片                                                                          ";
			contentHtml +="	</td>                                                                                 ";
			contentHtml +="	<td align='center' width='25%'>                                                       ";
			contentHtml +="		商品名称                                                                          ";
			contentHtml +="	</td>                                                                                 ";
			contentHtml +="	<td align='center' width='12%'>                                                       ";
			contentHtml +="		商品价格                                                                          ";
			contentHtml +="	</td>                                                                                 ";
			contentHtml +="	<td align='center' width='12%'>                                                       ";
			contentHtml +="		商品数量                                                                          ";
			contentHtml +="	</td>                                                                                 ";
			contentHtml +="	<td align='center' width='12%'>                                                       ";
			contentHtml +="		是否热门                                                                          ";
			contentHtml +="	</td>                                                                                 ";
			contentHtml +="<td width='12%' align='center'>                                                       ";
			contentHtml +="		编辑                                                                              ";
			contentHtml +="	</td>                                                                                 ";
			contentHtml +="	<td width='12%' align='center'>                                                       ";
			contentHtml +="		删除                                                                              ";
			contentHtml +="	</td>                                                                                 ";
			contentHtml +="</tr>                                                                                     ";
			for(int i=0;i<pageBean.getList().size();i++){
				contentHtml +="<tr onmouseover='this.style.backgroundColor = 'white''                                                                ";
				contentHtml +="	onmouseout='this.style.backgroundColor = '#F5FAFE';'>                                                                ";
				contentHtml +="	<td style='CURSOR: hand; HEIGHT: 22px' align='center'                                                                ";
				contentHtml +="		width='5%'>                                                                                                      ";
				contentHtml +=i+1;
				contentHtml +="	</td>                                                                                                                ";
				contentHtml +="	<td style='CURSOR: hand; HEIGHT: 22px' align='center'                                                                ";
				contentHtml +="		width='10%'>                                                                                                     ";
				contentHtml +="		<img width='40' class='showImage' height='45' src='/OnlineShoppingMall/statics/"+pageBean.getList().get(i).getImage()+"'>  ";
				contentHtml +="	</td>                                                                                                                ";
				contentHtml +="	<td style='CURSOR: hand; HEIGHT: 22px' align='center'                                                                ";
				contentHtml +="		width='25%'>                                                                                                     ";
				contentHtml +=pageBean.getList().get(i).getPname();
				contentHtml +="	</td>                                                                                                                ";
				contentHtml +="	<td style='CURSOR: hand; HEIGHT: 22px' align='center'                                                                ";
				contentHtml +="		width='12%'>                                                                                                     ";
				contentHtml +=pageBean.getList().get(i).getShop_price();
				contentHtml +="	</td>                                                                                                                ";
				contentHtml +="	<td style='CURSOR: hand; HEIGHT: 22px' align='center'                                                                ";
				contentHtml +="		width='12%'>                                                                                                     ";
				contentHtml +=pageBean.getList().get(i).getNum();
				contentHtml +="	</td>                                                                                                                ";
				contentHtml +="	<td style='CURSOR: hand; HEIGHT: 22px' align='center'                                                                ";
				contentHtml +="		width='12%'>                                                                                                     ";
				contentHtml +=pageBean.getList().get(i).getIs_hot()==1?"是":"否";
				contentHtml +="	</td>                                                                                                                ";
				contentHtml +="	<td align='center' style='HEIGHT: 22px'>                                                                             ";
				contentHtml +="		<a href='/OnlineShoppingMall/adminProduct_edit.action?pid="+pageBean.getList().get(i).getPid()+"'>                         ";
				contentHtml +="			<img src='/OnlineShoppingMall/statics/images/i_edit.gif' border='0' style='CURSOR: hand'>     ";
				contentHtml +="		</a>                                                                                                             ";
				contentHtml +="	</td>                                                                                                                ";
			    contentHtml +="                                                                                                                      ";
				contentHtml +="	<td align='center' style='HEIGHT: 22px'>                                                                             ";
				contentHtml +="		<a href='/OnlineShoppingMall/adminProduct_delete.action?pid="+pageBean.getList().get(i).getPid()+"' onClick='return confirm('确定删除?');'>";
				contentHtml +="			<img src='/OnlineShoppingMall/statics/images/i_del.gif' width='16' height='16' border='0' style='CURSOR: hand'>";
				contentHtml +="		</a>                                                                                                                              ";
				contentHtml +="	</td>                                                                                                                                 ";
				contentHtml +="</tr>";
			}
		}else{
			contentHtml = "<tr><td style='border: hidden; height:40px;'>无数据</td></tr>";
		}
		response.getWriter().println(contentHtml);
	}
}

package com.whpu.onlineShoppingMall.categorysecond.adminAction;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.category.entity.Category;
import com.whpu.onlineShoppingMall.category.service.CategoryService;
import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;
import com.whpu.onlineShoppingMall.categorysecond.service.CategorySecondService;
import com.whpu.onlineShoppingMall.utils.PageBean;

/**
 * 二级分类管理
* @date: 2019-3-31 
* @author: yangtz
* @title: AdminCategorySecondAction 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private static final long serialVersionUID = 1L;
	CategorySecond categorySecond = new CategorySecond();
	// 接收page参数:
	private Integer page;
	// 注入二级Service
	private CategorySecondService categorySecondService;
	// 注入一级分类的Service
	private CategoryService categoryService;

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}

	/**
	 * 带有分页的查询所有二级分类的操作:
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
		// 调用Service进行查询.
		PageBean<CategorySecond> pageBean = categorySecondService
				.findByPage(page);
		// 将pageBean的数据存入到值栈中.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * 跳转到天津页面的方法:
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
		// 查询所有一级分类.
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转:
		return "addPage";
	}

	/**
	 * 添加二级分类的方法:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: save 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	/**
	 * 删除二级分类的方法:
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
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	/**
	 * 编辑二级分类的方法:
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
		// 根据id查询二级分类:
		categorySecond = categorySecondService.findByCsid(categorySecond
				.getCsid());
		// 查询所有一级分类:
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转:
		return "editSuccess";
	}
	
	/**
	 *  修改二级分类的方法:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: update 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String update(){
		categorySecondService.update(categorySecond);
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
		PageBean<CategorySecond> pageBean = categorySecondService
				.findByPage(keywords);
		
		String contentHtml = "";
		if(pageBean.getList()!=null&&pageBean.getList().size()>0){
			contentHtml += "<tr ";
			contentHtml += 	"		style='font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3'>         ";
			contentHtml += 	"		<td align='center' width='8%' height='35px'>                                                 ";
			contentHtml += 	"			序号                                                                                     ";
			contentHtml += 	"		</td>                                                                                        ";
			contentHtml += 	"		<td align='center' width='25%' height='35px'>                                                ";
			contentHtml += 	"			二级分类名称                                                                             ";
			contentHtml += 	"		</td>                                                                                        ";
			contentHtml += 	"		<td width='10%' align='center' height='35px'>                                                ";
			contentHtml += 	"			编辑                                                                                     ";
			contentHtml += 	"		</td>                                                                                        ";
			contentHtml += 	"		<td width='10%' align='center' height='35px'>                                                ";
			contentHtml += 	"			删除                                                                                     ";
			contentHtml += 	"		</td>                                                                                        ";
			contentHtml += 	"	</tr> ";
			for(int i=0;i<pageBean.getList().size();i++){
				contentHtml += 	"			<tr onmouseover='this.style.backgroundColor = 'white''                                   ";
				contentHtml += 	"				onmouseout='this.style.backgroundColor = '#F5FAFE';'>                                ";
				contentHtml += 	"				<td style='CURSOR: hand;' align='center'                                             ";
				contentHtml += 	"					width='8%' height='35px'>                                                        ";
				contentHtml += 	i+1;
				contentHtml += 	"				</td>                                                                                ";
				contentHtml += 	"				<td style='CURSOR: hand;' align='center'                                             ";
				contentHtml += 	"					width='25%'  height='35px'>                                                      ";
				contentHtml += 	pageBean.getList().get(i).getCsname();
				contentHtml += 	"				</td>                                                                                ";
				contentHtml += 	"				<td align='center' height='35px'>                                                    ";
				contentHtml += 	"					<a href='/OnlineShoppingMall/adminCategorySecond_edit.action?csid="+pageBean.getList().get(i).getCsid()+"'>";
				contentHtml += 	"						<img src='/OnlineShoppingMall/statics/images/i_edit.gif' border='0' style='CURSOR: hand'>        " ;
				contentHtml += 	"					</a>                                                                                                                " ;
				contentHtml += 	"				</td>                                                                                                                   " ;
				contentHtml += 	"				<td align='center' height='35px'>                                                                                       " ;
				contentHtml += 	"					<a href='/OnlineShoppingMall/adminCategorySecond_delete.action?csid="+pageBean.getList().get(i).getCsid()+"' onclick='return confirm(确定删除?);'>";
				contentHtml +=  "                  <img src='/OnlineShoppingMall/statics/images/i_del.gif' width='16' height='16' border='0' style='CURSOR: hand'>";
				contentHtml += 	"					</a>   ";
				contentHtml += 	"				</td>      ";
				contentHtml += 	"			</tr>          ";
			}
		}else{
			contentHtml = "<tr><td style='border: hidden; height:40px;'>无数据</td></tr>";
		}
		response.getWriter().println(contentHtml);
	}

}

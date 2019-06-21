package com.whpu.onlineShoppingMall.category.adminAction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.category.entity.Category;
import com.whpu.onlineShoppingMall.category.service.CategoryService;

/**
 * 后台一级分类管理
* @date: 2019-3-30 
* @author: yangtz
* @title: AdminCategoryAction 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	private static final long serialVersionUID = 1L;
	Category category = new Category();
	CategoryService categoryService ;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getModel() {
		return category;
	}
	
	/**
	 * 后台查询所有一级分类
	* @date: 2019-3-30 
	* @author: yangtz
	* @title: findAll 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findAll(){
		
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	/**
	 * 添加一级分类
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: save 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String save(){
		// 调用Service完成保存一级分类
		categoryService.save(category);
		// 进行页面跳转:
		return "saveSuccess";
	}
	/**
	 * 删除一级分类
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: delete 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String delete(){
		// 调用Service完成 一级分类的删除
		// 级联删除一定先查询在删除:
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		// 进行页面转向:
		return "deleteSuccess";
	}
	
	/**
	 * 编辑
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: edit 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String edit(){
		// 接收cid:
		// 根据cid进行查询:
		category = categoryService.findByCid(category.getCid());
		// 完成页面转向:将一级分类数据显示到页面上.
		return "editSuccess";
	}
	/**
	 * 修改
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
		// 使用模型驱动接收前台提交数据:
		categoryService.update(category);
		// 页面跳转:
		return "updateSuccess";
	}
}

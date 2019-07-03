package com.jeecg.apm.controller;
import com.jeecg.apm.entity.ExtUserActionsEntity;
import com.jeecg.apm.service.ExtUserActionsServiceI;
import com.jeecg.util.IpAddressUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 用户行为表格
 * @author onlineGenerator
 * @date 2019-07-01 15:35:23
 * @version V1.0   
 *
 */
@Api(value="ExtUserActions",description="用户行为表格",tags="extUserActionsController")
@Controller
@RequestMapping("/extUserActionsController")
public class ExtUserActionsController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ExtUserActionsController.class);

	@Autowired
	private ExtUserActionsServiceI extUserActionsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 用户行为表格列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/apm/extUserActionsList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ExtUserActionsEntity extUserActions,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ExtUserActionsEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, extUserActions, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.extUserActionsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除用户行为表格
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ExtUserActionsEntity extUserActions, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		extUserActions = systemService.getEntity(ExtUserActionsEntity.class, extUserActions.getId());
		message = "用户行为表格删除成功";
		try{
			extUserActionsService.delete(extUserActions);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户行为表格删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除用户行为表格
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户行为表格删除成功";
		try{
			for(String id:ids.split(",")){
				ExtUserActionsEntity extUserActions = systemService.getEntity(ExtUserActionsEntity.class, 
				id
				);
				extUserActionsService.delete(extUserActions);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "用户行为表格删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加用户行为表格
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ExtUserActionsEntity extUserActions, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户行为表格添加成功";
		try{
			extUserActionsService.save(extUserActions);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户行为表格添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新用户行为表格
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ExtUserActionsEntity extUserActions, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户行为表格更新成功";
		ExtUserActionsEntity t = extUserActionsService.get(ExtUserActionsEntity.class, extUserActions.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(extUserActions, t);
			extUserActionsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "用户行为表格更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 用户行为表格新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ExtUserActionsEntity extUserActions, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(extUserActions.getId())) {
			extUserActions = extUserActionsService.getEntity(ExtUserActionsEntity.class, extUserActions.getId());
			req.setAttribute("extUserActions", extUserActions);
		}
		return new ModelAndView("com/jeecg/apm/extUserActions-add");
	}
	/**
	 * 用户行为表格编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ExtUserActionsEntity extUserActions, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(extUserActions.getId())) {
			extUserActions = extUserActionsService.getEntity(ExtUserActionsEntity.class, extUserActions.getId());
			req.setAttribute("extUserActions", extUserActions);
		}
		return new ModelAndView("com/jeecg/apm/extUserActions-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","extUserActionsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ExtUserActionsEntity extUserActions,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ExtUserActionsEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, extUserActions, request.getParameterMap());
		List<ExtUserActionsEntity> extUserActionss = this.extUserActionsService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"用户行为表格");
		modelMap.put(NormalExcelConstants.CLASS,ExtUserActionsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户行为表格列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,extUserActionss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ExtUserActionsEntity extUserActions,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"用户行为表格");
    	modelMap.put(NormalExcelConstants.CLASS,ExtUserActionsEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户行为表格列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<ExtUserActionsEntity> listExtUserActionsEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ExtUserActionsEntity.class,params);
				for (ExtUserActionsEntity extUserActions : listExtUserActionsEntitys) {
					extUserActionsService.save(extUserActions);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="用户行为表格列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ExtUserActionsEntity>> list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(ExtUserActionsEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<ExtUserActionsEntity> listExtUserActionss = this.extUserActionsService.getListByCriteriaQuery(query,true);
		return Result.success(listExtUserActionss);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取用户行为表格信息",notes="根据ID获取用户行为表格信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ExtUserActionsEntity task = extUserActionsService.get(ExtUserActionsEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取用户行为表格信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建用户行为表格")
	public ResponseMessage<?> create(@ApiParam(name="用户行为表格对象")@RequestBody ExtUserActionsEntity extUserActions, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ExtUserActionsEntity>> failures = validator.validate(extUserActions);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}
		
		//qchzhu:获取IP地址
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		String ip = IpAddressUtil.getIpAddress(request);
		extUserActions.setIp(ip);
		extUserActions.setCreateOn(new Date());

		//保存
		try{
			extUserActionsService.save(extUserActions);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("用户行为表格信息保存失败");
		}
		return Result.success(extUserActions);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新用户行为表格",notes="更新用户行为表格")
	public ResponseMessage<?> update(@ApiParam(name="用户行为表格对象")@RequestBody ExtUserActionsEntity extUserActions) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ExtUserActionsEntity>> failures = validator.validate(extUserActions);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			extUserActionsService.saveOrUpdate(extUserActions);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新用户行为表格信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新用户行为表格信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除用户行为表格")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			extUserActionsService.deleteEntityById(ExtUserActionsEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("用户行为表格删除失败");
		}

		return Result.success();
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="extUserActionsList" checkbox="true" pagination="true" fitColumns="true" title="用户行为表格" actionUrl="extUserActionsController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createOn"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户编号"  field="userId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务场景"  field="actionScene"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户操作"  field="actionType"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作对象"  field="actionTarget"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主机名"  field="hostName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="IP地址"  field="ip"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="MAC地址"  field="mac"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始时间"  field="actionBeginTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束时间"  field="actionEndTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="extUserActionsController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="extUserActionsController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="extUserActionsController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="extUserActionsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="extUserActionsController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'extUserActionsController.do?upload', "extUserActionsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("extUserActionsController.do?exportXls","extUserActionsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("extUserActionsController.do?exportXlsByT","extUserActionsList");
}

//bootstrap列表图片格式化
function btListImgFormatter(value,row,index){
	return listFileImgFormat(value,"image");
}
//bootstrap列表文件格式化
function btListFileFormatter(value,row,index){
	return listFileImgFormat(value);
}

</script>

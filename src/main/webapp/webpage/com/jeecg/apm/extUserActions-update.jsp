<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>用户行为表格</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="extUserActionsController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${extUserActions.id}"/>
	<div class="form-group">
		<label for="ip" class="col-sm-3 control-label">IP地址：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="ip" name="ip" value='${extUserActions.ip}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入IP地址"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="mac" class="col-sm-3 control-label">MAC地址：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="mac" name="mac" value='${extUserActions.mac}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入MAC地址"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="actionType" class="col-sm-3 control-label">用户操作如点击：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="actionType" name="actionType" value='${extUserActions.actionType}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入用户操作如点击"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="actionTarget" class="col-sm-3 control-label">操作对象如菜单：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="actionTarget" name="actionTarget" value='${extUserActions.actionTarget}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入操作对象如菜单"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="actionScene" class="col-sm-3 control-label">业务场景：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="actionScene" name="actionScene" value='${extUserActions.actionScene}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入业务场景"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="hostName" class="col-sm-3 control-label">操作发起主机名：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="hostName" name="hostName" value='${extUserActions.hostName}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入操作发起主机名"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="actionBeginTime" class="col-sm-3 control-label">操作开始时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="actionBeginTime" name="actionBeginTime" value='${extUserActions.actionBeginTime}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入操作开始时间"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="actionEndTime" class="col-sm-3 control-label">操作结束时间：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="actionEndTime" name="actionEndTime" value='${extUserActions.actionEndTime}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入操作结束时间"  ignore="ignore" />
			</div>
		</div>
	</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
	
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
			var win = frameElement.api.opener;
			if (data.success == true) {
				frameElement.api.close();
			    win.reloadTable();
			    win.tip(data.msg);
			} else {
			    if (data.responseText == '' || data.responseText == undefined) {
			        $.messager.alert('错误', data.msg);
			        $.Hidemsg();
			    } else {
			        try {
			            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
			            $.messager.alert('错误', emsg);
			            $.Hidemsg();
			        } catch (ex) {
			            $.messager.alert('错误', data.responseText + "");
			            $.Hidemsg();
			        }
			    }
			    return false;
			}
		}
	});
});
</script>
</body>
</html>
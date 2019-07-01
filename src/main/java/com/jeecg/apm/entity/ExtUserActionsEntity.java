package com.jeecg.apm.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 用户行为表格
 * @author onlineGenerator
 * @date 2019-07-01 15:35:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ext_user_actions", schema = "")
@SuppressWarnings("serial")
public class ExtUserActionsEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createOn;
	/**用户编号*/
	private java.lang.String userId;
	/**业务场景*/
	@Excel(name="业务场景",width=15)
	private java.lang.String actionScene;
	/**用户操作*/
	@Excel(name="用户操作",width=15)
	private java.lang.String actionType;
	/**操作对象*/
	@Excel(name="操作对象",width=15)
	private java.lang.String actionTarget;
	/**主机名*/
	@Excel(name="主机名",width=15)
	private java.lang.String hostName;
	/**IP地址*/
	@Excel(name="IP地址",width=15)
	private java.lang.String ip;
	/**MAC地址*/
	@Excel(name="MAC地址",width=15)
	private java.lang.String mac;
	/**开始时间*/
	@Excel(name="开始时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date actionBeginTime;
	/**结束时间*/
	@Excel(name="结束时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date actionEndTime;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_ON",nullable=true,length=20)
	public java.util.Date getCreateOn(){
		return this.createOn;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateOn(java.util.Date createOn){
		this.createOn = createOn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户编号
	 */

	@Column(name ="USER_ID",nullable=true,length=50)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户编号
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务场景
	 */

	@Column(name ="ACTION_SCENE",nullable=true,length=32)
	public java.lang.String getActionScene(){
		return this.actionScene;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务场景
	 */
	public void setActionScene(java.lang.String actionScene){
		this.actionScene = actionScene;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户操作
	 */

	@Column(name ="ACTION_TYPE",nullable=true,length=32)
	public java.lang.String getActionType(){
		return this.actionType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户操作
	 */
	public void setActionType(java.lang.String actionType){
		this.actionType = actionType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作对象
	 */

	@Column(name ="ACTION_TARGET",nullable=true,length=32)
	public java.lang.String getActionTarget(){
		return this.actionTarget;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作对象
	 */
	public void setActionTarget(java.lang.String actionTarget){
		this.actionTarget = actionTarget;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主机名
	 */

	@Column(name ="HOST_NAME",nullable=true,length=32)
	public java.lang.String getHostName(){
		return this.hostName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主机名
	 */
	public void setHostName(java.lang.String hostName){
		this.hostName = hostName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  IP地址
	 */

	@Column(name ="IP",nullable=true,length=32)
	public java.lang.String getIp(){
		return this.ip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  IP地址
	 */
	public void setIp(java.lang.String ip){
		this.ip = ip;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  MAC地址
	 */

	@Column(name ="MAC",nullable=true,length=32)
	public java.lang.String getMac(){
		return this.mac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  MAC地址
	 */
	public void setMac(java.lang.String mac){
		this.mac = mac;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */

	@Column(name ="ACTION_BEGIN_TIME",nullable=true,length=32)
	public java.util.Date getActionBeginTime(){
		return this.actionBeginTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setActionBeginTime(java.util.Date actionBeginTime){
		this.actionBeginTime = actionBeginTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */

	@Column(name ="ACTION_END_TIME",nullable=true,length=32)
	public java.util.Date getActionEndTime(){
		return this.actionEndTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setActionEndTime(java.util.Date actionEndTime){
		this.actionEndTime = actionEndTime;
	}
}
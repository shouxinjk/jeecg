package com.jeecg.apm.service;
import com.jeecg.apm.entity.ExtUserActionsEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ExtUserActionsServiceI extends CommonService{
	
 	public void delete(ExtUserActionsEntity entity) throws Exception;
 	
 	public Serializable save(ExtUserActionsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ExtUserActionsEntity entity) throws Exception;
 	
}

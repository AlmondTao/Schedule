package com.taoqy.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * For transmit information between service and controller
 *
 * @author taoqy
 */
public class ServiceResult {

    private boolean success;

    private String resultDescription;
    private Object responseData;


    /**
     * @param success 处理是否成功
     * @param resultDescription 对处理结果的描述
     */
    public ServiceResult(boolean success, String resultDescription) {
        setServiceResult(success, resultDescription, null);
    }

    /**
     * @param success 处理是否成功
     * @param resultDescription 对处理结果的描述
     * @param responseData 传给前台的数据
     */
    public ServiceResult(boolean success, String resultDescription, Object responseData) {
        setServiceResult(success, resultDescription, responseData);
    }

    public ServiceResult(boolean success, String resultDescription, List dataList, Long total) {
        Map<String,Object> responseMessage = new HashMap<>();
        responseMessage.put("total", total);
        responseMessage.put("dataList", dataList);
        setServiceResult(success, resultDescription, responseMessage);
    }
    public void setServiceResult(boolean success, String resultDescription, Object responseData){
        this.success = success;
        this.resultDescription = resultDescription;
        this.responseData = responseData;
    }
    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        if(responseData != null){
            this.responseData = responseData;
        }else {
            this.responseData = new HashMap();
        }
    }
    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

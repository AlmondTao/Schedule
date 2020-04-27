package com.taoqy.entity;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Save message for response to layout
 * @author taoqy
 */
public class ResponseMessage implements Serializable {
    //状态码
    /**
     *
     * @see HttpStatus
     */
    private int status;
    //状态描述
    private String description;
    //数据对象
    private Object dataObject;

    public ResponseMessage(HttpStatus status, String description) {
        this.setMessage(status, description, null);
    }

    public ResponseMessage(HttpStatus status, String description, Object dataObject) {
        this.setMessage(status, description, dataObject);
    }

    public void setMessage(HttpStatus status, String description, Object dataObject) {
        this.status = status.value();
        this.description = description;
        if(dataObject != null) {
            this.dataObject = dataObject;
        } else {
            this.dataObject = new Object();
        }
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDataObject() {
        return dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }


}

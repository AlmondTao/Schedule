package com.taoqy.controller;

import com.taoqy.entity.ResponseMessage;
import com.taoqy.entity.ServiceResult;
import com.taoqy.service.JobViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/12/5
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@RestController
@RequestMapping("/view")
public class JobViewController {

    @Autowired
    private JobViewService jobViewService;


    @RequestMapping(value = "/getJobGroupList",method = RequestMethod.GET)
    public ResponseMessage getJobGroupList(@RequestParam(value = "schedName",required = false)String schedName){
        ServiceResult serviceResult = jobViewService.getJobGroupList(schedName);
        return new ResponseMessage(HttpStatus.OK,serviceResult.getResultDescription(),serviceResult.getResponseData() );
    }

    public ResponseMessage getJobList(@RequestParam(value = "schedName",required = false)String schedName){
        ServiceResult serviceResult = jobViewService.getJobList(schedName);
        return new ResponseMessage(HttpStatus.OK, serviceResult.getResultDescription(),serviceResult.getResponseData());
    }
}

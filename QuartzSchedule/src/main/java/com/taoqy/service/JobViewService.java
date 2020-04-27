package com.taoqy.service;

import com.taoqy.entity.ServiceResult;

public interface JobViewService {
    ServiceResult getJobGroupList(String schedName);

    ServiceResult getJobList(String schedName);
}

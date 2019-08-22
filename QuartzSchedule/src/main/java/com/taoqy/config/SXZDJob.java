package com.taoqy.config;

import org.quartz.Job;

public interface SXZDJob extends Job {

    String getCron();

}

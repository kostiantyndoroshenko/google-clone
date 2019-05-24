package com.kostiantynd.google.indexing.job;

import com.kostiantynd.google.indexing.service.IndexService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexJob implements Job  {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private IndexService service;

    public IndexJob(IndexService service) {
        this.service = service;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap data = jobExecutionContext.getMergedJobDataMap();

        try {
            service.indexPage(data.getString("URL"), data.getIntValue("DEPTH"));
        } catch (Exception  e) {
            logger.error("INDEX ERROR: URL - " + data.getString("URL") + ", REASON: " + e.getMessage());
        }
    }
}

package com.kostiantynd.google.indexing.service;

import com.kostiantynd.google.indexing.job.IndexJob;
import com.kostiantynd.google.indexing.web.dto.IndexRequest;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class QuartzIndexScheduler extends AbstractQuartzScheduler implements IndexScheduler {

    protected QuartzIndexScheduler(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void schedule(IndexRequest request) {
        schedule(request.getUrl(), request.getScanDepth());
    }

    @Override
    public void schedule(Set<String> urlList, Integer depth) {
        urlList.forEach(url -> schedule(url, depth));
    }

    @Override
    public void schedule(String url, Integer depth) {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("URL", url);
        dataMap.put("DEPTH", depth);

        scheduleJob(IndexJob.class, url, dataMap);
    }
}

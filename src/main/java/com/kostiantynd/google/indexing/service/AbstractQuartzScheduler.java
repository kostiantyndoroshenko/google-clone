package com.kostiantynd.google.indexing.service;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractQuartzScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Scheduler scheduler;

    protected AbstractQuartzScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    protected void scheduleJob(Class<? extends Job> clazz, String id, JobDataMap dataMap) {
        JobDetail jobDetail = buildJobDetail(clazz, id, dataMap);
        Trigger trigger = buildJobTrigger(jobDetail);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            logger.info("SCHEDULE ERROR: " + e.getMessage());
        }
    }

    private JobDetail buildJobDetail(Class<? extends Job> clazz, String id, JobDataMap dataMap) {
        return JobBuilder.newJob(clazz)
                .withIdentity(id, clazz.getSimpleName())
                .usingJobData(dataMap)
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getJobClass().getSimpleName())
                .startNow()
                .build();
    }
}

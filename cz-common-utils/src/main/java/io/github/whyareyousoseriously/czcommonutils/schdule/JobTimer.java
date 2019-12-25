package io.github.whyareyousoseriously.czcommonutils.schdule;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author chenzhen
 * Created by chenzhen on 2019/12/13.
 */
public class JobTimer implements SchedulingConfigurer {
    private String jobName;

    private Runnable job;

    private Trigger trigger;

    public JobTimer(String jobName, Runnable job, Trigger trigger) {
        this.jobName = jobName;
        this.job = job;
        this.trigger = trigger;
    }

    public String getJobName() {
        return jobName;
    }

    public JobTimer setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public Runnable getJob() {
        return job;
    }

    public JobTimer setJob(Runnable job) {
        this.job = job;
        return this;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public JobTimer setTrigger(Trigger trigger) {
        this.trigger = trigger;
        return this;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(job,trigger);
    }
}

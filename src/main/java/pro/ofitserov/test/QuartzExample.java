package pro.ofitserov.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzExample {

    public static void main(String[] args) {

        System.out.println("Quartz Example");

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        try {

            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail job1 = JobBuilder.newJob(SimpleJob.class)
                    .withIdentity("SimpleJob", "group1")
                    .build();

            JobDetail job2 = JobBuilder.newJob(JobWithParameters.class)
                    .withIdentity("JobWithParameters", "group1")
                    .usingJobData("jobSays", "Hello World!")
                    .usingJobData("myFloatValue", 3.141f)
                    .build();


            Trigger trigger = TriggerBuilder.newTrigger().
                    withIdentity("trigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder
                            .simpleSchedule()
                            .withIntervalInSeconds(3)
                            .repeatForever())
                    .build();

            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 29 18 * * ?"))
                    //.forJob("JobWithParameters", "group1")
                    .build();


            scheduler.scheduleJob(job1, trigger);

            scheduler.scheduleJob(job2, cronTrigger);

            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

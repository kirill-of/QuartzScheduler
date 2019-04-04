package pro.ofitserov.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {
    public void execute(JobExecutionContext arg) throws JobExecutionException {
        System.out.println("This is a simple quartz job!");
    }
}
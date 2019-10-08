package cz.campanus.schedule;

import cz.campanus.Constants;
import cz.campanus.config.Configuration;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class Runner {

	private static final Logger LOG = LoggerFactory.getLogger(Runner.class);
	private final Scheduler scheduler;

	public Runner() throws SchedulerException {
		scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
	}

	public void planJobs() {
		planCampanusJob();
	}

	private void planCampanusJob() {
		try (InputStream input = new FileInputStream(Constants.CONFIG_FILE)) {
			final Configuration configuration = new Configuration(input);

			final JobDataMap jobData = new JobDataMap();
			jobData.put("configuration", configuration);

			for (int hour : configuration.getRunAtHours()) {
				final JobDetail campanusJob = JobBuilder.newJob(CampanusJob.class)
						.usingJobData(jobData)
						.build();
				final Trigger cronTrigger = TriggerBuilder.newTrigger()
						.withSchedule(
								CronScheduleBuilder
										.dailyAtHourAndMinute(hour, 0)
										.withMisfireHandlingInstructionFireAndProceed()
						)
						.build();
				final Date firstRun = scheduler.scheduleJob(campanusJob, cronTrigger);
				LOG.info("Campanus job: {} planned at {}", campanusJob.getKey(), firstRun);
			}
		} catch (Exception e) {
			LOG.error("Unexpected error", e);
		}
	}
}

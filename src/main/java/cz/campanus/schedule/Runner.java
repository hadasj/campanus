package cz.campanus.schedule;

import cz.campanus.Constants;
import cz.campanus.config.Configuration;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
			final JobDetail campanusJob = JobBuilder.newJob(CampanusJob.class)
					.usingJobData(jobData)
					.build();

			final Date  startAt = Date.from(LocalDateTime.now()
					.withHour(configuration.getStartAt().getHour())
					.withMinute(configuration.getStartAt().getMinute())
					.withSecond(0)
					.atZone(ZoneId.systemDefault())
					.toInstant());
			final Trigger trigger = TriggerBuilder.newTrigger()
					.startAt(startAt)
					.withSchedule(
							SimpleScheduleBuilder
									.simpleSchedule()
									.repeatForever()
									.withIntervalInMinutes(configuration.getIntervalInMinutes())
					)
					.build();

			scheduler.scheduleJob(campanusJob, trigger);
		} catch (Exception e) {
			LOG.error("Unexpected error", e);
		}
	}
}

package cz.campanus.schedule;

import cz.campanus.Constants;
import cz.campanus.config.Configuration;
import cz.campanus.send.Sender;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;

public class CampanusJob implements org.quartz.Job {
	private static final Logger LOG = LoggerFactory.getLogger(CampanusJob.class);

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try (InputStream input = new FileInputStream(Constants.CONFIG_FILE)) {
			Configuration configuration = new Configuration(input);
			Sender sender = new Sender(configuration);

			boolean sent = sender.checkWeb(configuration.getJardaUrl(), configuration.getFilePath() + configuration.getJardaFile(),
					configuration.getJardaSubject(), configuration.getEmails());
			if (sent)
				LOG.info(configuration.getJardaLog());

			sent = sender.checkWeb(configuration.getStandaUrl(), configuration.getFilePath() + configuration.getStandaFile(),
					configuration.getStandaSubject(), configuration.getEmails());
			if (sent)
				LOG.info(configuration.getStandaLog());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
}

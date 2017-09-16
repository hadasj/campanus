package cz.campanus;

import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.campanus.config.Configuration;
import cz.campanus.send.Sender;

/**
 * @author jan.hadas@i.cz
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
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
            }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

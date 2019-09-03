package cz.campanus;

import cz.campanus.schedule.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.hadas@i.cz
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            final Runner runner = new Runner();
            runner.planJobs();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

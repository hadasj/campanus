package cz.campanus;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jan.hadas@i.cz
 */
public class LogTest {
    private static Logger LOG = LoggerFactory.getLogger(LogTest.class);

    @Ignore
    @Test
    public void logTest() {
        LOG.info("Testing file log ...");
    }
}

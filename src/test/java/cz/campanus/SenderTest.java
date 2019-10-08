package cz.campanus;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cz.campanus.config.Configuration;
import cz.campanus.send.Sender;

/**
 * @author jan.hadas@i.cz
 */
public class SenderTest {
    private static final String JARDA_URL = "http://www.campanus.cz/cizkova/";
    private static final String JARDA_SUBJECT = "CAMPANUS - WEB Hemalová";
    private static final String JARDA_FILE = "hemalova.txt";
    private static final String HONZA_EMAIL = "hadas.jan@gmail.com";

    private Sender sender;

    @Before
    public void init() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties");
        assertNotNull(input);

        Configuration configuration = new Configuration(input);
        sender = new Sender(configuration);
    }

    @Ignore
    @Test
    public void sendNotificationTest() throws Exception {
        sender.checkWeb(JARDA_URL, JARDA_FILE, JARDA_SUBJECT, singletonList(HONZA_EMAIL));
    }
}

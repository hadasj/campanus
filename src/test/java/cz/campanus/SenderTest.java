package cz.campanus;

import org.junit.Ignore;
import org.junit.Test;

import cz.campanus.send.Sender;

/**
 * @author jan.hadas@i.cz
 */
public class SenderTest {
    private static final String URL = "http://www.campanus.cz/hemalova/";
    private static final String FILE = "hemal.txt";
    private static final String MAIL = "hadas.jan@gmail.com";

    private static final String JARDA_URL = "http://www.campanus.cz/hemalova/";
    private static final String JARDA_SUBJECT = "CAMPANUS - WEB Hemalová";
    private static final String JARDA_FILE = "hemalova.txt";
    private static final String HONZA_EMAIL = "hadas.jan@gmail.com";

    private Sender sender = new Sender();

    @Test
    @Ignore
    public void sendNewsletter() throws Exception {
        sender.checkWeb(URL, FILE, "Test campanus utilitky", MAIL);
    }

    @Test
    public void test() throws Exception {
        sender.checkWeb(JARDA_URL, JARDA_FILE, JARDA_SUBJECT, HONZA_EMAIL);
    }
}

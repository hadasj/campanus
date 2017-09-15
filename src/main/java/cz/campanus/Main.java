package cz.campanus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.campanus.send.Sender;

/**
 * @author jan.hadas@i.cz
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final String JARDA_URL = "http://www.campanus.cz/hemalova/";
    private static final String JARDA_SUBJECT = "CAMPANUS - WEB Hemalová";
    private static final String JARDA_LOG = "HEMALOVÁ - rozdíl, email odeslán. ";
    private static final String JARDA_FILE = "hemalova.txt";

    private static final String STANDA_URL = "http://www.campanus.cz/vackova/";
    private static final String STANDA_SUBJECT = "CAMPANUS - WEB Vacková";
    private static final String STANDA_LOG = "VACKOVÁ - rozdíl, email odeslán. ";
    private static final String STANDA_FILE = "vackova.txt";

    private static final String HONZA_EMAIL = "hadas.jan@gmail.com";
    private static final String TANA_EMAIL = "hadasova.t@gmail.com";

    public static void main(String[] args) {
        try {
            Sender sender = new Sender();

            boolean sent = sender.checkWeb(JARDA_URL, Constants.FILE_PATH + JARDA_FILE, JARDA_SUBJECT, HONZA_EMAIL, TANA_EMAIL);
            if (sent)
                LOG.info(JARDA_LOG);

            sent = sender.checkWeb(STANDA_URL, Constants.FILE_PATH + STANDA_FILE, STANDA_SUBJECT, HONZA_EMAIL, TANA_EMAIL);
            if (sent)
                LOG.info(STANDA_LOG);
            }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

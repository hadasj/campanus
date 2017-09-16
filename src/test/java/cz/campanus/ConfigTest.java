package cz.campanus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import cz.campanus.config.Configuration;

/**
 * @author jan.hadas@i.cz
 */
public class ConfigTest {
    private static final String TEST_CONFIG_FILE = "test.properties";
    private Configuration configuration;

    @Before
    public void setUp() throws IOException {
        InputStream input = getClass().getClassLoader().getResource(TEST_CONFIG_FILE).openStream();
        configuration = new Configuration(input);
    }

    @Test
    public void should_return_correct_values() {
        Set<String> mails =  configuration.getEmails();
        assertFalse(mails.isEmpty());
        String mail = mails.iterator().next();
        assertEquals("honza@fedora", mail);

        assertEquals("Jarda test mail", configuration.getJardaSubject());
        assertEquals("Odesláno", configuration.getJardaLog());
    }
}

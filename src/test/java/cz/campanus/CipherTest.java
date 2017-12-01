package cz.campanus;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import cz.campanus.security.Cipher;

/**
 * @author jan.hadas@i.cz
 */
public class CipherTest {
    private static final String PASSWORD = "test..";
    public Cipher cipher = new Cipher();

    @Test
    public void encryptionTest() {
        String encrypted = cipher.encrypt(PASSWORD);
        String decrypted = cipher.decrypt(encrypted);
        assertEquals(PASSWORD, decrypted);
    }
}

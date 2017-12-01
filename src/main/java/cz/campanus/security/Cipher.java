package cz.campanus.security;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author jan.hadas@i.cz
 */
public class Cipher {
    private static final String INITIAL_PASSWORD = "Ctyrkolak 434.2186*";

    private BasicTextEncryptor encryptor;

    public Cipher() {
        encryptor = new BasicTextEncryptor();
        encryptor.setPassword(INITIAL_PASSWORD);
    }

    public String encrypt(String decrypted) {
        return encryptor.encrypt(decrypted);
    }

    public String decrypt(String encrypted) {
        return encryptor.decrypt(encrypted);
    }

}

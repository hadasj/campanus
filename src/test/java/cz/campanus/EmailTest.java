package cz.campanus;

import org.junit.Ignore;
import org.junit.Test;

import cz.campanus.mail.StartTLSMail;

/**
 * @author jan.hadas@i.cz
 */
public class EmailTest {

  private static final String USERNAME = "hadas.jan";
  private static final String EMAIL_ADDRESS = "honza@Fedora";

  @Ignore
  @Test
  public void should_send_icz_mail() throws Exception {
    //GoogleMail.Send();
    StartTLSMail.sendEmail("Hello world!! ;-)", "Test email 6", "jan.hadas@i.cz");
  }
}

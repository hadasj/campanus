package cz.campanus.send;

import static cz.campanus.util.EntryUtil.entryToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.mail.MessagingException;

import cz.campanus.Constants;
import cz.campanus.dto.EntryDto;
import cz.campanus.io.EntryFileLoader;
import cz.campanus.io.EntryLoader;
import cz.campanus.mail.StartTLSMail;
import cz.campanus.parse.EntryTitleSummaryParser;
import cz.campanus.parse.Parser;
import cz.campanus.io.EntryFileStore;
import cz.campanus.io.EntryStore;

import static cz.campanus.Constants.ENTRY_START_PATTERN;
import static cz.campanus.Constants.ENTRY_END_PATTERN;
/**
 * @author jan.hadas@i.cz
 */
public class Sender {

    public boolean checkWeb(String webUrl, String storeFileName, String subject, String... emails)
                                                                    throws IOException, MessagingException {
        Parser parser = new EntryTitleSummaryParser();
        EntryLoader loader = new EntryFileLoader(storeFileName);
        BufferedReader input = new BufferedReader(new InputStreamReader(new URL(webUrl).openStream()));

        List<EntryDto> actualEntries = parser.parse(input, Constants.PARSED_ENTRY_COUNT, ENTRY_START_PATTERN, ENTRY_END_PATTERN);
        List<EntryDto> oldEntries = loader.loadAllEntries();

        // overwrite file by actual data
        EntryStore store = new EntryFileStore(storeFileName);
        store.storeAllEntries(actualEntries);

        int count = 0;
        if (!oldEntries.isEmpty()) {
            // remove old entries from newsletter
            EntryDto oldEntry = oldEntries.get(0);
            for (EntryDto newEntry : actualEntries) {
                if (newEntry.getTitle().equals(oldEntry.getTitle()) &&
                    newEntry.getDetailUrl().equals(oldEntry.getDetailUrl())) {
                    // old entry matches actual entry - stop
                    break;
                }
                // different - new entry - add it
                count++;
            }
        } else
            count = actualEntries.size();

        boolean emailSent = false;
        for (String email : emails) {
            emailSent = sendNewsletter(actualEntries, count, subject, email);
        }
        return emailSent;
    }

    private boolean sendNewsletter(List<EntryDto> entries, int count, String subject,
                                String email) throws MessagingException {
        String content = "";

        for (int i = 0; i < count; i++) {
            EntryDto entry = entries.get(i);
            content += entryToString(entry) + "<br/>";
        }

        if (!content.equals("")) {
            StartTLSMail.sendEmail(content, subject, email);
            return true;
        }
        return false;
    }
}

package cz.campanus.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.campanus.config.Configuration;
import cz.campanus.dto.EntryDto;

/**
 * @author jan.hadas@i.cz
 */
public class EntryTitleSummaryParser extends Parser {
    final static Logger LOG = LoggerFactory.getLogger(EntryTitleSummaryParser.class);

    private Configuration configuration;

    public EntryTitleSummaryParser(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected EntryDto parseEntry(BufferedReader input) throws IOException {
        EntryDto entry = new EntryDto();
        String line;
        boolean entryFound = false, detailFound = false;

        do {
            line = input.readLine();
            if (line != null && line.contains(configuration.getTitlePattern()))
                // start of entry has been found - continue searching
                entryFound = true;

            if (entryFound && line != null && line.contains("<a href")) {
                // first anchor has been found in entry
                String titleContent = line.substring(line.indexOf("bookmark\">")+10, line.indexOf("</a>"));
                String detailUrl = line.substring(line.indexOf("href") + 6);
                detailUrl = detailUrl.substring(0, detailUrl.indexOf("\""));
                entry.setTitle(titleContent);
                entry.setDetailUrl(detailUrl);
                LOG.debug("found url for {}: {}", titleContent, detailUrl);
                detailFound = true;
                break;
            }
        } while (line != null);

        if (detailFound)
            readDetail(entry);

        return entry;
    }

    private void readDetail(EntryDto dto) throws IOException {
        final InputStream inputStream = new URL(dto.getDetailUrl()).openStream();
        BufferedReader summaryInput = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        do {  // skip first part
            line = summaryInput.readLine();
        } while (line != null && !line.contains(configuration.getContentPattern()));

        do {
            line = readLine(summaryInput);
            if (line != null && !line.contains(configuration.getContentPattern()))
                dto.getContent().add(line);
        } while (line != null && !line.contains(configuration.getContentPattern()));
    }

    /**
     * Removes HTML tags from lines
     * @return string without HTML entities
     */
    private String readLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null)
            return null;

        while (line.contains("<div class=\"" + configuration.getContentPattern())) {
            line = reader.readLine();
        }
        // remove <p>
        line = line.replaceAll("<p>", "");
        // remove </p>
        line = line.replaceAll("</p>", "");
        // remove </p>
        line = line.replaceAll("<br.*?/>", "");
        // remove nbsp
        line = line.replaceAll("&nbsp;", " ");
        // remove </div>
        line = line.replaceAll("</div.*?>", "");
        // replace &amp; by &
        line = line.replaceAll("&amp;", "&");
        return line;
    }
}

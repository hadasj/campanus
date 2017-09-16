package cz.campanus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.campanus.config.Configuration;
import cz.campanus.dto.EntryDto;
import cz.campanus.parse.EntryTitleSummaryParser;
import cz.campanus.parse.Parser;

/**
 * @author jan.hadas@i.cz
 */
public class ParserTest {
    private static final Logger LOG = LoggerFactory.getLogger(ParserTest.class);
    private static final String HEMALOVA_URL = "http://www.campanus.cz/hemalova/";
    private static final String CONTENT_PATTERN = "content";

    private Parser parser;

    @Before
    public void init() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties");
        assertNotNull(input);

        Configuration configuration = new Configuration(input);
        parser = new EntryTitleSummaryParser(configuration);
    }

    @Test
    public void simpleTest() throws Exception {
        final InputStream inputStream = new URL(HEMALOVA_URL).openStream();
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

        List<EntryDto> parsedResult = parser.parse(input, 5, CONTENT_PATTERN, "#" + CONTENT_PATTERN);

        assertNotNull(parsedResult);
        assertFalse(parsedResult.isEmpty());
        EntryDto entryDto = parsedResult.get(0);

        LOG.info("Title: {}", entryDto.getTitle());
        LOG.info("Content: {}", entryDto.getContent());
    }
}

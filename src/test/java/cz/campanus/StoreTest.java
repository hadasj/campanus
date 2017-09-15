package cz.campanus;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.campanus.dto.EntryDto;
import cz.campanus.io.EntryFileLoader;
import cz.campanus.io.EntryFileStore;
import cz.campanus.io.EntryLoader;

/**
 * @author jan.hadas@i.cz
 */
public class StoreTest {
    private static final String FILE_NAME = "test_entries.txt";
    private static final Logger LOG = LoggerFactory.getLogger(StoreTest.class);

    private EntryFileStore store;

    @Before
    public void setUp() throws Exception {
        store = new EntryFileStore(FILE_NAME);
    }

    @Test
    public void should_store_read_entry() throws Exception {
        EntryDto entry = createEntry("Test", "http://test.cz/Hello.html", "Blah blah..");

        store.storeAllEntries(asList(entry));

        File file = new File(FILE_NAME);
        assertTrue(file.exists());
        LOG.info("Stored file: {}", file.getAbsolutePath());

        EntryLoader loader = new EntryFileLoader(FILE_NAME);
        List<EntryDto> entries = loader.loadAllEntries();

        assertNotNull(entries);
        assertFalse(entries.isEmpty());

        EntryDto first = entries.get(0);

        LOG.info("Title: {}, url: {}", first.getTitle(), first.getDetailUrl());
    }

    private EntryDto createEntry(String title, String url, String... content) {
        EntryDto entry = new EntryDto();
        entry.setTitle(title);
        entry.setDetailUrl(url);

        for (String line : content) {
            entry.getContent().add(line);
        }

        return entry;
    }
}

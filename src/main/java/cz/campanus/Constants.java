package cz.campanus;

import java.time.format.DateTimeFormatter;

/**
 * @author jan.hadas@i.cz
 */
public interface Constants {
    static final String SEPARATOR = ":";
    static final String FILE_PATH = "/home/hadasj/app/Campanus/";
    static final int PARSED_ENTRY_COUNT = 5;

    static final String ENTRY_START_PATTERN = "content";
    static final String ENTRY_END_PATTERN = "#content";
    final static String TITLE_PATTERN = "entry-title";
    final static String CONTENT_PATTERN = "entry-content";
}

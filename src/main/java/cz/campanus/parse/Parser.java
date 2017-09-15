package cz.campanus.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.campanus.dto.EntryDto;

/**
 * @author jan.hadas@i.cz
 */
public abstract class Parser {

    /**
     * Parses count entries from input stream
     * @param input - @NotNull stream
     * @param count - max 10
     * @param startPattern - text to be parsed starts with this pattern
     * @param endPattern - parsed text ends with this pattern
     * @return list of parsed entries
     */
    public List<EntryDto> parse(final BufferedReader input, final int count, final String startPattern,
                                final String endPattern) throws IOException {
        final List<EntryDto> parsedEntries = new ArrayList<EntryDto>();

        String line;
        int entriesCount = 0;
        boolean start = false;

        do {
            line = input.readLine();
            if (line != null) {
                if (line.contains(startPattern)) {
                    start = true;
                }

                if (start) {
                    EntryDto entry = parseEntry(input);
                    parsedEntries.add(entry);
                    entriesCount++;
                }
            }
        } while (line != null && entriesCount < count && !line.contains(endPattern));

        return parsedEntries;
    }

    protected abstract EntryDto parseEntry(final BufferedReader input) throws IOException;
}

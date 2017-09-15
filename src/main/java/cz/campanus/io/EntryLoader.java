package cz.campanus.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.campanus.dto.EntryDto;

/**
 * @author jan.hadas@i.cz
 */
public abstract class EntryLoader {

    public List<EntryDto> loadAllEntries() throws IOException {
        List<EntryDto> loadedEntries = new ArrayList<>();
        EntryDto entry = loadEntry();

        while (entry != null) {
            loadedEntries.add(entry);
            entry = loadEntry();
        }
        close();

        return loadedEntries;
    }

    protected abstract EntryDto loadEntry() throws IOException;

    protected abstract void close() throws IOException;
}

package cz.campanus.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.campanus.dto.EntryDto;

/**
 * @author jan.hadas@i.cz
 */
public abstract class EntryStore {

    public void storeAllEntries(List<EntryDto> entries) throws IOException {
        for (EntryDto entry : entries) {
            storeEntry(entry);
        }
        close();
    }

    protected abstract void storeEntry(EntryDto entry) throws IOException;

    protected abstract void close() throws IOException;
}

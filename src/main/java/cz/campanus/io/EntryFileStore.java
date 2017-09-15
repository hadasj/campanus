package cz.campanus.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import cz.campanus.dto.EntryDto;
import static cz.campanus.Constants.SEPARATOR;

/**
 * @author jan.hadas@i.cz
 */
public class EntryFileStore extends EntryStore {
    private PrintWriter output;

    public EntryFileStore(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        output = new PrintWriter(new FileOutputStream(file));
    }

    @Override
    protected void storeEntry(EntryDto entry) throws IOException {
        output.print(entry.getTitle());
        output.print(SEPARATOR);
        output.print(entry.getDetailUrl());
        output.println();
    }

    @Override
    protected void close() throws IOException {
        output.flush();
        output.close();
    }
}

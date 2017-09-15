package cz.campanus.io;

import static cz.campanus.Constants.SEPARATOR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.campanus.dto.EntryDto;

/**
 * @author jan.hadas@i.cz
 */
public class EntryFileLoader extends EntryLoader {
    private BufferedReader reader;

    public EntryFileLoader(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } else {
            file.createNewFile();
        }
    }

    @Override
    protected EntryDto loadEntry() throws IOException {
        if (reader == null)
            return null;

        String line = reader.readLine();
        if (line == null)
            return null;

        EntryDto entry = new EntryDto();
        entry.setTitle(line.substring(0, line.indexOf(SEPARATOR)));
        entry.setDetailUrl(line.substring(line.indexOf(SEPARATOR) + 1));

        return entry;
    }

    @Override
    protected void close() throws IOException {
        if (reader != null)
            reader.close();
    }
}

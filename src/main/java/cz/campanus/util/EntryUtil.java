package cz.campanus.util;

import cz.campanus.dto.EntryDto;

/**
 * @author jan.hadas@i.cz
 */
public class EntryUtil {
    public static String entryToString(EntryDto dto) {
        String content = "<h3>" + dto.getTitle() + "</h3><br/>";
        for (String line : dto.getContent())
            content += line + "<br/>";
        return content;
    }
}

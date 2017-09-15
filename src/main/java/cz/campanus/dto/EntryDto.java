package cz.campanus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jan.hadas@i.cz
 */
public class EntryDto implements Serializable {

    private String title;

    private String detailUrl;

    private List<String> content = new ArrayList<String>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public List<String> getContent() {
        return content;
    }
}

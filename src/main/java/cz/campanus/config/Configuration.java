package cz.campanus.config;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import cz.campanus.security.Cipher;

/**
 * @author jan.hadas@i.cz
 */
public class Configuration {
    private Properties properties;
    private Set<String> emails = new HashSet<>();
    private String jardaFile;
    private String jardaSubject;
    private String jardaUrl;
    private String jardaLog;
    private String standaFile;
    private String standaSubject;
    private String standaUrl;
    private String standaLog;
    private String filePath;
    private int entryCount;
    private String entryStartPattern;
    private String entryStopPattern;
    private String titlePattern;
    private String contentPattern;
    private String password;
    private LocalTime startAt;
    private int intervalInMinutes;

    public Configuration(InputStream input) throws IOException {
        properties = new Properties();
        properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        init();
    }

    public void init() {
        String email = properties.getProperty("email");
        if (isNotEmpty(email))
            for(String mail : email.split(","))
                emails.add(mail);
        jardaFile = properties.getProperty("jarda.file");
        jardaSubject = properties.getProperty("jarda.subject");
        jardaUrl = properties.getProperty("jarda.url");
        jardaLog = properties.getProperty("jarda.log");
        standaFile = properties.getProperty("standa.file");
        standaSubject = properties.getProperty("standa.subject");
        standaUrl = properties.getProperty("standa.url");
        standaLog = properties.getProperty("standa.log");
        filePath = properties.getProperty("file.path");
        entryCount = Integer.parseInt(properties.getProperty("entry.count"));
        entryStartPattern = properties.getProperty("entry.start.pattern");
        entryStopPattern = properties.getProperty("entry.stop.pattern");
        titlePattern = properties.getProperty("title.pattern");
        contentPattern = properties.getProperty("content.pattern");
        password = new Cipher().decrypt(properties.getProperty("password"));
        final String[] startTime = properties.getProperty("start.at.time").split(":");
        startAt = LocalTime.of(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]));
        intervalInMinutes = Integer.parseInt(properties.getProperty("interval.in.minutes"));
    }

    public Set<String> getEmails() {
        return emails;
    }

    public String getJardaFile() {
        return jardaFile;
    }

    public String getJardaSubject() {
        return jardaSubject;
    }

    public String getJardaUrl() {
        return jardaUrl;
    }

    public String getJardaLog() {
        return jardaLog;
    }

    public String getStandaFile() {
        return standaFile;
    }

    public String getStandaSubject() {
        return standaSubject;
    }

    public String getStandaUrl() {
        return standaUrl;
    }

    public String getStandaLog() {
        return standaLog;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public String getEntryStartPattern() {
        return entryStartPattern;
    }

    public String getEntryStopPattern() {
        return entryStopPattern;
    }

    public String getTitlePattern() {
        return titlePattern;
    }

    public String getContentPattern() {
        return contentPattern;
    }

    public String getPassword() {
        return password;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public int getIntervalInMinutes() {
        return intervalInMinutes;
    }
}

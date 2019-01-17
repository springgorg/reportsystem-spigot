package fr.springg.reportsystem.mods;

public class Report {

    private String name;
    private String author;
    private String reason;

    public Report(String name, String author, String reason){
        this.name = name;
        this.author = author;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getReason() {
        return reason;
    }
}

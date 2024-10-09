package org.sopt.seminar1;

public class Diary {

    private long id;
    private String content;

    public Diary(final long id, final String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

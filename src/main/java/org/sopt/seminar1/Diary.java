package org.sopt.seminar1;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Diary {

    private final long id;
    private String body;
    private final LocalDateTime createdAt;

    private Diary(long id, String body) {
        this.id = id;
        this.body = body;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static Diary create(final long id, final String body) {
        return new Diary(id, body);
    }
}

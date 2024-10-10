package org.sopt.seminar1;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Diary {

    private final long id;
    private String body;
    private LocalDateTime updatedAt;
    private int updateCount = 0;

    private Diary(long id, String body) {
        this.id = id;
        this.body = body;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getUpdateCount() { return updateCount; }

    public void updateBody(String body) {
        if (updatedAt.getDayOfMonth() < LocalDateTime.now().getDayOfMonth()) {
            updateCount = 0;
        }
        this.body = body;
        this.updatedAt = LocalDateTime.now();
        this.updateCount += 1;
    }

    public static Diary create(final long id, final String body) {
        return new Diary(id, body);
    }
}

package com.mszalek.szalkoczat;

/**
 * Created by Marcinus on 2016-08-18.
 */
public class Message {

    private String authorName;
    private String text;

    public Message() {
    }

    public Message(String text, String authorName) {
        this.text = text;
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

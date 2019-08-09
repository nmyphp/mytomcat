package com.github.nmyphp.mytomcat;

import com.github.nmyphp.mytomcat.exception.UnkownContentTypeException;

public enum ContentType {
    TEXT("text/html"),
    JSON("application/json");

    private String type;

    ContentType(String type) {
        this.type = type;
    }

    public static ContentType match(String type) {
        for (ContentType contentType : ContentType.values()) {
            if (contentType.type.equalsIgnoreCase(type)) {
                return contentType;
            }
        }
        throw new UnkownContentTypeException();
    }

    @Override
    public String toString() {
        return "Content-Type: " + type;
    }
}

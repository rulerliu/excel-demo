package com.mayikt.enums;

import java.util.Arrays;
import java.util.Objects;

public enum LoginType {
    TYPE_YZY("YZY"),
    TYPE_TYRZ("TYRZ"),
    TYPE_YZH("YZH");
    String name;

    LoginType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static LoginType getByKey(String key) {
        return Arrays.stream(LoginType.values()).filter(f -> Objects.equals(f.getName(), key)).findFirst().orElse(LoginType.TYPE_YZY);
    }
}
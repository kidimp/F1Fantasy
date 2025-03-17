package com.example.f1fantasy.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionTypeEnum {

    PRACTICE("Practice"),
    QUALIFYING("Qualifying"),
    RACE("Race");

    @JsonValue
    private final String value;

    public static SessionTypeEnum fromValue(String value) {
        for (SessionTypeEnum s : SessionTypeEnum.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unexpected value SessionTypeEnum '" + value + "'");
    }
}

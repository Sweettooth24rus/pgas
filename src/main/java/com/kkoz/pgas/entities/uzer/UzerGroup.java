package com.kkoz.pgas.entities.uzer;


import com.kkoz.pgas.controls.exceptions.NotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UzerGroup {

    BPI18_01    ("BPI18_01", "Администратор"),
    BIS18_01     ("BIS18_01", "Мега"),
    BIA18_01    ("BIA18_01", "Гипер"),
    BIA18_02    ("BIA18_02", "Гипер"),
    BIE18_01    ("BIE18_01", "Гипер"),
    BKB18_01    ("BKB18_01", "Гипер"),
    BCA18_01    ("BCA18_01", "Гипер"),
    BCI18_01    ("BCI18_01", "Гипер"),
    BPM18_01    ("BPM18_01", "Гипер"),
    BSK18_01    ("BSK18_01", "Гипер"),
    TB18_01    ("TB18_01", "Гипер");

    private final String key;
    private final String value;

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static UzerGroup getGroupFromValue(String value) {
        for (UzerGroup group : UzerGroup.values()) {
            if (group.getValue().equals(value)) {
                return group;
            }
        }
        throw new NotFoundException(UzerGroup.class.getSimpleName(), "value", value);
    }

    public static UzerGroup getGroupFromKey(String key) {
        for (UzerGroup group : UzerGroup.values()) {
            if (group.getKey().equals(key)) {
                return group;
            }
        }
        throw new NotFoundException(UzerGroup.class.getSimpleName(), "key", key);
    }

}

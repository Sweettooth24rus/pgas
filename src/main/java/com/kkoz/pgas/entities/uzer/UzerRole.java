package com.kkoz.pgas.entities.uzer;


import com.kkoz.pgas.controls.exceptions.NotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UzerRole {

    STUDENT    ("STUDENT", "Студент"),
    EXPERT     ("EXPERT", "Эксперт");

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

    public static UzerRole getRoleFromValue(String value) {
        for (UzerRole role : UzerRole.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new NotFoundException(UzerRole.class.getSimpleName(), "value", value);
    }

    public static UzerRole getRoleFromKey(String key) {
        for (UzerRole role : UzerRole.values()) {
            if (role.getKey().equals(key)) {
                return role;
            }
        }
        throw new NotFoundException(UzerRole.class.getSimpleName(), "key", key);
    }

}

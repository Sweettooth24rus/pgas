package com.kkoz.pgas.entities;


import com.kkoz.pgas.controls.exceptions.NotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {

    SCIENCE     ("SCIENCE", "Научно-исследовательская деятельность"),
    SOCIAL      ("SOCIAL", "Общественная деятельность"),
    STUDY       ("STUDY", "Учебная деятельность"),
    ART         ("ART", "Культурно-творческая деятельность"),
    SPORT       ("SPORT", "Спортивная деятельность");

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

    public static Direction getGroupFromValue(String value) {
        for (Direction group : Direction.values()) {
            if (group.getValue().equals(value)) {
                return group;
            }
        }
        throw new NotFoundException(Direction.class.getSimpleName(), "value", value);
    }

    public static Direction getGroupFromKey(String key) {
        for (Direction group : Direction.values()) {
            if (group.getKey().equals(key)) {
                return group;
            }
        }
        throw new NotFoundException(Direction.class.getSimpleName(), "key", key);
    }

}

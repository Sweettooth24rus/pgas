package com.kkoz.pgas.entities.dictionaries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DirectionDictionary extends EntityDictionary implements Serializable {

    public static final DirectionDictionary SCIENCE = new DirectionDictionary(1, "SCIENCE", "Научно-исследовательская деятельность");
    public static final DirectionDictionary SOCIAL  = new DirectionDictionary(2, "SOCIAL", "Общественная деятельность");
    public static final DirectionDictionary STUDY   = new DirectionDictionary(3, "STUDY", "Учебная деятельность");
    public static final DirectionDictionary ART     = new DirectionDictionary(4, "ART", "Учебная деятельность");
    public static final DirectionDictionary SPORT   = new DirectionDictionary(5, "SPORT", "Спортивная деятельность");

    @Id
    @JsonIgnore
    private Integer id;

    @JsonProperty(value = "key")
    private String key;

    @JsonProperty(value = "value")
    private String value;

}

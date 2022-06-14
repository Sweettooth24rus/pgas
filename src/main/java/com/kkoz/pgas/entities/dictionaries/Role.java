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
public class Role extends EntityDictionary implements Serializable {

    public static final Role STUDENT    = new Role(1, "STUDENT", "Студент");
    public static final Role EXPERT     = new Role(2, "EXPERT", "Эксперт");

    @Id
    @JsonIgnore
    private Integer id;

    @JsonProperty(value = "key")
    private String key;

    @JsonProperty(value = "value")
    private String value;

}

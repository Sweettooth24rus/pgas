package com.kkoz.pgas.entities.dictionaries;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TypeDocument extends EntityDictionary implements Serializable {

    public static final TypeDocument APPLICATION_DOCUMENT = new TypeDocument(1, "APPLICATION_DOCUMENT", "Документ заявки");

    @Id
    @JsonIgnore
    private Integer id;

    private String key;

    private String value;
}

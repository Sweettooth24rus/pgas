package com.kkoz.pgas.entities.dictionaries.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkoz.pgas.entities.dictionaries.EntityDictionary;
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
public class StatusApplication extends EntityDictionary implements Serializable {

    public static final StatusApplication DRAFT             = new StatusApplication(1, "DRAFT", "Черновик");
    public static final StatusApplication IN_PROCESSING     = new StatusApplication(2, "IN_PROCESSING", "На рассмотрении");
    public static final StatusApplication FOR_REVISION      = new StatusApplication(3, "FOR_REVISION", "Требует доработок");
    public static final StatusApplication ACCEPTED          = new StatusApplication(4, "ACCEPTED", "Рассмотрена");
    public static final StatusApplication CLOSED            = new StatusApplication(5, "CLOSED", "Закрыт");

    @Id
    @JsonIgnore
    private Integer id;

    private String key;

    private String value;

}

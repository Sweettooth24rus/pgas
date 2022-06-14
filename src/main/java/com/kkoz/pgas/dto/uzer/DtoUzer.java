package com.kkoz.pgas.dto.uzer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkoz.pgas.entities.uzer.Uzer;
import com.kkoz.pgas.entities.uzer.UzerRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoUzer {

    private Integer id;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String patronymicName;
    private String email;
    private String uzerGroup;
    private String role;
    private Boolean active;

    public DtoUzer(Uzer entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.password = "";
        this.lastName = entity.getLastName();
        this.firstName = entity.getFirstName();
        this.patronymicName = entity.getPatronymicName();
        this.email = entity.getEmail();
        this.uzerGroup = entity.getUzerGroup();
        this.role = entity.getRole().getKey();
        this.active = entity.getActive();
    }
}

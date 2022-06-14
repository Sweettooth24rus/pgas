package com.kkoz.pgas.dto.uzer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkoz.pgas.entities.uzer.Uzer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoUzerUpdate {

    private Integer id;
    private String username;
    private String password;
    private String newPassword;
    private String lastName;
    private String firstName;
    private String patronymicName;
    private String email;
    private String uzerGroup;

    public DtoUzerUpdate(DtoUzer entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.password = "";
        this.lastName = entity.getLastName();
        this.firstName = entity.getFirstName();
        this.patronymicName = entity.getPatronymicName();
        this.email = entity.getEmail();
        this.uzerGroup = entity.getUzerGroup();
    }
}

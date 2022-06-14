package com.kkoz.pgas.entities.uzer;

import com.kkoz.pgas.entities.meta.MetaEntityInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Uzer extends MetaEntityInteger {

    @Column(unique = true)
    private String username;

    private String password;

    private String lastName;

    private String firstName;

    private String patronymicName;

    private String email;

    private String uzerGroup;

    @Enumerated(EnumType.STRING)
    private UzerRole role = UzerRole.STUDENT;

    private Boolean active = false;
}

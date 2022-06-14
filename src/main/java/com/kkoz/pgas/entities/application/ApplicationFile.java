package com.kkoz.pgas.entities.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kkoz.pgas.entities.dictionaries.EntityDictionary;
import com.kkoz.pgas.entities.file.FileUpload;
import com.kkoz.pgas.entities.meta.MetaEntityInteger;
import com.kkoz.pgas.entities.meta.MetaEntityWithFiles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ApplicationFile extends MetaEntityInteger {

    private String description;
    private Integer score = 0;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private FileUpload file;
}
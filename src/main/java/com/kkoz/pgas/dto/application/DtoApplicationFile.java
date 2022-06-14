package com.kkoz.pgas.dto.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kkoz.pgas.dto.file.DtoFileUpload;
import com.kkoz.pgas.entities.application.ApplicationFile;
import com.kkoz.pgas.entities.file.FileUpload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoApplicationFile {

    private Integer id;
    private String description;
    private Integer score;
    private DtoFileUpload file;

    public DtoApplicationFile(ApplicationFile entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.score = entity.getScore();
        this.file = new DtoFileUpload(entity.getFile());
    }
}

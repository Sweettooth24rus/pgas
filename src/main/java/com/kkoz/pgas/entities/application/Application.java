package com.kkoz.pgas.entities.application;

import com.kkoz.pgas.entities.Direction;
import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.entities.meta.MetaEntityInteger;
import com.kkoz.pgas.entities.meta.MetaEntityWithFiles;
import com.kkoz.pgas.entities.uzer.Uzer;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Application extends MetaEntityInteger {

    @Enumerated(EnumType.STRING)
    private StateApplication status = StateApplication.DRAFT;
    @OneToMany
    private List<ApplicationFile> applicationFileList;

    private String comment = null;

    @ManyToOne
    private Competition competition;

    @ManyToOne
    private Uzer uzer;

    private Integer sumScore = 0;
}

package com.kkoz.pgas.services;

import com.kkoz.pgas.controls.exceptions.MismatchException;
import com.kkoz.pgas.controls.exceptions.NotFoundException;
import com.kkoz.pgas.controls.exceptions.UniqueUzerException;
import com.kkoz.pgas.dto.uzer.DtoUzer;
import com.kkoz.pgas.dto.uzer.DtoUzerCredentials;
import com.kkoz.pgas.dto.uzer.DtoUzerUpdate;
import com.kkoz.pgas.entities.uzer.Uzer;
import com.kkoz.pgas.filters.SpecUzer;
import com.kkoz.pgas.repositories.RepoUzer;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceUzer {

    private final RepoUzer repoUzer;
    private static Uzer currentUzer;

    @SneakyThrows
    public Uzer loadUserByUsername(String username) {
        return repoUzer.getByUsername(username)
                .orElseThrow(() -> new LoginException(
                        "Неверно указан логин или пароль"
                ));
    }

    @SneakyThrows
    public Uzer getByUsername(String username) {
        return repoUzer.getByUsername(username)
                .orElseThrow(() -> new LoginException(
                        "Неверно указан логин или пароль"
                ));
    }

    public Uzer getByCredentials(DtoUzerCredentials credentials) {
        String encodedPassword = this.getByUsername(credentials.getUsername()).getPassword();
        if (credentials.getPassword().equals(encodedPassword)) {
            Uzer tmp = repoUzer.getByUsername(credentials.getUsername()).get();
            currentUzer = tmp;
            return tmp;
        }
        else {
            throw new NotFoundException("Неправильно указан логин или пароль");
        }
    }

    public Uzer getCurrentUzer() {
        return currentUzer;
    }

    public Uzer getById(Integer id) {
        return repoUzer.getUzerById(id).orElseThrow(
                () -> new NotFoundException(id, Uzer.class.getSimpleName())
        );
    }

    public Uzer createUzer(DtoUzer dtoUzer) {
        if (repoUzer.existsByUsername(dtoUzer.getUsername())) {
            return null;
        }
        Uzer uzer = new Uzer();
        return this.saveUzer(uzer, new DtoUzerUpdate(dtoUzer));
    }

    public Uzer updateUzer(Integer id, DtoUzerUpdate dtoUzer) {
        if (!id.equals(dtoUzer.getId())) {
            throw new MismatchException(
                    "Provided id [" + id + "] isn't equal to provided DTO id [" + dtoUzer.getId() + "]"
            );
        }

        Uzer uzer = repoUzer.getUzerById(id).orElseThrow(
                () -> new NotFoundException(id, Uzer.class.getSimpleName())
        );

        return this.saveUzer(uzer, dtoUzer);
    }

    private Uzer saveUzer(Uzer uzer, DtoUzerUpdate dtoUzer) {
        uzer.setUsername(dtoUzer.getUsername());

        if (dtoUzer.getPassword() != null && !dtoUzer.getPassword().isBlank()) {
            uzer.setPassword(
                    dtoUzer.getPassword()
            );
        }
        uzer.setLastName(dtoUzer.getLastName());
        uzer.setFirstName(dtoUzer.getFirstName());
        uzer.setPatronymicName(dtoUzer.getPatronymicName());
        uzer.setEmail(dtoUzer.getEmail());
        uzer.setUzerGroup(dtoUzer.getUzerGroup());
        Uzer savedUzer;

        try {
            savedUzer = repoUzer.save(uzer);
        } catch (DataIntegrityViolationException e) {
            ConstraintViolationException constraintViolation = (ConstraintViolationException) e.getCause();
            throw new UniqueUzerException(constraintViolation.getConstraintName());
        }

        return savedUzer;
    }

    public boolean existsByUsername(String username) {
        return repoUzer.existsByUsername(username);
    }

    public boolean checkActive(String username) {
        return repoUzer.getByUsername(username).get().getActive();
    }

    public JsonNode mail(DtoUzer uzer) throws UnirestException {
        String text;
        text = "Здравствуй. Необходимо подтвердить аккаунт. " +
                "Для этого необходимо перейти по ссылке:\n " +
                "https://pgas.herokuapp.com/api/user/activity/"
                + uzer.getUsername() + "/" + uzer.getId() ;
        HttpResponse<JsonNode> request = Unirest.post(System.getenv("MAIL_GUN_DOMAIN") + "/messages")
                .basicAuth("api", System.getenv("MAIL_GUN_KEY"))
                .queryString("from", "pgas <System@kkoz.pgas.com>")
                .queryString("to", uzer.getEmail())
                .queryString("subject", "Подтверждение аккаунта")
                .queryString("text", text)
                .asJson();
        return request.getBody();
    }

    public void updateUzerActivity(String username, Integer key) throws LoginException {
        Uzer uzerMail = repoUzer.getByUsername(username).orElseThrow(
                () -> new NotFoundException(username, Uzer.class.getSimpleName())
        );

        if (!uzerMail.getId().equals(key)) {
            throw new LoginException("Логин и ключ не соотносятся");
        }

        uzerMail.setActive(true);
        repoUzer.save(uzerMail);
    }
}

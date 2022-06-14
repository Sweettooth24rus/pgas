package com.kkoz.pgas.controls;


import com.kkoz.pgas.controls.exceptions.NotFoundException;
import com.kkoz.pgas.dto.uzer.DtoUzer;
import com.kkoz.pgas.dto.uzer.DtoUzerCredentials;
import com.kkoz.pgas.dto.uzer.DtoUzerUpdate;
import com.kkoz.pgas.entities.uzer.Uzer;
import com.kkoz.pgas.entities.uzer.UzerRole;
import com.kkoz.pgas.services.ServiceUzer;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class ApiUzer {

    private final ServiceUzer serviceUzer;

    @GetMapping("/{id}")
    public DtoUzer getUzer(@PathVariable @Min(1) Integer id) {
        log.info("-> GET: Getting Uzer with id [{}]", id);
        DtoUzer dtoUzer = new DtoUzer(serviceUzer.getById(id));
        log.info("<- GET: Got Uzer with id [{}]: {}", dtoUzer.getId(), dtoUzer);
        return dtoUzer;
    }

    @GetMapping("/all/roles")
    public List<String> getRoles() {
        return Arrays.stream(UzerRole.values())
                .map(UzerRole::getValue)
                .sorted()
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateUzer(@PathVariable @Min(1) Integer id,
                                                          @RequestBody DtoUzerUpdate uzer) {
        log.info("-> PUT: Updating user [{}]: {}", id, uzer);
        Uzer updatedUzer = serviceUzer.updateUzer(id, uzer);
        Map<String, String> response = new HashMap<>();
        response.put("response", "User [" + updatedUzer.getId() + "] was updated");
        log.info("<- PUT: User [{}] was updated", updatedUzer.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/current")
    public DtoUzer getCurrentUzer() {
        log.info("-> GET: Getting current Uzer");
        DtoUzer dtoUzer = new DtoUzer(serviceUzer.getCurrentUzer());
        log.info("<- GET: Got current Uzer");
        return dtoUzer;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody DtoUzerCredentials credentials) throws LoginException {
        log.info("-> POST: Authentication: uzername[{}]", credentials.getUsername());

        try {

            final String username = credentials.getUsername();
            final String password = credentials.getPassword();

            Uzer uzer;

            if (serviceUzer.existsByUsername(username)) {
                uzer = serviceUzer.getByCredentials(new DtoUzerCredentials(username, password));
                if (!uzer.getActive()) {
                    throw new NotFoundException("Неверно указан логин или пароль");
                }
            }
            else {
                throw new NotFoundException("Неверно указан логин или пароль");
            }
        } catch (NotFoundException e) {
            log.debug("<- POST: Authentication failed. There is no such uzer with uzername[{}]", credentials.getUsername());
            throw new LoginException("Неверно указан логин или пароль");
        }

        if (!serviceUzer.checkActive(credentials.getUsername())) {
            throw new LoginException("Аккаунт не активирован");
        }

        return ResponseEntity.ok("Успешно");
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> createUzer(@RequestBody DtoUzer uzer) throws UnirestException {
        log.info("-> POST: Adding new user: {}", uzer);
        Map<String, String> response = new HashMap<>();
        Uzer createdUzer = serviceUzer.createUzer(uzer);
        if (createdUzer == null) {
            response.put("Ошибка","Пользователь с таким логином уже существует");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        response.put("id", createdUzer.getId().toString());
        response.put("response", "User created with id [" + createdUzer.getId() + "]");
        log.info("<- POST: User created with id [{}]", createdUzer.getId());
        response.put("mail", serviceUzer.mail(uzer).toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/activity/{username}/{key}")
    public ResponseEntity<Map<String, String>> updateUzerActivity(@PathVariable String username,
                                                                      @PathVariable Integer key) throws LoginException {
        log.info("-> PUT: Updating user [{}] activity", username);
        serviceUzer.updateUzerActivity(username, key);
        Map<String, String> response = new HashMap<>();
        response.put("response", "User [" + username + "] activity was updated");
        log.info("<- PUT: User [{}] activity was updated", username);
        return ResponseEntity.ok(response);
    }
}

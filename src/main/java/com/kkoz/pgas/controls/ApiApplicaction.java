package com.kkoz.pgas.controls;

import com.kkoz.pgas.dto.application.DtoApplication;
import com.kkoz.pgas.dto.application.DtoApplicationPagination;
import com.kkoz.pgas.dto.application.DtoApplicationShow;
import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.services.ServiceApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application")
public class ApiApplicaction {

    private final ServiceApplication serviceApplication;

    @Transactional
    @GetMapping("/all")
    public List<DtoApplicationPagination> getPage(@RequestParam String direction) {
        log.info("-> GET: Getting recipe page");
        List<DtoApplicationPagination> applicationList = serviceApplication.getList(direction);
        log.info("<- GET: Got recipe page");
        return applicationList;
    }

    @Transactional
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> createRecipe(@RequestBody DtoApplication recipe) {
        log.info("-> POST: Adding new recipe: {}", recipe);
        Application createdApplication = serviceApplication.createApplication(recipe);
        Map<String, String> response = new HashMap<>();
        response.put("id", createdApplication.getId().toString());
        response.put("response", "Recipe created with id [" + createdApplication.getId() + "]");
        log.info("<- POST: Recipe created with id [{}]", createdApplication.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/{id}")
    public DtoApplicationShow getRecipe(@PathVariable Integer id) {
        log.info("-> GET: Getting Recipe with id [{}]", id);
        DtoApplicationShow dtoRecipe = new DtoApplicationShow(serviceApplication.getById(id));
        log.info("<- GET: Got Recipe with id [{}]: {}", dtoRecipe.getId(), dtoRecipe);
        return dtoRecipe;
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateRecipe(@PathVariable Integer id,
                                                          @RequestBody DtoApplication recipe) {
        log.info("-> PUT: Updating recipe [{}]: {}", id, recipe);
        Application updatedRecipe = serviceApplication.updateApplication(id, recipe);
        Map<String, String> response = new HashMap<>();
        response.put("response", "Recipe [" + updatedRecipe.getId() + "] was updated");
        log.info("<- PUT: Recipe [{}] was updated", updatedRecipe.getId());
        return ResponseEntity.ok(response);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceApplication.deleteApplication(id));
    }
}

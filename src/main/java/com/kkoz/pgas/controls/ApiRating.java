package com.kkoz.pgas.controls;

import com.kkoz.pgas.dto.application.DtoApplication;
import com.kkoz.pgas.dto.application.DtoApplicationPagination;
import com.kkoz.pgas.dto.application.DtoApplicationShow;
import com.kkoz.pgas.dto.rating.DtoRatingPagination;
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
@RequestMapping("/api/rating")
public class ApiRating {

    private final ServiceApplication serviceApplication;

    @Transactional
    @GetMapping("/all")
    public List<DtoRatingPagination> getPage(@RequestParam String direction) {
        log.info("-> GET: Getting recipe page");
        List<DtoRatingPagination> applicationList = serviceApplication.getRating(direction);
        log.info("<- GET: Got recipe page");
        return applicationList;
    }
}

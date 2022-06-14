package com.kkoz.pgas.filters;

import com.kkoz.pgas.entities.application.Application;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class SpecRecipe {

    public Specification<Application> getNameFilter(String name) {
        /*return (root, criteriaQuery, criteriaBuilder) -> (
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Recipe_.NAME)),
                        "%" + name.toLowerCase() + "%"
                )
        );*/
        return new Specification<Application>() {
            @Override
            public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    public Specification<Application> getDifficultyFilter(/*Difficulty difficulty*/) {
        /*return (root, criteriaQuery, criteriaBuilder) -> (
                criteriaBuilder.equal(root.get(Recipe_.DIFFICULTY), difficulty
                )
        );*/
        return new Specification<Application>() {
            @Override
            public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
}

package com.kkoz.pgas.filters;

import com.kkoz.pgas.entities.uzer.Uzer;
import com.kkoz.pgas.entities.uzer.UzerRole;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class SpecUzer {

    public Specification<Uzer> getUsernameFilter(String username) {
        /*return (root, criteriaQuery, criteriaBuilder) -> (
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Uzer_.USERNAME)),
                        "%" + username.toLowerCase() + "%"
                )
        );*/
        return new Specification<Uzer>() {
            @Override
            public Predicate toPredicate(Root<Uzer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    public Specification<Uzer> getFirstNameFilter(String firstName) {
        /*return (root, criteriaQuery, criteriaBuilder) -> (
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Uzer_.FIRST_NAME)),
                        "%" + firstName.toLowerCase() + "%"
                )
        );*/
        return new Specification<Uzer>() {
            @Override
            public Predicate toPredicate(Root<Uzer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    public Specification<Uzer> getLastNameFilter(String lastName) {
        /*return (root, criteriaQuery, criteriaBuilder) -> (
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Uzer_.LAST_NAME)),
                        "%" + lastName.toLowerCase() + "%"
                )
        );*/
        return new Specification<Uzer>() {
            @Override
            public Predicate toPredicate(Root<Uzer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    public Specification<Uzer> getPatronymicNameFilter(String patronymicName) {
        /*return (root, criteriaQuery, criteriaBuilder) -> (
                criteriaBuilder.like(criteriaBuilder.lower(root.get(Uzer_.PATRONYMIC_NAME)),
                        "%" + patronymicName.toLowerCase() + "%"
                )
        );*/
        return new Specification<Uzer>() {
            @Override
            public Predicate toPredicate(Root<Uzer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    public Specification<Uzer> getRoleFilter(UzerRole role) {
        /*return (root, criteriaQuery, criteriaBuilder) -> (
                criteriaBuilder.isMember(role, root.get(Uzer_.ROLE))
        );*/
        return new Specification<Uzer>() {
            @Override
            public Predicate toPredicate(Root<Uzer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    public Specification<Uzer> getIsActiveFilter(Boolean isActive) {
        /*return (root, criteriaQuery, criteriaBuilder) -> (
                criteriaBuilder.equal(root.get(Uzer_.IS_ACTIVE), isActive)
        );*/
        return new Specification<Uzer>() {
            @Override
            public Predicate toPredicate(Root<Uzer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
}

package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.ConditionType;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.capgemini.wsb.persistence.enums.ConditionType.*;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
    @Override
    public List<PatientEntity> findAllByLastname(final String lastName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> criteriaQuery = builder.createQuery(getDomainClass());
        Root<PatientEntity> root = criteriaQuery.from(getDomainClass());
        criteriaQuery.select(root)
                .where(builder.equal(root.get("lastName"), lastName));
        TypedQuery<PatientEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithVisitsGreaterThan(int numberOfVisits) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> criteriaQuery = builder.createQuery(PatientEntity.class);
        Root<PatientEntity> patientRoot = criteriaQuery.from(PatientEntity.class);
        Join<PatientEntity, VisitEntity> visitJoin = patientRoot.join("visitEntities");
        criteriaQuery.select(patientRoot)
                .groupBy(patientRoot.get("id"))
                .having(builder.gt(builder.count(visitJoin), numberOfVisits));

        TypedQuery<PatientEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsByFieldCondition(String fieldName, Object value, ConditionType condition) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> criteriaQuery = builder.createQuery(PatientEntity.class);
        Root<PatientEntity> root = criteriaQuery.from(PatientEntity.class);

        switch(condition) {
            case GREATER_THAN:
                criteriaQuery.select(root)
                        .where(builder.greaterThan(root.get(fieldName), (Comparable) value));
                break;
            case LESS_THAN:
                criteriaQuery.select(root)
                        .where(builder.lessThan(root.get(fieldName), (Comparable) value));
                break;
            case AFTER:
                criteriaQuery.select(root)
                        .where(builder.greaterThan(root.get(fieldName), (Comparable) value));
                break;
            case BEFORE:
                criteriaQuery.select(root)
                        .where(builder.lessThan(root.get(fieldName), (Comparable) value));
                break;
            case CONTAINS:
                criteriaQuery.select(root)
                        .where(builder.like(root.get(fieldName), "%" + value + "%"));
                break;
            default:
                throw new IllegalArgumentException("Nieprawidłowy typ warunku.");
        }

        TypedQuery<PatientEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }


}

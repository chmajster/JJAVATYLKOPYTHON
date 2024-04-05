package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.VisitDao;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {
    @Override
    public List<VisitEntity> findAllByPatientId(Long patientId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VisitEntity> criteriaQuery = builder.createQuery(getDomainClass());
        Root<VisitEntity> root = criteriaQuery.from(getDomainClass());
        criteriaQuery.select(root)
                .where(builder.equal(root.get("patientId"), patientId));
        TypedQuery<VisitEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}

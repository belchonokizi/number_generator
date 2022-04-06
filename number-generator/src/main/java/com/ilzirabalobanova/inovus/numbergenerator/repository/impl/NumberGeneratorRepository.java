package com.ilzirabalobanova.inovus.numbergenerator.repository.impl;

import com.ilzirabalobanova.inovus.numbergenerator.entity.Number;
import com.ilzirabalobanova.inovus.numbergenerator.repository.INumberGeneratorRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class NumberGeneratorRepository implements INumberGeneratorRepository {
    private final EntityManager entityManager;

    @Autowired
    public NumberGeneratorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean isNumberExists(String number) {
        return entityManager.createQuery("select n from Number n " +
                "where n.numberValue = :number").setParameter("number", number).getFirstResult() != 0;
    }

    @Override
    public void saveNumber(Number number) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(number);
    }

    @Override
    public Number findLast() {
        return (Number) entityManager.createQuery("select n from Number n " +
                "where n.id = (select MAX(id) from Number)").getSingleResult();
    }
}

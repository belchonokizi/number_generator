package com.ilzirabalobanova.inovus.numbergenerator.repository;

import org.springframework.stereotype.Repository;

@Repository
public class NumberGeneratorRepository implements INumberGeneratorRepository {
    @Override
    public boolean isNumberExists(String number) {
        return false;
    }
}

package com.ilzirabalobanova.inovus.numbergenerator.repository;

import com.ilzirabalobanova.inovus.numbergenerator.entity.Number;

public interface INumberGeneratorRepository {

    boolean isNumberExists(String number);

    void saveNumber(Number number);
}

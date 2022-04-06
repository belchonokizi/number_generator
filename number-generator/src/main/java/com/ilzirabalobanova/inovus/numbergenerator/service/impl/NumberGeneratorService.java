package com.ilzirabalobanova.inovus.numbergenerator.service.impl;

import com.ilzirabalobanova.inovus.numbergenerator.generator.NextNumberGenerator;
import com.ilzirabalobanova.inovus.numbergenerator.generator.RandomNumberGenerator;
import com.ilzirabalobanova.inovus.numbergenerator.service.INumberGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberGeneratorService implements INumberGeneratorService {
    private static final String CONSTANT = " 116 RUS";
    private final List<Character> letters = List.of('А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М');

    private final RandomNumberGenerator randomNumberGenerator;
    private final NextNumberGenerator nextNumberGenerator;

    @Autowired
    public NumberGeneratorService(RandomNumberGenerator randomNumberGenerator, NextNumberGenerator nextNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.nextNumberGenerator = nextNumberGenerator;
    }

    @Override
    public String generateRandomNumber() {
        return randomNumberGenerator.generateRandomNumber(letters, CONSTANT);
    }

    @Override
    public String generateNextNumber() {
        return nextNumberGenerator.generateNextNumber(letters, CONSTANT);
    }
}

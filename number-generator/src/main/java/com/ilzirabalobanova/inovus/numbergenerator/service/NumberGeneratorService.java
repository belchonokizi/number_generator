package com.ilzirabalobanova.inovus.numbergenerator.service;

import com.ilzirabalobanova.inovus.numbergenerator.repository.INumberGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class NumberGeneratorService implements INumberGeneratorService {
    private static final String CONSTANT = " 116 RUS";

    private final INumberGeneratorRepository generatorRepository;

    @Autowired
    public NumberGeneratorService(INumberGeneratorRepository generatorRepository) {
        this.generatorRepository = generatorRepository;
    }

    @Override
    public String generateRandomNumber() {
        List<String> letters = List.of("А", "Е", "Т", "О", "Р", "Н", "У", "К", "Х", "С", "В", "М");
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        builder.append(letters.get(random.nextInt(letters.size())));
        for (int i = 0; i < 3; i++) {
            builder.append(random.nextInt(10));
        }
        builder.append(letters.get(random.nextInt(letters.size()))).append(letters.get(random.nextInt(letters.size())))
                .append(CONSTANT);
        return builder.toString();
    }

    @Override
    public String generateNextNumber() {
        return null;
    }
}

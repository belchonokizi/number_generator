package com.ilzirabalobanova.inovus.numbergenerator.generator;

import com.ilzirabalobanova.inovus.numbergenerator.entity.Number;
import com.ilzirabalobanova.inovus.numbergenerator.repository.INumberGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomNumberGenerator {
    private final Random random = new Random();

    private final INumberGeneratorRepository generatorRepository;

    @Autowired
    public RandomNumberGenerator(INumberGeneratorRepository generatorRepository) {
        this.generatorRepository = generatorRepository;
    }

    public String generateRandomNumber(List<Character> letters, String constant) {
        StringBuilder builder = new StringBuilder();
        String resultString;
        do {
            builder.append(letters.get(random.nextInt(letters.size())));
            for (int i = 0; i < 3; i++) {
                builder.append(random.nextInt(10));
            }
            builder.append(letters.get(random.nextInt(letters.size()))).append(letters.get(random.nextInt(letters.size())))
                    .append(constant);
            resultString = builder.toString();
        } while (generatorRepository.isNumberExists(resultString));
        generatorRepository.saveNumber(new Number(resultString));
        return resultString;
    }
}

package com.ilzirabalobanova.inovus.numbergenerator.service;

import com.ilzirabalobanova.inovus.numbergenerator.entity.Number;
import com.ilzirabalobanova.inovus.numbergenerator.repository.INumberGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class NumberGeneratorService implements INumberGeneratorService {
    private static final String CONSTANT = " 116 RUS";
    private List<Character> letters = List.of('А', 'Е', 'Т', 'О', 'Р', 'Н', 'У', 'К', 'Х', 'С', 'В', 'М');

    private final INumberGeneratorRepository generatorRepository;

    @Autowired
    public NumberGeneratorService(INumberGeneratorRepository generatorRepository) {
        this.generatorRepository = generatorRepository;
    }

    @Override
    public String generateRandomNumber() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        String resultString;
        do {
            builder.append(letters.get(random.nextInt(letters.size())));
            for (int i = 0; i < 3; i++) {
                builder.append(random.nextInt(10));
            }
            builder.append(letters.get(random.nextInt(letters.size()))).append(letters.get(random.nextInt(letters.size())))
                    .append(CONSTANT);
            resultString = builder.toString();
        } while (generatorRepository.isNumberExists(resultString));
        generatorRepository.saveNumber(new Number(resultString));
        return resultString;
    }

    @Override
    public String generateNextNumber() {
        String lastNumberValue = getLastNumber().getNumberValue();
        String newNumericPart = increaseNumeric(lastNumberValue);
        String newLetterPart = generateNewLetterPart(newNumericPart, lastNumberValue);
        StringBuilder builder = new StringBuilder();
        builder.append(lastNumberValue, 0, 1).append(newNumericPart).append(newLetterPart).append(CONSTANT);
        generatorRepository.saveNumber(new Number(builder.toString()));
        return builder.toString();
    }

    private Number getLastNumber() {
        Number lastNumber;
        try {
            lastNumber = generatorRepository.findLast();
        } catch (NoResultException e) {
            lastNumber = new Number(generateRandomNumber());
        }
        return lastNumber;
    }

    private String increaseNumeric(String number) {
        int numericPart = Integer.parseInt(number.substring(1, 4));
        int newNumericPart;
        if (numericPart < 999) {
            newNumericPart = numericPart + 1;
        } else {
            newNumericPart = 0;
        }
        return String.format("%03d", newNumericPart);
    }

    private String generateNewLetterPart(String newNumericPart, String numberValue) {
        List<Character> newList = new ArrayList<>(letters);
        Collections.sort(newList);
        StringBuilder sb;
        if (newNumericPart.equals("000")) {
            sb = new StringBuilder();
            if (numberValue.charAt(5) == 'X') {
                char oldValue = numberValue.charAt(4);
                char newValue = newList.get(newList.indexOf(oldValue) + 1);
                sb.append(newValue).append('A');
            } else {
                char oldValue = numberValue.charAt(5);
                char newValue = newList.get(newList.indexOf(oldValue) + 1);
                sb.append(numberValue.charAt(4)).append(newValue);
            }
        } else {
            sb = new StringBuilder(numberValue.substring(4, 6));
        }
        return sb.toString();
    }
}

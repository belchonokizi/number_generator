package com.ilzirabalobanova.inovus.numbergenerator.generator;

import com.ilzirabalobanova.inovus.numbergenerator.entity.Number;
import com.ilzirabalobanova.inovus.numbergenerator.repository.INumberGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class NextNumberGenerator {
    private final INumberGeneratorRepository generatorRepository;

    @Autowired
    public NextNumberGenerator(INumberGeneratorRepository generatorRepository) {
        this.generatorRepository = generatorRepository;
    }

    public String generateNextNumber(List<Character> list, String constant) {
        String lastNumberValue = generatorRepository.findLast().getNumberValue();
        String resultString;
        do {
            String newNumericPart = increaseNumeric(lastNumberValue);
            String newLetterPart = generateNewLetterPart(newNumericPart, lastNumberValue, list);
            StringBuilder builder = new StringBuilder();
            resultString = builder.append(lastNumberValue, 0, 1).append(newNumericPart)
                    .append(newLetterPart).append(constant).toString();
        } while (generatorRepository.isNumberExists(resultString));
        generatorRepository.saveNumber(new Number(resultString));
        return resultString;
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

    private String generateNewLetterPart(String newNumericPart, String numberValue, List<Character> letters) {
        List<Character> newList = new ArrayList<>(letters);
        Collections.sort(newList);
        StringBuilder sb;
        if (newNumericPart.equals("000")) {
            sb = new StringBuilder();
            if (numberValue.charAt(5) == 'Х') {
                char newValue = findNextChar(newList, numberValue.charAt(4));
                sb.append(newValue).append('А');
            } else {
                char newValue = findNextChar(newList, numberValue.charAt(5));
                sb.append(numberValue.charAt(4)).append(newValue);
            }
        } else {
            sb = new StringBuilder(numberValue.substring(4, 6));
        }
        return sb.toString();
    }

    private char findNextChar(List<Character> list, char oldValue) {
        return list.get(list.indexOf(oldValue) + 1);
    }
}

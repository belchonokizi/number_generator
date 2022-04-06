package com.ilzirabalobanova.inovus.numbergenerator;

import com.ilzirabalobanova.inovus.numbergenerator.service.INumberGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NumberGeneratorApplicationTests {
    private final String regex = "[АЕТОРНУКХСВМ]\\d{3}[АЕТОРНУКХСВМ]{2}\\s116\\sRUS";

    @Autowired
    private INumberGeneratorService numberGeneratorService;

    @Test
    void generateRandomNumber() {
        String randomNumber = numberGeneratorService.generateRandomNumber();
        assertThat(randomNumber, hasLength(14));
        assertThat(randomNumber, matchesPattern(regex));
    }

    @Test
    void generateNextNumber() {
        String nextNumber = numberGeneratorService.generateNextNumber();
        assertThat(nextNumber, hasLength(14));
        assertThat(nextNumber, matchesPattern(regex));
        assertEquals("А000ЕА 116 RUS", nextNumber);
    }
}

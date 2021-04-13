package com.betvictor.service;

import com.betvictor.testUtilities.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractMap;
import java.util.Map;

import static com.betvictor.utilites.Constants.ONE;
import static com.betvictor.utilites.Constants.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ParagraphCalculatorImplTest {

    @InjectMocks
    private ParagraphCalculatorImpl underTest;

    @Test
    public void testParagraphCalculatorImplTest_whenWordsAllDifferent_thenReturnKeyValueEntry() {
        //GIVEN
        String[] words = TestUtils.createWords("One", "Two", "Three");
        Map.Entry<String, Integer> frequentWord = new AbstractMap.SimpleEntry<>("One", ONE);
        //WHEN
        Map.Entry<String, Integer> result = underTest.calculateMostFrequentWord(words);
        //THEN
        assertEquals(result,frequentWord);
    }

    @Test
    public void testParagraphCalculatorImplTest_whenWordsHaveSameOne_thenReturnKeyValueEntry() {
        //GIVEN
        String[] words = TestUtils.createWords("One", "Two", "Two");
        Map.Entry<String, Integer> frequentWord = new AbstractMap.SimpleEntry<>("Two", 2);
        //WHEN
        Map.Entry<String, Integer> result = underTest.calculateMostFrequentWord(words);
        //THEN
        assertEquals(result,frequentWord);

    }

    @Test
    public void testParagraphCalculatorImplTest_whenNoWords_thenReturnDefaultKeyValueEntry() {
        //GIVEN
        String[] words = new String[0];
        Map.Entry<String, Integer> frequentWord = new AbstractMap.SimpleEntry<>("", ZERO);
        //WHEN
        Map.Entry<String, Integer> result = underTest.calculateMostFrequentWord(words);
        //THEN
        assertEquals(result,frequentWord);
    }
}

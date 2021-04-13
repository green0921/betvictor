package com.betvictor.service;

import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.betvictor.utilites.Constants.ONE;
import static com.betvictor.utilites.Constants.ZERO;

@Service
public class ParagraphCalculatorImpl implements ParagraphCalculator {

    @Override
    public Map.Entry<String, Integer> calculateMostFrequentWord(String[] words) {
        Map<String,Integer> occurrences= new LinkedHashMap<>();
        for (String word : words)
        {
            if (occurrences.containsKey(word))
                occurrences.put(word, occurrences.get(word) + ONE);
            else
                occurrences.put(word, ONE);
        }
        return occurrences.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(new AbstractMap.SimpleEntry<>("", ZERO));
    }
}

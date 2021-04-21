package com.betvictor.processing.service;

import java.util.Map;

public interface ParagraphCalculator {

    Map.Entry<String, Integer> calculateMostFrequentWord(String[] words);
}

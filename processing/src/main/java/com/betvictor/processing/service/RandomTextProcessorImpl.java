package com.betvictor.processing.service;

import com.betvictor.processing.model.ParagraphRequest;
import com.betvictor.processing.model.RandomTextResponse;
import com.betvictor.processing.model.StreamData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.IntStream;

import static com.betvictor.processing.utilites.Constants.REPLACE_REGEXP;
import static com.betvictor.processing.utilites.Constants.SPLIT_REGEXP;
import static org.springframework.util.StringUtils.countOccurrencesOf;

@Service
public class RandomTextProcessorImpl implements RandomTextProcessor {

    @Autowired
    private ParagraphFeign paragraphFeign;
    @Autowired
    private ParagraphCalculator paragraphCalculator;

    @Override
    public StreamData processRandomTexts(ParagraphRequest paragraphRequest) {
        StreamData streamData = new StreamData();
        IntStream.rangeClosed(paragraphRequest.getP_start(), paragraphRequest.getP_end())
                .parallel()
                .forEach(numberOfParagraphsRequest -> processRandomText(streamData, paragraphRequest, numberOfParagraphsRequest));
        return streamData;
    }

    private void processRandomText(StreamData streamData, ParagraphRequest paragraphRequest, int numberOfParagraphsRequest) {
        RandomTextResponse randomText = paragraphFeign.getRandomText(numberOfParagraphsRequest,
                paragraphRequest.getW_count_min(),
                paragraphRequest.getW_count_max());
        int numberOfParagraphs = countOccurrencesOf(randomText.getTextOut(), ".");
        String[] words = randomText.getTextOut().replaceAll(REPLACE_REGEXP, "")
                .split(SPLIT_REGEXP);
        Map.Entry<String, Integer> frequentWord = paragraphCalculator.calculateMostFrequentWord(words);
        updateStreamData(streamData, frequentWord, words.length, numberOfParagraphs);
    }

    private void updateStreamData(StreamData streamData, Map.Entry<String, Integer> frequentWord,
                                               int wordsNumberOfSum, int numberOfParagraphs) {
        if (frequentWord.getValue() > streamData.getFrequentWord().getValue()) {
            streamData.setFrequentWord(frequentWord);
        }
        streamData.getParagraphSizeOfSum().getAndAdd(numberOfParagraphs);
        streamData.getWordsNumberOfSum().getAndAdd(wordsNumberOfSum);
    }
}

package com.betvictor.service;

import com.betvictor.model.ParagraphRequest;
import com.betvictor.model.RandomTextResponse;
import com.betvictor.model.StreamData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.Map;

import static com.betvictor.testUtilities.TestUtils.*;
import static com.betvictor.utilites.Constants.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RandomTextProcessorImplTest {

    @Mock
    private ParagraphFeign paragraphFeign;
    @Mock
    private ParagraphCalculator paragraphCalculator;

    @InjectMocks
    private RandomTextProcessorImpl underTest;

    @Test
    public void testRandomTextProcessorImpl_whenRequestOneParagraphBetweenTwoAndFourRandomWords_thenReturnStreamData() {
        //GIVEN
        ParagraphRequest paragraphRequest = createParagraphRequest(ONE, ONE, 2, 4);
        String[] words = createWords("One", "Two", "Three");
        Map.Entry<String, Integer> frequentWord = new AbstractMap.SimpleEntry<>("One", ONE);
        StreamData streamData = createStreamData(frequentWord, BigInteger.ONE, BigInteger.valueOf(words.length));
        RandomTextResponse randomTextResponse = new RandomTextResponse();
        randomTextResponse.setTextOut("<p>One Two Three.</p>\r");
        when(paragraphFeign.getRandomText(ONE, 2, 4)).thenReturn(randomTextResponse);
        when(paragraphCalculator.calculateMostFrequentWord(words)).thenReturn(frequentWord);
        //WHEN
        StreamData result = underTest.processRandomTexts(paragraphRequest);
        //THEN
        assertEquals(result, streamData);
        verify(paragraphFeign, times(ONE)).getRandomText(ONE, 2, 4);
        verify(paragraphCalculator, times(1)).calculateMostFrequentWord(words);
    }
}
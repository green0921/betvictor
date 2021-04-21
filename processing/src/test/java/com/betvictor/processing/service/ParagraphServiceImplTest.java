package com.betvictor.processing.service;

import com.betvictor.processing.model.ParagraphRequest;
import com.betvictor.processing.model.ParagraphResponse;
import com.betvictor.processing.model.StreamData;
import com.betvictor.processing.utilites.TimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.AbstractMap;

import static com.betvictor.processing.testUtilities.TestUtils.createParagraphRequest;
import static com.betvictor.processing.testUtilities.TestUtils.createStreamData;
import static com.betvictor.processing.utilites.Constants.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParagraphServiceImplTest {

    @Mock
    private ParagraphCreator paragraphCreator;
    @Mock
    private RandomTextProcessor randomTextProcessor;
    @Mock
    private TimeUtil timeUtil;

    @InjectMocks
    private ParagraphServiceImpl underTest;

    @Test
    public void testParagraphServiceImpl_whenRequestIsServed_thenReturnParagraphResponse() throws Exception {
        //GIVEN
        ParagraphRequest paragraphRequest = createParagraphRequest(ONE, ONE, 2, 4);
        long numberOfRequests = ONE;
        double currentProcessingTime = ONE;
        StreamData streamData = createStreamData(new AbstractMap.SimpleEntry<>("One", ONE), BigInteger.ONE, BigInteger.valueOf(3));
        ParagraphResponse paragraphResponse = new ParagraphResponse("Something", "1", "2", "3");
        when(randomTextProcessor.processRandomTexts(paragraphRequest)).thenReturn(streamData);
        when(timeUtil.getElapsedTimeInSeconds()).thenReturn(currentProcessingTime);
        when(paragraphCreator.createParagraphResponse(streamData, numberOfRequests, currentProcessingTime)).thenReturn(paragraphResponse);
        //WHEN
        ParagraphResponse result = underTest.serveParagraphRequest(paragraphRequest);
        //THEN
        assertEquals(result, paragraphResponse);
        verify(randomTextProcessor, times(ONE)).processRandomTexts(paragraphRequest);
        verify(timeUtil, times(ONE)).getElapsedTimeInSeconds();
        verify(paragraphCreator, times(ONE)).createParagraphResponse(streamData, numberOfRequests, currentProcessingTime);
    }
}
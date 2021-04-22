package com.betvictor.processing.service;

import com.betvictor.processing.model.ParagraphResponse;
import com.betvictor.processing.model.StreamData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.betvictor.processing.testUtilities.TestUtils.createStreamData;
import static com.betvictor.processing.utilites.Constants.ONE;
import static com.betvictor.processing.utilites.Constants.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ParagraphCreatorImplTest {


    @InjectMocks
    private ParagraphCreatorImpl underTest;

    @Test
    public void testParagraphCreatorImplTest_whenNumbersCanBeDivide_thenReturnParagraphResponse() {
        //GIVEN
        StreamData streamData = createStreamData(new AbstractMap.SimpleEntry<>("Something", ONE),
                new AtomicInteger(ONE), new AtomicInteger(10));
        ParagraphResponse paragraphResponse = new ParagraphResponse("Something", "10",
                "1.0000 s", "1.0000 s");
        long numberOfRequests = ONE;
        double totalProcessingTime = ONE;
        //WHEN
        ParagraphResponse result = underTest.createParagraphResponse(streamData, numberOfRequests, totalProcessingTime);
        //THEN
        assertEquals(result, paragraphResponse);
    }


    @Test
    public void testParagraphCreatorImplTest_whenNumbersCannotBeDivide_thenReturnParagraphResponse() {
        //GIVEN
        StreamData streamData = createStreamData(new AbstractMap.SimpleEntry<>("Something", ONE),
                new AtomicInteger(ZERO), new AtomicInteger(10));
        ParagraphResponse paragraphResponse = new ParagraphResponse("Something", "0",
                "0.0000 s", "1.0000 s");
        long numberOfRequests = ZERO;
        double totalProcessingTime = ONE;
        //WHEN
        ParagraphResponse result = underTest.createParagraphResponse(streamData, numberOfRequests, totalProcessingTime);
        //THEN
        assertEquals(result, paragraphResponse);
    }
}

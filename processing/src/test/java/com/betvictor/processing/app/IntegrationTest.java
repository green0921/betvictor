package com.betvictor.processing.app;

import com.betvictor.processing.model.ParagraphRequest;
import com.betvictor.processing.model.ParagraphResponse;
import com.betvictor.processing.service.ParagraphServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.betvictor.processing.testUtilities.TestUtils.createParagraphRequest;
import static com.betvictor.processing.utilites.Constants.ONE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private ParagraphServiceImpl underTest;

    @Test
    public void testParagraphServiceImpl_whenRequestIsServed_thenReturnParagraphResponse() {
        //GIVEN
        int wordMinCount = 2;
        int wordMaxCount = 4;
        ParagraphRequest paragraphRequest = createParagraphRequest(ONE, ONE, wordMinCount, wordMaxCount);
        //WHEN
        ParagraphResponse result = underTest.serveParagraphRequest(paragraphRequest);
        //THEN
        int averageParagraphSize = Integer.parseInt(result.getAverageParagraphSize());
        assertNotNull(result.getFrequentWord());
        assertTrue(averageParagraphSize >= wordMinCount && averageParagraphSize <= wordMaxCount);
        assertNotNull(result.getAverageParagraphProcessingTime());
        assertNotNull(result.getTotalProcessingTime());
    }
}
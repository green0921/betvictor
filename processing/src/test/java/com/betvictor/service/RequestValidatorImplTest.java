package com.betvictor.service;

import com.betvictor.exception.RequestException;
import com.betvictor.model.ParagraphRequest;
import com.betvictor.testUtilities.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RequestValidatorImplTest {

    @InjectMocks
    private RequestValidatorImpl underTest;

    @Test
    public void testRequestValidatorImpl_whenRequestIsValid_thenDoNothing() {
        //GIVEN
        ParagraphRequest paragraphRequest = TestUtils.createParagraphRequest(1, 2, 3, 4);
        //WHEN
        //THEN
        underTest.validateParagraphRequest(paragraphRequest);
    }

    @Test
    public void testRequestValidatorImpl_whenRequestStartNumberIsBiggerThanEndNumber_thenThrowException() {
        //GIVEN
        ParagraphRequest paragraphRequest = TestUtils.createParagraphRequest(3, 1, 2, 3);
        String expectedMessage = "Start number of paragraphs can't be bigger than end number of paragraphs!";
        //WHEN
        //THEN
        RequestException requestException = Assertions.assertThrows(RequestException.class, () -> {
            underTest.validateParagraphRequest(paragraphRequest);
        });
        assertEquals(requestException.getMessage(), expectedMessage);
    }

    @Test
    public void testRequestValidatorImpl_whenRequestMinNumberIsBiggerThanMaxNumber_thenThrowException() {
        //GIVEN
        ParagraphRequest paragraphRequest = TestUtils.createParagraphRequest(1, 2, 3, 1);
        String expectedMessage = "Min number of words can't be bigger than max number of paragraphs!";
        //WHEN
        //THEN
        RequestException requestException = Assertions.assertThrows(RequestException.class, () -> {
            underTest.validateParagraphRequest(paragraphRequest);
        });
        assertEquals(requestException.getMessage(), expectedMessage);
    }

    @Test
    public void testRequestValidatorImpl_whenRequestOneOfTheParameterIsNegative_thenThrowException() {
        //GIVEN
        ParagraphRequest paragraphRequest = TestUtils.createParagraphRequest(-1, 1, 2, 3);
        String expectedMessage = "Request parameters cannot be negative!";
        //WHEN
        //THEN
        RequestException requestException = Assertions.assertThrows(RequestException.class, () -> {
            underTest.validateParagraphRequest(paragraphRequest);
        });
        assertEquals(requestException.getMessage(), expectedMessage);
    }
}

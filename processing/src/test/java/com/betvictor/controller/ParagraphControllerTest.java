package com.betvictor.controller;

import com.betvictor.model.ParagraphRequest;
import com.betvictor.model.ParagraphResponse;
import com.betvictor.service.ParagraphService;
import com.betvictor.service.RequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.betvictor.testUtilities.TestUtils.createParagraphRequest;
import static com.betvictor.testUtilities.TestUtils.createParagraphResponse;
import static com.betvictor.utilites.Constants.TOPIC;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ParagraphControllerTest {

    @Mock
    private RequestValidator requestValidator;
    @Mock
    private ParagraphService paragraphService;
    @Mock
    private KafkaTemplate<String, ParagraphResponse> kafkaTemplate;

    @InjectMocks
    private ParagraphController underTest;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void testParagraphController_whenGetParagraphFromParagraphServiceWithValidRequest_thenReturnParagraphResponse() throws Exception {
        //GIVEN
        ParagraphRequest paragraphRequest = createParagraphRequest(0, 1, 2, 3);
        ParagraphResponse paragraphResponse = createParagraphResponse("Something", "1", "2", "3");
        doNothing().when(requestValidator).validateParagraphRequest(paragraphRequest);
        when(kafkaTemplate.send(TOPIC, paragraphResponse)).thenReturn(any());
        when(paragraphService.serveParagraphRequest(paragraphRequest)).thenReturn(paragraphResponse);
        //WHEN
        //THEN
        mockMvc.perform(get("/betvictor/text?p_start=0&p_end=1&w_count_min=2&w_count_max=3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.freq_word", is(paragraphResponse.getFrequentWord())))
                .andExpect(jsonPath("$.avg_paragraph_size", is(paragraphResponse.getAverageParagraphSize())))
                .andExpect(jsonPath("$.avg_paragraph_processing_time", is(paragraphResponse.getAverageParagraphProcessingTime())))
                .andExpect(jsonPath("$.total_processing_time", is(paragraphResponse.getTotalProcessingTime())));
        verify(requestValidator, times(1)).validateParagraphRequest(paragraphRequest);
        verify(paragraphService, times(1)).serveParagraphRequest(paragraphRequest);
        verify(kafkaTemplate, times(1)).send(TOPIC, paragraphResponse);
    }
}
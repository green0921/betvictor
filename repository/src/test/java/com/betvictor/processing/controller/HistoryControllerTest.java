package com.betvictor.processing.controller;

import com.betvictor.repository.controller.HistoryController;
import com.betvictor.repository.dto.History;
import com.betvictor.repository.service.HistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.betvictor.processing.testUtilities.TestUtils.createHistory;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class HistoryControllerTest {

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private HistoryController underTest;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void testHistoryController_whenGetHistoryFromHistoryService_thenReturnHistoryList() throws Exception {
        //GIVEN
        List<History> histories = new ArrayList<>();
        histories.add(createHistory());
        when(historyService.getHistory()).thenReturn(histories);
        //WHEN
        //THEN
        mockMvc.perform(get("/betvictor/history")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].freq_word", is(histories.get(0).getFrequentWord())))
                .andExpect(jsonPath("$[0].avg_paragraph_size", is(histories.get(0).getAverageParagraphSize())))
                .andExpect(jsonPath("$[0].avg_paragraph_processing_time", is(histories.get(0).getAverageParagraphProcessingTime())))
                .andExpect(jsonPath("$[0].total_processing_time", is(histories.get(0).getTotalProcessingTime())));
        verify(historyService, times(1)).getHistory();
    }


    @Test
    public void testHistoryController_whenConsumeMessageFromKafka_thenAddHistory() throws JsonProcessingException {
        //GIVEN
        History history = createHistory();
        String message = new ObjectMapper().writeValueAsString(history);
        doNothing().when(historyService).addHistory(history);
        //WHEN
        //THEN
        underTest.consumeMessage(message);
        verify(historyService, times(1)).addHistory(history);
    }
}

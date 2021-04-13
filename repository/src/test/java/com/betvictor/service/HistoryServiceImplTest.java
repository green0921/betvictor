package com.betvictor.service;

import com.betvictor.dto.History;
import com.betvictor.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.betvictor.testUtilities.TestUtils.createHistory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceImplTest {

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private HistoryServiceImpl underTest;

    @Test
    public void testHistoryServiceImpl_whenAddHistoryToMongoDB_thenAdd() {
        //GIVEN
        History history = createHistory();
        when(historyRepository.save(history)).thenReturn(any());
        //WHEN
        //THEN
        underTest.addHistory(history);
        verify(historyRepository, times(1)).save(history);
    }

    @Test
    public void testHistoryServiceImpl_whenGetHistoryFromMongoDB_thenReturnListOfHistory() {
        //GIVEN
        History history = createHistory();
        List<History> listOfHistory = new ArrayList<>();
        when(historyRepository.findAll()).thenReturn(listOfHistory);
        //WHEN
        //THEN
        List<History> result = underTest.getHistory();
        verify(historyRepository, times(1)).findAll();
        assertEquals(result.size(), listOfHistory.size());
    }
}

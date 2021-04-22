package com.betvictor.processing.model;

import lombok.*;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static com.betvictor.processing.utilites.Constants.ZERO;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StreamData {

    private volatile Map.Entry<String, Integer> frequentWord = new AbstractMap.SimpleEntry<>("", ZERO);
    private volatile AtomicInteger paragraphSizeOfSum = new AtomicInteger(ZERO);
    private volatile AtomicInteger wordsNumberOfSum = new AtomicInteger(ZERO);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreamData that = (StreamData) o;
        return Objects.equals(frequentWord, that.frequentWord)
                && Objects.equals(paragraphSizeOfSum.get(), that.paragraphSizeOfSum.get())
                && Objects.equals(wordsNumberOfSum.get(), that.wordsNumberOfSum.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequentWord, paragraphSizeOfSum, wordsNumberOfSum);
    }
}

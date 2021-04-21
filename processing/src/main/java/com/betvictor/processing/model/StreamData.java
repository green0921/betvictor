package com.betvictor.processing.model;

import lombok.*;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.Map;

import static com.betvictor.processing.utilites.Constants.ZERO;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StreamData {

    private volatile Map.Entry<String, Integer> frequentWord = new AbstractMap.SimpleEntry<>("", ZERO);
    private volatile BigInteger paragraphSizeOfSum = BigInteger.ZERO;
    private volatile BigInteger wordsNumberOfSum = BigInteger.ZERO;
}

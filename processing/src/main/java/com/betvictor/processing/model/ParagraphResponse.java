package com.betvictor.processing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ParagraphResponse {

    @JsonProperty(value = "freq_word")
    private final String frequentWord;
    @JsonProperty(value = "avg_paragraph_size")
    private final String averageParagraphSize;
    @JsonProperty(value = "avg_paragraph_processing_time")
    private final String averageParagraphProcessingTime;
    @JsonProperty(value = "total_processing_time")
    private final String totalProcessingTime;
}

package com.betvictor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document
@EqualsAndHashCode
public class History {

    @Transient
    @Id
    private String id;
    @JsonProperty(value = "freq_word")
    private String frequentWord;
    @JsonProperty(value = "avg_paragraph_size")
    private String averageParagraphSize;
    @JsonProperty(value = "avg_paragraph_processing_time")
    private String averageParagraphProcessingTime;
    @JsonProperty(value = "total_processing_time")
    private String totalProcessingTime;
}

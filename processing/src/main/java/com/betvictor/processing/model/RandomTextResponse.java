package com.betvictor.processing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RandomTextResponse {

    private String type;
    private String amount;
    private String number;
    private String number_max;
    private String format;
    private String time;
    @JsonProperty(value = "text_out")
    private String textOut;
}

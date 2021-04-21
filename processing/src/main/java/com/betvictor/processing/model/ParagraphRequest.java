package com.betvictor.processing.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ParagraphRequest {

    int p_start;
    int p_end;
    int w_count_min;
    int w_count_max;
}

package com.springboot.mycgv.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieDto {

    private String name;
    private int ageLimit;

    @Builder
    public MovieDto(String name, int ageLimit) {
        this.name = name;
        this.ageLimit = ageLimit;
    }
}

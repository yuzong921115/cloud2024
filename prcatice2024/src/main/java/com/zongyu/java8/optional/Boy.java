package com.zongyu.java8.optional;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Boy {
    private Girl girl;

    @Override
    public String toString() {
        return "Boy{" +
                "girl='" + girl + "'}";
    }
}

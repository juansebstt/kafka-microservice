package com.eligibilitymicroservice.common;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameEligibleEvent {

    private Integer id;
    private String name;
    private String userId;
    private boolean isEligible;

}

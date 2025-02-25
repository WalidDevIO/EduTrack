package fr.elite.authapi.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeResponse {

    private Boolean ok;
    private Long studentNumber;

}
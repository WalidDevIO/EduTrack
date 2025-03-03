package fr.elite.authapi.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicError {
    
    public String message;

}

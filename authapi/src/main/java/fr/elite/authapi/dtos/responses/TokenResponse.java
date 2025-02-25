package fr.elite.authapi.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse extends BasicResponse {
    
    private String token;

    public TokenResponse(String message, String token) {
        super(message);
        this.token = token;
    }

}
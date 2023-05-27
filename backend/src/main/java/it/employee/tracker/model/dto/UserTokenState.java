package it.employee.tracker.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenState {

    private String accessToken;
    private int accessExpiresIn;

    private String refreshToken;
    private int refreshExpiresIn;

}

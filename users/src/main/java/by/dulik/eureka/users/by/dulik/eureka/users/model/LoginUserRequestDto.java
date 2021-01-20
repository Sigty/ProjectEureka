package by.dulik.eureka.users.by.dulik.eureka.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequestDto {

    private String email;
    private String password;
}

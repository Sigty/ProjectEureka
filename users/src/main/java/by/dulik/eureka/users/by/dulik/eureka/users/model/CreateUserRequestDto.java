package by.dulik.eureka.users.by.dulik.eureka.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {

    @NotNull(message = "First name cannot be null")
    @Size(min=2, message = "First name must not be less than two characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min=2, message = "Last name must not be less than two characters")
    private String lastName;

    @NotNull(message = "First name cannot be null")
    @Size(min=8, message = "Password must not be less than 8 characters and more 16 characters")
    private String password;

    @NotNull(message = "Email not be null")
    @Email
    private String email;
}

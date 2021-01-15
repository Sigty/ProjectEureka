package by.dulik.eureka.users.by.dulik.eureka.users.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
public class CreateUserRequestModel {

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

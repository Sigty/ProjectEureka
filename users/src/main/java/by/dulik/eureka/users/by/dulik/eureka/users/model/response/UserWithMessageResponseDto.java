package by.dulik.eureka.users.by.dulik.eureka.users.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWithMessageResponseDto {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<MessageResponseDto> messages;
}

package models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserData {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}

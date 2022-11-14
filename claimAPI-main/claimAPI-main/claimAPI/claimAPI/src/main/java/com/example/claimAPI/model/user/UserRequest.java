package com.example.claimAPI.model.user;
import com.example.claimAPI.util.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private long userID;
    //private String userName;
    private String username;
    private String email;
    //private String userPassword;
    private String password;
    private Role role;
}

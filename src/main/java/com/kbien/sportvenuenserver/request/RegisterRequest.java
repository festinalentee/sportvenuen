package com.kbien.sportvenuenserver.request;

import com.kbien.sportvenuenserver.entity.User;
import com.kbien.sportvenuenserver.entity.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegisterRequest {
    private User user;
    private UserDetails userDetails;
}

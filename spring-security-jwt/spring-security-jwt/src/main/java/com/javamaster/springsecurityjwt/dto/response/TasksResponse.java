package com.javamaster.springsecurityjwt.dto.response;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TasksResponse {

    private Integer id;

    private Integer user_id;

    private String username;
    
    private String name;
   
    private LocalDateTime date;
    
}

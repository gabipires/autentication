package com.javamaster.springsecurityjwt.dto.request;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TasksCreateRequest {
 
    private Integer user_id;
    
    private String name;
   
    private LocalDateTime date;
    
}

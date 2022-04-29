package com.example.FlightsManagementSystem.Info.DTO;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(value="details")
public class InfoDTO {
    @Id
    private int id;
//    private String className;
    private String time;



}

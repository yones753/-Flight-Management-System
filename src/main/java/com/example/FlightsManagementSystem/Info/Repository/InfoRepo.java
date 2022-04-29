package com.example.FlightsManagementSystem.Info.Repository;

import com.example.FlightsManagementSystem.Info.DTO.InfoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfoRepo extends MongoRepository<InfoDTO,Integer> {

}

package com.example.FlightsManagementSystem.Info.Service;


import com.example.FlightsManagementSystem.Info.DTO.InfoDTO;
import com.example.FlightsManagementSystem.Info.Repository.InfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    @Autowired
    private InfoRepo infoRepo;

    public List<InfoDTO> getAll(){
        return infoRepo.findAll();
    }

    public InfoDTO addInfo(InfoDTO info){
        return infoRepo.save(info);
    }
}

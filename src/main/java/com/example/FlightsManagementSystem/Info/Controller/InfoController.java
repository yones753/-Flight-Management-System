package com.example.FlightsManagementSystem.Info.Controller;


import com.example.FlightsManagementSystem.Info.DTO.InfoDTO;
import com.example.FlightsManagementSystem.Info.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("/")
    public List<InfoDTO> getAll() {
        return infoService.getAll();
    }

    @PostMapping("/")
    public void add(@RequestBody InfoDTO info) {
        infoService.addInfo(info);
    }
}

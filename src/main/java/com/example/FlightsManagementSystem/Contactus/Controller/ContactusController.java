package com.example.FlightsManagementSystem.Contactus.Controller;


import com.example.FlightsManagementSystem.Contactus.DTO.ContactusDTO;
import com.example.FlightsManagementSystem.Contactus.Service.ContactusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactus")
public class ContactusController {

    @Autowired
    ContactusService contactusService;
    @GetMapping("/")
    public List<ContactusDTO> getAll() {
        return contactusService.getAllContactus();
    }

    @PostMapping("/")
    public void add(@RequestBody ContactusDTO contactus) {
        contactusService.addContactus(contactus);
    }

}

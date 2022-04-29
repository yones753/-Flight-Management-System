package com.example.FlightsManagementSystem.Contactus.Service;

import com.example.FlightsManagementSystem.Contactus.DTO.ContactusDTO;
import com.example.FlightsManagementSystem.Contactus.Repository.ContactusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactusService{

    @Autowired
    ContactusRepo contactusRepo;

    public List<ContactusDTO> getAllContactus() {
        List<ContactusDTO> contactus = new ArrayList<>();
        contactusRepo.findAll().forEach(contactus::add);
        return contactus;
    }

    public void addContactus(ContactusDTO contactus){
        contactusRepo.save(contactus);
    }


}

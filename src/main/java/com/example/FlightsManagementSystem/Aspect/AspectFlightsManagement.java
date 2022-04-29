package com.example.FlightsManagementSystem.Aspect;



import com.example.FlightsManagementSystem.Info.Controller.InfoController;
import com.example.FlightsManagementSystem.Info.DTO.InfoDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Aspect
public class AspectFlightsManagement {

    @Pointcut("execution(* com.example.FlightsManagementSystem.Controllers.*.add*(..))")
    public void allAddMethod() {
    }

    @Autowired
    InfoController infoController;

    //adding info in mongodbDB after all add methods in controllers
    @After("allAddMethod()")
    public void myAdviceMethod() throws Throwable {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currTime = myDateObj.format(myFormatObj);
        int num = infoController.getAll().size();
        infoController.add(new InfoDTO(++num, currTime));
    }
}

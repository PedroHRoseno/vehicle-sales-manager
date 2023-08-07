package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/index")
public class VehicleWebController{

        @Autowired
        private VehicleService vehicleService;

        @GetMapping("/test1")
        @ResponseBody
        public String hello(){
            return "Hello World!";
        }

        @GetMapping
        public ModelAndView showVehicles() {
            List<Vehicle> vehicleList = vehicleService.getAllVehicles();
            ModelAndView mv = new ModelAndView("index.html");
            mv.addObject("vehicles", vehicleList);
            return mv;
        }

}

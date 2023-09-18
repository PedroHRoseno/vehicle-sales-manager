package com.pedrohroseno.vehiclessalesmanager;

import com.pedrohroseno.vehiclessalesmanager.model.*;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;

import java.time.Instant;
import java.util.Date;

public class Utils {

    public static Sale buildSale(){

        Sale sale = new Sale();
        sale.setVehicle(buildVehicle());
        sale.setCustomer(buildCustomer());
        sale.setSalePrice(10000);
        return sale;
    }

    public static Vehicle buildVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC1234");
        vehicle.setBrand(VehicleBrand.FERRARI);
        vehicle.setModelName("F40");
        vehicle.setColor("BLUE");
        vehicle.setModelYear(1989);
        vehicle.setManufactureYear(1989);
        vehicle.setKilometersDriven(10000);
        vehicle.setInStock(true);
        return vehicle;
    }

    private static Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setCpf("10999538470");
        customer.setName("Pedro Roseno");
        customer.setAddress(new Address());
        customer.getAddress().setId(1L);
        customer.getAddress().setZipCode("55012010");
        return customer;
    }

    public static Purchase buildPurchase(){
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(Date.from(Instant.now()));
        purchase.setId(1L);
        return purchase;
    }
}

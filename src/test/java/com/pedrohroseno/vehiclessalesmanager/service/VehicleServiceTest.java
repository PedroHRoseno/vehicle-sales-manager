package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import com.pedrohroseno.vehiclessalesmanager.repository.VehicleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    public VehicleServiceTest() {
    }

    @Test
    public void testGetAllVehicles() {
        // Criar alguns veículos para simular o retorno do repositório
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicles1 = this.buildVehicle();
        vehicles1.setLicensePlate("ABC1234");
        vehicles1.setModelName("Corolla");
        vehicles1.setBrand(VehicleBrand.TOYOTA);
        vehicles.add(vehicles1);
        Vehicle vehicles2 = this.buildVehicle();
        vehicles2.setLicensePlate("DEF5678");
        vehicles2.setModelName("Civic");
        vehicles2.setBrand(VehicleBrand.HONDA);
        vehicles.add(vehicles2);

        // Definir o comportamento esperado do repositório
        Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);

        // Chamar o método do serviço
        List<Vehicle> result = vehicleService.getAllVehicles();

        // Verificar se o resultado está correto
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("ABC1234", result.get(0).getLicensePlate());
        Assert.assertEquals(VehicleBrand.TOYOTA, result.get(0).getBrand());
        Assert.assertEquals("Corolla", result.get(0).getModelName());
        Assert.assertEquals("DEF5678", result.get(1).getLicensePlate());
        Assert.assertEquals(VehicleBrand.HONDA, result.get(1).getBrand());
        Assert.assertEquals("Civic", result.get(1).getModelName());
    }

    @Test
    public void testGetOneVehicle() {
        // Criar alguns veículos para simular o retorno do repositório
        Vehicle vehicle = this.buildVehicle();

        // Definir o comportamento esperado do repositório
        Mockito.when(vehicleRepository.findById(vehicle.getLicensePlate())).thenReturn(Optional.of(vehicle));

        // Chamar o método do serviço
        Vehicle result = vehicleService.getVehicleByLicensePlate(vehicle.getLicensePlate());

        // Verificar se o resultado está correto
        Assert.assertEquals("ABC1234", result.getLicensePlate());
        Assert.assertEquals(VehicleBrand.FERRARI, result.getBrand());
        Assert.assertEquals("F40", result.getModelName());
    }

    @Test
    public void testVehicleIsAvailable(){
        // Criar alguns veículos para simular o retorno do repositório
        Vehicle vehicle = this.buildVehicle();

        // Definir o comportamento esperado do repositório
        Mockito.when(vehicleRepository.findById(vehicle.getLicensePlate())).thenReturn(Optional.of(vehicle));

        // Chamar o método do serviço
        boolean result = vehicleService.vehicleIsAvailable(vehicle.getLicensePlate());

        Assertions.assertTrue(result);
    }

    @Test
    public void testVehicleIsUnavailable(){
        // Criar alguns veículos para simular o retorno do repositório
        Vehicle vehicle = this.buildVehicle();

        // Definir o comportamento esperado do repositório
        Mockito.when(vehicleRepository.findById(vehicle.getLicensePlate())).thenReturn(Optional.of(null));

        // Chamar o método do serviço
        boolean result = vehicleService.vehicleIsAvailable(vehicle.getLicensePlate());

        Assertions.assertFalse(result);
    }



    public Vehicle buildVehicle(){
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

}

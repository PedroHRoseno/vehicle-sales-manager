package com.pedrohroseno.vehiclessalesmanager;

import com.pedrohroseno.vehiclessalesmanager.controller.VehicleController;
import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import com.pedrohroseno.vehiclessalesmanager.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// A anotação springbootTest fala ao spring para olhar para a classe de configuração com o @SpringBootApplication
// e usa isso para iniciar o contexto spring
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    VehicleService vehicleService;


    @Test
    public void ShouldReturnAllVehicles() throws Exception {
        Vehicle vehicle = this.buildVehicle();
        Mockito.when(vehicleService.getAllVehicles()).thenReturn(List.of(vehicle));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicles/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                    .andExpect(content().json("[{\"licensePlate\":\"ABC1234\",\"brand\":\"FERRARI\",\"modelName\":\"F40\",\"manufactureYear\":1989,\"modelYear\":1989,\"color\":\"BLUE\",\"kilometersDriven\":10000,\"inStock\":true}]"));
    }

    @Test
    public void ShouldFindOneVehicle() throws Exception {
        Vehicle vehicle = this.buildVehicle();
        Mockito.when(vehicleService.getVehicleByLicensePlate(vehicle.getLicensePlate())).thenReturn(vehicle);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicles/search?licensePlate=ABC1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"licensePlate\":\"ABC1234\",\"brand\":\"FERRARI\",\"modelName\":\"F40\",\"manufactureYear\":1989,\"modelYear\":1989,\"color\":\"BLUE\",\"kilometersDriven\":10000,\"inStock\":true}"));
    }

    @Test
    public void ShouldNotFindOneVehicle() throws Exception {
        Vehicle vehicle = this.buildVehicle();
        Mockito.when(vehicleService.getVehicleByLicensePlate("DEF5678")).thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicles/search?licensePlate=DEF5678")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void ShoulCreateOneVehicle() throws Exception {
        Vehicle vehicle = this.buildVehicle();
        Mockito.doNothing().when(vehicleService).addVehicle(vehicle);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/vehicles")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void ShoulDeleteOneVehicle() throws Exception {
        Vehicle vehicle = this.buildVehicle();
        Mockito.doNothing().when(vehicleService).deleteVehicle("DEF5678");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/vehicles/DEF5678")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
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

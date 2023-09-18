package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.Utils;
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
    public void shouldReturnAllVehicles() throws Exception {
        //Mockando o veiculo
        Vehicle vehicle = Utils.buildVehicle();
        //Mockando o retorno do método ou seja quando o método for chamado, eu já defino uma resposta padrao pra esse teste
        Mockito.when(vehicleService.getAllVehicles()).thenReturn(List.of(vehicle));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicles/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                    .andExpect(content().json("[{\"licensePlate\":\"ABC1234\",\"brand\":\"FERRARI\",\"modelName\":\"F40\",\"manufactureYear\":1989,\"modelYear\":1989,\"color\":\"BLUE\",\"kilometersDriven\":10000,\"inStock\":true}]"));
    }

    @Test
    public void shouldFindOneVehicle() throws Exception {
        Vehicle vehicle = Utils.buildVehicle();
        Mockito.when(vehicleService.getVehicleByLicensePlate(vehicle.getLicensePlate())).thenReturn(vehicle);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicles/search?licensePlate=ABC1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"licensePlate\":\"ABC1234\",\"brand\":\"FERRARI\",\"modelName\":\"F40\",\"manufactureYear\":1989,\"modelYear\":1989,\"color\":\"BLUE\",\"kilometersDriven\":10000,\"inStock\":true}"));
    }

    @Test
    public void shouldNotFindOneVehicle() throws Exception {
        Vehicle vehicle = Utils.buildVehicle();
        Mockito.when(vehicleService.getVehicleByLicensePlate("DEF5678")).thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicles/search?licensePlate=DEF5678")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shoulCreateOneVehicle() throws Exception {
        Vehicle vehicle = Utils.buildVehicle();
        Mockito.doNothing().when(vehicleService).addVehicle(vehicle);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/vehicles")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shoulDeleteOneVehicle() throws Exception {
        Vehicle vehicle = Utils.buildVehicle();
        Mockito.doNothing().when(vehicleService).deleteVehicle("DEF5678");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/vehicles/DEF5678")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}

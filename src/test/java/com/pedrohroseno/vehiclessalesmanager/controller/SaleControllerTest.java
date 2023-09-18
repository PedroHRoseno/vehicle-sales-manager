package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import com.pedrohroseno.vehiclessalesmanager.service.SaleService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static com.pedrohroseno.vehiclessalesmanager.Utils.buildSale;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaleControllerTest {

    @MockBean
    SaleService saleService;

    @LocalServerPort
    private int port;

    @Test
    public void testGetAllSales(){
        given()
            .port(port)
        .when()
            .get("/sales")
        .then()
            .statusCode(200);
    }

    @Test
    public void testGetOneSale(){
        Sale sale = buildSale();

        Mockito.when(saleService.getSaleById(sale.getId())).thenReturn(sale);

        given()
            .port(port)
            .param("id", "1")
        .when()
            .get("/sales/search")
        .then()
            .statusCode(200);
    }

    @Test
    public void testCreateOneSale(){
        Sale sale = buildSale();

        Mockito.doNothing().when(saleService).createSale(sale);

        given()
            .port(port)
            .body(sale)
            .contentType(ContentType.JSON)
        .when()
            .post("/sales")
        .then()
            .statusCode(201);

    }

    @Test
    public void testDeleteOneSale(){
        Sale sale = buildSale();

        Mockito.doNothing().when(saleService).deleteSaleById(sale.getId());

        given()
            .port(port)
            .pathParam("id", sale.getId())
        .when()
            .delete("/sales/{id}")
        .then()
            .statusCode(204);

    }

}

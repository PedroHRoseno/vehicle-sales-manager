package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.Purchase;
import com.pedrohroseno.vehiclessalesmanager.service.PurchaseService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchaseControllerTest {

    @MockBean
    PurchaseService purchaseService;

    @LocalServerPort
    private int port;

    @Test
    public void testGetAllPurchases(){

        given()
            .port(port)
        .when()
            .get("/purchases")
        .then()
            .statusCode(200);
    }

    @Test
    public void ShouldGetOnePurchase(){
        Purchase purchase = buildPurchase();

        Mockito.when(purchaseService.getPurchaseById(purchase.getId())).thenReturn(purchase);

        given()
            .port(port)
            .param("id", purchase.getId())
        .when()
            .get("/purchases/search")
        .then()
            .statusCode(200);
    }

    @Test
    public void ShouldCreatePurchase(){
        Purchase purchase = buildPurchase();

        Mockito.when(purchaseService.createPurchase(purchase)).thenReturn(purchase);

        given()
            .port(port)
            .body(purchase)
            .contentType(ContentType.JSON)
        .when()
            .post("/purchases")
        .then()
            .statusCode(201);
    }

    @Test
    public void ShouldDeletePurchase(){
        Purchase purchase = buildPurchase();

        Mockito.doNothing().when(purchaseService).deletePurchase(purchase.getId());

        given()
            .port(port)
            .pathParam("id", purchase.getId())
        .when()
            .delete("/purchases/{id}")
        .then()
            .statusCode(204);
    }

    private Purchase buildPurchase(){
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(Date.from(Instant.now()));
        purchase.setId(1L);
        return purchase;
    }
}

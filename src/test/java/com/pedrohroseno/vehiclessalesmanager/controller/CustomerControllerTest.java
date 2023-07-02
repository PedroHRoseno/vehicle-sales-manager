package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.service.CustomerService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    /// to do um banco de dados para mockar os dados do retorno do rest assured
    @MockBean
    CustomerService customerService;

    @LocalServerPort
    private int port;

    @Test
    public void testGetAllCustomers() {
        given()
            .port(port)
        .when()
            .get("/customers/")
        .then()
            .statusCode(200);
    }

    @Test
    public void ShouldGetCustomer() {
        Customer customer = buildCustomer();

        Mockito.when(customerService.getCustomerByCpf(customer.getCpf())).thenReturn(customer);

        given()
            .port(port)
            .param("cpf", customer.getCpf())
        .when()
            .get("/customers/search")
        .then()
            .statusCode(200);

    }

    @Test
    public void ShouldNotGetCustomer() {

        given()
            .port(port)
            .param("cpf", "123")
        .when()
            .get("/customers/search")
        .then()
            .statusCode(404);
    }

    @Test
    public void ShoudCreateCustomer(){
        Customer customer = buildCustomer();

        Mockito.doNothing().when(customerService).addCustomer(customer);

        given()
            .port(port)
            .body(customer)
            .contentType(ContentType.JSON)
        .when()
            .post("/customers/")
        .then()
            .statusCode(201);
    }

    @Test
    public void ShoudDeleteCustomer(){
        Customer customer = buildCustomer();

        Mockito.doNothing().when(customerService).deleteCustomerByCpf(customer.getCpf());

        given()
            .port(port)
            .pathParam("cpf", customer.getCpf())
        .when()
            .delete("/customers/{cpf}")
        .then()
            .statusCode(204);
    }



    private Customer buildCustomer(){
        Customer customer = new Customer();
        customer.setCpf("10999538470");
        customer.setName("Pedro Roseno");
        return customer;
    }


}

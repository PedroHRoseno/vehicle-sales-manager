package com.pedrohroseno.vehiclessalesmanager.clients;

import com.pedrohroseno.vehiclessalesmanager.model.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepClient {

    @Autowired
    private RestTemplate restTemplate;

    public ViaCepResponse getDataFromViaCep(String cep){
        ResponseEntity<ViaCepResponse> response = restTemplate.exchange(
                "https://viacep.com.br/ws/" + cep + "/json/", HttpMethod.GET, null, ViaCepResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new IllegalArgumentException("Error while retriving data from ViaCep");
        }
    }
}

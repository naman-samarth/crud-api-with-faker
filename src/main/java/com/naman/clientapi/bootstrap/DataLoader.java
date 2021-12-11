package com.naman.clientapi.bootstrap;

import com.github.javafaker.Faker;
import com.naman.clientapi.entities.Client;
import com.naman.clientapi.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClientService clientService;

    public DataLoader(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 10; i++) {
            Faker faker = new Faker();
            Client client = Client.builder()
                    .name(faker.name().fullName())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .build();

            clientService.saveClient(client);
        }
    }
}

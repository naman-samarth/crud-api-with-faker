package com.naman.clientapi.controllers;

import com.naman.clientapi.entities.Client;
import com.naman.clientapi.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        Client client = clientService.getClient(id);
        if (client == null) {
            throw new RuntimeException();
        }
        return client;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) throws URISyntaxException {
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity
                .created(new URI("/clients/" + savedClient.getId()))
                .body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id,
                                               @RequestBody Client client) {
        Client currentClient = clientService.getClient(id);
        if (currentClient == null) throw new RuntimeException();
        currentClient.setName(client.getName());
        currentClient.setPhoneNumber(client.getPhoneNumber());
        currentClient = clientService.saveClient(client);
        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }


}

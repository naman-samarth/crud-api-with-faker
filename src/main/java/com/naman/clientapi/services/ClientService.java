package com.naman.clientapi.services;

import com.naman.clientapi.entities.Client;

import java.util.List;

public interface ClientService {

    List<Client> getClients();
    Client getClient(Long id);
    Client saveClient(Client client);
    void deleteClient(Long id);

}

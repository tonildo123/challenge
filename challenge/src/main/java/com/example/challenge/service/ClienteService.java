package com.example.challenge.service;

import com.example.challenge.model.Clientes;
import com.example.challenge.model.Profile;
import com.example.challenge.repository.IClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private IClientesRepository iClientesRepository;

    // get all
    public List<Clientes> getAllClients(){
        return  iClientesRepository.findAll();
    }

    //get on by id
    public Clientes getClientById(Long id){
        return iClientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }
    //get on by name
    public Clientes getClientByName(String name){
        return iClientesRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Profile not found with name: " + name));
    }
    // insert
    public Clientes createClient(Clientes clientes) {
            return iClientesRepository.save(clientes);
    }
    // update

    public Clientes updateClient(Long id, Clientes clientes) {
        Clientes existingClient = iClientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        existingClient.setName(clientes.getName());
        existingClient.setLastname(clientes.getLastname());
        existingClient.setPhone(clientes.getPhone());
        existingClient.setCuit(clientes.getCuit());
        existingClient.setBrithdate(clientes.getBrithdate());
        existingClient.setDomicilio(clientes.getDomicilio());
        existingClient.setEmail(clientes.getEmail());

        return iClientesRepository.save(existingClient);
    }

    public void deleteClientById(Long id){
        iClientesRepository.deleteById(id);
    }

}

package com.example.challenge.service;

import com.example.challenge.dto.ClienteDTO;
import com.example.challenge.model.Clientes;
import com.example.challenge.repository.IClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
   public ResponseEntity<ClienteDTO> getClientDTOById(Long id) {
        Optional<Clientes> clientOptional = iClientesRepository.findById(id);

        if (clientOptional.isPresent()) {
            Clientes client = clientOptional.get();
            ClienteDTO clienteDTO = new ClienteDTO(client.getName(), client.getLastname(), client.getBrithdate(),
                    client.getCuit(), client.getEmail(),
                    client.getAddress(), client.getPhone());
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //get on by name
    public ResponseEntity<ClienteDTO> getClientDTOByName(String name) {
        Optional<Clientes> clientOptional = iClientesRepository.findByName(name);

        if (clientOptional.isPresent()) {
            Clientes client = clientOptional.get();
            ClienteDTO clienteDTO = new ClienteDTO(client.getName(), client.getLastname(), client.getBrithdate(),
                    client.getCuit(), client.getEmail(),
                    client.getAddress(), client.getPhone());
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createClientDTO(Clientes client) {
        Clientes clientCreated = iClientesRepository.save(client);
        if (clientCreated != null) {
            ClienteDTO clientDTO = new ClienteDTO(clientCreated.getName(), clientCreated.getLastname(),
                    clientCreated.getBrithdate(), clientCreated.getCuit(), clientCreated.getEmail(),
                    clientCreated.getAddress(), clientCreated.getPhone());
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            String errorMessage = "El usuario con el correo electrónico " + client.getEmail() + " ya está registrado.";
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<ClienteDTO> updateClientDTO(Long id, Clientes clientes) {
        Clientes updatedClient = updateClient(id, clientes);
        if (updatedClient != null) {
            ClienteDTO clienteDTO = new ClienteDTO(updatedClient.getName(), updatedClient.getLastname(),
                    updatedClient.getBrithdate(), updatedClient.getCuit(), updatedClient.getEmail(),
                    updatedClient.getAddress(), updatedClient.getPhone());
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Clientes updateClient(Long id, Clientes clientes) {
        Clientes existingClient = iClientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        existingClient.setName(clientes.getName());
        existingClient.setLastname(clientes.getLastname());
        existingClient.setPhone(clientes.getPhone());
        existingClient.setCuit(clientes.getCuit());
        existingClient.setBrithdate(clientes.getBrithdate());
        existingClient.setAddress(clientes.getAddress());
        existingClient.setEmail(clientes.getEmail());

        return iClientesRepository.save(existingClient);
    }
    public void deleteClientById(Long id){
        iClientesRepository.deleteById(id);
    }

}

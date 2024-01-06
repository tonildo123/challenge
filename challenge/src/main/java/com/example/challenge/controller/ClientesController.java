package com.example.challenge.controller;

import com.example.challenge.dto.ClienteDTO;
import com.example.challenge.dto.UserDTO;
import com.example.challenge.model.Clientes;
import com.example.challenge.model.Profile;
import com.example.challenge.model.User;
import com.example.challenge.service.ClienteService;
import com.example.challenge.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/clientes/")
public class ClientesController {

    // get all clients
    @Autowired
    private ClienteService clienteService;

    @GetMapping("getall")
    @ResponseBody
    public ResponseEntity<List<ClienteDTO>> getAll() {
        List<Clientes> clientes = clienteService.getAllClients();
        List<ClienteDTO> clientesDTOList = clientes.stream()
                .map(cliente -> new ClienteDTO(cliente.getName(), cliente.getLastname(), cliente.getBrithdate(), cliente.getCuit(), cliente.getEmail(),
                        cliente.getDomicilio(), cliente.getPhone()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(clientesDTOList, HttpStatus.OK);
    }

    @GetMapping("getone/{id}")
    @ResponseBody
    public ResponseEntity<ClienteDTO> getOne(@PathVariable Long id) {
        Clientes client = clienteService.getClientById(id);

        if (client != null) {
            ClienteDTO clienteDTO = new ClienteDTO(client.getName(), client.getLastname(), client.getBrithdate(),
                    client.getCuit(), client.getEmail(),
                    client.getDomicilio(), client.getPhone());
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getbyname/{name}")
    @ResponseBody
    public ResponseEntity<ClienteDTO> getOneByName(@PathVariable String name) {
        Clientes client = clienteService.getClientByName(name);

        if (client != null) {
            ClienteDTO clienteDTO = new ClienteDTO(client.getName(), client.getLastname(), client.getBrithdate(),
                    client.getCuit(), client.getEmail(),
                    client.getDomicilio(), client.getPhone());
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/inserter")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody Clientes client){
        Clientes clientCreated = clienteService.createClient(client);
        if (clientCreated != null) {
            ClienteDTO clientDTO = new ClienteDTO(clientCreated.getName(), clientCreated.getLastname(),
                    clientCreated.getBrithdate(),clientCreated.getCuit(), clientCreated.getEmail(),
                    clientCreated.getDomicilio(), clientCreated.getPhone());
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            String errorMessage = "El usuario con el correo electrónico " + client.getEmail() + " ya está registrado.";
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("update/{id}")
    @ResponseBody
    public ResponseEntity<ClienteDTO> update(@RequestBody Clientes clientes, @PathVariable Long id) {
        clientes.setId(id);
        Clientes updatedClient = clienteService.updateClient(id, clientes);
        if (updatedClient != null) {
            ClienteDTO clienteDTO = new ClienteDTO(updatedClient.getName(), updatedClient.getLastname(),
                    updatedClient.getBrithdate(),updatedClient.getCuit(), updatedClient.getEmail(),
                    updatedClient.getDomicilio(), updatedClient.getPhone());
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clienteService.deleteClientById(id);
        return new ResponseEntity<>("Cliente borrado", HttpStatus.OK);
    }

}

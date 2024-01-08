package com.example.challenge.controller;

import com.example.challenge.dto.ClienteDTO;
import com.example.challenge.model.Clientes;
import com.example.challenge.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/clients/")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientesController {

    // get all clients
    @Autowired
    private ClienteService clienteService;

    @GetMapping("getall")
    @ResponseBody
    public ResponseEntity<List<ClienteDTO>> getAll() {
        List<Clientes> clientes = clienteService.getAllClients();
        List<ClienteDTO> clientesDTOList = clientes.stream()
                .map(cliente -> new ClienteDTO(cliente.getName(), cliente.getLastname(),
                        cliente.getBrithdate(), cliente.getCuit(),
                        cliente.getAddress(), cliente.getPhone(),cliente.getEmail()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(clientesDTOList, HttpStatus.OK);
    }

    @GetMapping("getone/{id}")
    @ResponseBody
    public ResponseEntity<ClienteDTO> getOne(@PathVariable Long id) {
        return clienteService.getClientDTOById(id);
    }

    @GetMapping("getbyname/{name}")
    @ResponseBody
    public ResponseEntity<ClienteDTO> getOneByName(@PathVariable String name) {
        return clienteService.getClientDTOByName(name);
    }

    @PostMapping("/inserter")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody Clientes client) {
        return clienteService.createClientDTO(client);
    }

    @PutMapping("update/{id}")
    @ResponseBody
    public ResponseEntity<ClienteDTO> update(@RequestBody Clientes clientes, @PathVariable Long id) {
        return clienteService.updateClientDTO(id, clientes);
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clienteService.deleteClientById(id);
        return new ResponseEntity<>("Cliente borrado", HttpStatus.OK);
    }

}

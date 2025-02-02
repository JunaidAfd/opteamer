package io.medsys.opteamer.rest;

import io.medsys.opteamer.dto.OperationProviderDTO;
import io.medsys.opteamer.repositories.OperationProviderRepository;
import io.medsys.opteamer.services.OperationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operationproviders")
public class OperationProviderController {
    OperationProviderService operationProviderService;
    @Autowired
    public OperationProviderController(OperationProviderService operationProviderService){
        this.operationProviderService=operationProviderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationProviderDTO> getOperationProviderById(@PathVariable(name = "id") String id){
        Optional<OperationProviderDTO> operationProviderDTOOptional = operationProviderService.getOperationProviderById(id);
        return operationProviderDTOOptional.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<OperationProviderDTO>> getAllOperationProviders(){
        List<OperationProviderDTO> operationProviderList = operationProviderService.getAllOperationProvider();
        return ResponseEntity.status(HttpStatus.CREATED).body(operationProviderList);
    }

    @PostMapping
    public ResponseEntity<OperationProviderDTO> createOperationProvider(@RequestBody OperationProviderDTO operationProviderDTO){
        OperationProviderDTO createdOperationProviderDTO = operationProviderService.createOperationProvider(operationProviderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOperationProviderDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationProviderDTO> updateOperationProvider(@PathVariable(name = "id") String id,@RequestBody OperationProviderDTO operationProviderDTO){
        Optional<OperationProviderDTO> operationProviderDTOOptional = operationProviderService.updateOperationProvider(id, operationProviderDTO);
        return operationProviderDTOOptional.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperationProvider(@PathVariable(name = "id") String id){
        boolean deleted = operationProviderService.deleteOperationProvider(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

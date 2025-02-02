package io.medsys.opteamer.services;

import io.medsys.opteamer.dto.OperationProviderDTO;
import io.medsys.opteamer.model.OperationProvider;
import io.medsys.opteamer.model.enums.OperationProviderType;
import io.medsys.opteamer.repositories.OperationProviderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OperationProviderService {
    OperationProviderRepository operationProviderRepository;
    @Autowired
    public OperationProviderService(OperationProviderRepository operationProviderRepository){
        this.operationProviderRepository=operationProviderRepository;
    }

    public Optional<OperationProviderDTO> getOperationProviderById(String id){
        try{
            OperationProvider operationProvider = operationProviderRepository.findByType(OperationProviderType.valueOf(id)).orElseThrow();
            return Optional.of(mapEntityToDTO(operationProvider));
        }catch (NoSuchElementException e){
            return Optional.empty();
        }
    }

    public List<OperationProviderDTO> getAllOperationProvider(){
        List<OperationProviderDTO> list=new ArrayList<>();
        Iterable<OperationProvider> allOperationProviders = operationProviderRepository.findAll();
        allOperationProviders.forEach(operationProvider -> list.add(mapEntityToDTO(operationProvider)));
        return list;
    }

    public OperationProviderDTO createOperationProvider(OperationProviderDTO operationProviderDTO){
        ModelMapper modelMapper=new ModelMapper();
        OperationProvider operationProvider=modelMapper.map(operationProviderDTO,OperationProvider.class);
        operationProvider = operationProviderRepository.save(operationProvider);
        return modelMapper.map(operationProvider,OperationProviderDTO.class);
    }

    public Optional<OperationProviderDTO> updateOperationProvider(String id,OperationProviderDTO operationProviderDTO){
        return operationProviderRepository.findByType(OperationProviderType.valueOf(id)).map(operationProvider -> {
            operationProvider.setType(operationProviderDTO.getType());
            operationProviderRepository.save(operationProvider);
            return mapEntityToDTO(operationProvider);
        });
    }

    public boolean deleteOperationProvider(String id){
        return operationProviderRepository.findByType(OperationProviderType.valueOf(id)).map(operationProvider -> {
            operationProviderRepository.delete(operationProvider);
            return true;
        }).orElse(false);
    }

    private OperationProvider mapDtoToEntity(OperationProviderDTO operationProviderDTO){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(operationProviderDTO,OperationProvider.class);
    }

    private OperationProviderDTO mapEntityToDTO(OperationProvider operationProvider){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(operationProvider,OperationProviderDTO.class);
    }
}

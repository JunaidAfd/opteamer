package io.medsys.opteamer.services;

import io.medsys.opteamer.dto.OperationRoomDTO;
import io.medsys.opteamer.model.OperationRoom;
import io.medsys.opteamer.repositories.OperationRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OperationRoomService {
    OperationRoomRepository operationRoomRepository;
    @Autowired
    public OperationRoomService(OperationRoomRepository operationRoomRepository){
        this.operationRoomRepository=operationRoomRepository;
    }

    public Optional<OperationRoomDTO> getOperationRoomById(Long id){
        try{
            OperationRoom operationRoom = operationRoomRepository.findById(id).orElseThrow();
            return Optional.of(mapEntityToDTO(operationRoom));
        }catch (NoSuchElementException e){
            return Optional.empty();
        }
    }

    public List<OperationRoomDTO> getAllOperationRoom(){
        List<OperationRoomDTO> list=new ArrayList<>();
        Iterable<OperationRoom> allOperationRooms = operationRoomRepository.findAll();
        allOperationRooms.forEach(operationRoom -> list.add(mapEntityToDTO(operationRoom)));
        return list;
    }

    public OperationRoomDTO createOperationRoom(OperationRoomDTO operationRoomDTO){
        ModelMapper modelMapper=new ModelMapper();
        OperationRoom operationRoom=modelMapper.map(operationRoomDTO,OperationRoom.class);
        operationRoom = operationRoomRepository.save(operationRoom);
        return modelMapper.map(operationRoom,OperationRoomDTO.class);
    }

    public Optional<OperationRoomDTO> updateOperationRoom(Long id,OperationRoomDTO operationRoomDTO){
        return operationRoomRepository.findById(id).map(operationRoom -> {
            operationRoom.setRoomNr(operationRoomDTO.getRoomNr());
            operationRoom.setType(operationRoomDTO.getType());
            operationRoom.setState(operationRoomDTO.getState());
            operationRoom.setFloor(operationRoomDTO.getFloor());
            operationRoom.setBuildingBlock(operationRoomDTO.getBuildingBlock());
            return mapEntityToDTO(operationRoom);
        });
    }

    public boolean deleteOperationRoom(Long id){
        return operationRoomRepository.findById(id).map(operationRoom -> {
            operationRoomRepository.delete(operationRoom);
            return true;
        }).orElse(false);
    }

    private OperationRoom mapDtoToEntity(OperationRoomDTO operationRoomDTO){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(operationRoomDTO,OperationRoom.class);
    }

    private OperationRoomDTO mapEntityToDTO(OperationRoom operationRoom){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(operationRoom, OperationRoomDTO.class);
    }
}

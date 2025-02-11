package io.medsys.opteamer.services;

import io.medsys.opteamer.dto.AssetDTO;
import io.medsys.opteamer.dto.OperationRoomDTO;
import io.medsys.opteamer.dto.RoomInventoryDTO;
import io.medsys.opteamer.model.Asset;
import io.medsys.opteamer.model.Inventory;
import io.medsys.opteamer.model.OperationRoom;
import io.medsys.opteamer.model.RoomInventory;
import io.medsys.opteamer.model.embeddedids.RoomInventoryId;
import io.medsys.opteamer.repositories.AssetRepoistory;
import io.medsys.opteamer.repositories.OperationRoomRepository;
import io.medsys.opteamer.repositories.RoomInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoomInventoryService {
    RoomInventoryRepository roomInventoryRepository;
    AssetRepoistory assetRepoistory;
    OperationRoomRepository operationRoomRepository;
    @Autowired
    public RoomInventoryService(RoomInventoryRepository roomInventoryRepository,
                                AssetRepoistory assetRepoistory,
                                OperationRoomRepository operationRoomRepository){
        this.roomInventoryRepository=roomInventoryRepository;
        this.assetRepoistory=assetRepoistory;
        this.operationRoomRepository=operationRoomRepository;
    }

    public Optional<RoomInventoryDTO> getRoomInventoryById(Long assedId, Long roomId){
        try{
            RoomInventoryId roomInventoryId=new RoomInventoryId(assedId,roomId);
            RoomInventory roomInventory=roomInventoryRepository.findById(roomInventoryId).orElseThrow();
            return Optional.of(getRoomInventoryDTO(
                                        roomInventory,
                                        roomInventory.getAsset(),
                                        roomInventory.getOperationRoom()));
        }catch (NoSuchElementException e){
            return Optional.empty();
        }
    }

    public List<RoomInventoryDTO> getAllRoomInventories(){
        List<RoomInventoryDTO> list=new ArrayList<>();
        Iterable<RoomInventory> allRoomInventorys=roomInventoryRepository.findAll();
        allRoomInventorys.forEach(roomInventory->list.add(getRoomInventoryDTO(
                roomInventory,
                roomInventory.getAsset(),
                roomInventory.getOperationRoom())));
        return list;
    }

    public RoomInventoryDTO createRoomInventory(RoomInventoryDTO roomInventoryDTO){
        RoomInventory roomInventory=new RoomInventory();
        Asset asset=assetRepoistory.findById(roomInventoryDTO.getAssetId()).get();
        OperationRoom operationRoom = operationRoomRepository.findById(roomInventoryDTO.getOperationRoomId()).get();
        if(asset==null || operationRoom==null)
            throw new NoSuchElementException();

        roomInventory.setRoomInventoryId(new RoomInventoryId(asset.getId(), operationRoom.getId()));
        roomInventory.setAsset(asset);
        roomInventory.setOperationRoom(operationRoom);
        roomInventory.setCount(roomInventoryDTO.getCount());
        roomInventory=roomInventoryRepository.save(roomInventory);
        return getRoomInventoryDTO(roomInventory, roomInventory.getAsset(),roomInventory.getOperationRoom());
    }

    private static RoomInventoryDTO getRoomInventoryDTO(RoomInventory roomInventory, Asset asset, OperationRoom operationRoom) {
        return new RoomInventoryDTO(roomInventory.getAsset().getId(),
                roomInventory.getOperationRoom().getId(),
                new AssetDTO(asset.getId(), asset.getType(), asset.getName()),
                new OperationRoomDTO(
                        operationRoom.getId(),
                        operationRoom.getRoomNr(),
                        operationRoom.getBuildingBlock(),
                        operationRoom.getFloor(),
                        operationRoom.getType(),
                        operationRoom.getState()
                ),
                roomInventory.getCount());
    }

    public Optional<RoomInventoryDTO> updateRoomInventory(Long assetId,Long roomId,RoomInventoryDTO roomInventoryDTO){
        RoomInventoryId roomInventoryId=new RoomInventoryId(assetId,roomId);
        return  roomInventoryRepository.findById(roomInventoryId).map(roomInventory -> {
            roomInventory.setCount(roomInventoryDTO.getCount());
            roomInventoryRepository.save(roomInventory);
            return getRoomInventoryDTO(roomInventory,roomInventory.getAsset(),roomInventory.getOperationRoom());
        });
    }

    public boolean deleteRoomInventory(Long assetId,Long roomId){
        RoomInventoryId roomInventoryId=new RoomInventoryId(assetId,roomId);
        return roomInventoryRepository.findById(roomInventoryId).map(roomInventory -> {
            roomInventoryRepository.delete(roomInventory);
            return true;
        }).orElse(false);
    }
}

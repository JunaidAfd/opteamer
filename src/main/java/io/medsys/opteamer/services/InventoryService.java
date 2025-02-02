package io.medsys.opteamer.services;

import io.medsys.opteamer.dto.AssetDTO;
import io.medsys.opteamer.dto.InventoryDTO;
import io.medsys.opteamer.model.Asset;
import io.medsys.opteamer.model.Inventory;
import io.medsys.opteamer.repositories.AssetRepoistory;
import io.medsys.opteamer.repositories.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InventoryService {

    InventoryRepository inventoryRepository;
    AssetRepoistory assetRepoistory;
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository,AssetRepoistory assetRepoistory){
        this.inventoryRepository=inventoryRepository;
        this.assetRepoistory=assetRepoistory;
    }

    public Optional<InventoryDTO> getInventoryById(Long id){
        try{
            Inventory inventory=inventoryRepository.findById(id).orElseThrow();
            return Optional.of(getInventoryDTO(inventory, inventory.getAsset()));
        }catch (NoSuchElementException e){
            return Optional.empty();
        }
    }

    public List<InventoryDTO> getAllInventories(){
        List<InventoryDTO> list=new ArrayList<>();
        Iterable<Inventory> allInventorys=inventoryRepository.findAll();
        allInventorys.forEach(inventory->list.add(getInventoryDTO(inventory, inventory.getAsset())));
        return list;
    }

    public InventoryDTO createInventory(InventoryDTO inventoryDTO){
        Inventory inventory=new Inventory();
        Asset asset=assetRepoistory.findById(inventoryDTO.getAssetID()).get();
        if(asset==null)
            throw new NoSuchElementException();
        inventory.setAsset(asset);
        inventory.setCount(inventoryDTO.getCount());
        inventory=inventoryRepository.save(inventory);
        return getInventoryDTO(inventory, asset);
    }

    private static InventoryDTO getInventoryDTO(Inventory inventory, Asset asset) {
        return new InventoryDTO(inventory.getAssetId(), inventory.getCount(), new AssetDTO(asset.getId(), asset.getType(), asset.getName()));
    }

    public Optional<InventoryDTO> updateInventory(Long id,InventoryDTO inventoryDTO){
        return inventoryRepository.findById(id).map(inventory -> {
            inventory.setCount(inventoryDTO.getCount());
            inventoryRepository.save(inventory);
            return getInventoryDTO(inventory,inventory.getAsset());
        });
    }

    public boolean deleteInventory(Long id){
        return inventoryRepository.findById(id).map(inventory -> {
            inventoryRepository.delete(inventory);
            return true;
        }).orElse(false);
    }

}

package io.medsys.opteamer.services;

import io.medsys.opteamer.dto.AssetDTO;
import io.medsys.opteamer.model.Asset;
import io.medsys.opteamer.repositories.AssetRepoistory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AssetService {//Service Implements the Business logic of an application.
    AssetRepoistory assetRepoistory;

    @Autowired
    public AssetService(AssetRepoistory assetRepoistory){
        this.assetRepoistory=assetRepoistory;
    }

    public AssetDTO createAsset(AssetDTO assetDTO){
        ModelMapper modelMapper=new ModelMapper();
        Asset asset=modelMapper.map(assetDTO,Asset.class);
        asset=assetRepoistory.save(asset);
        return modelMapper.map(asset,AssetDTO.class);
    }

    public List<AssetDTO> getAllAssets() {
        List<AssetDTO> list=new ArrayList<>();
        Iterable<Asset> allAssets = assetRepoistory.findAll();
        allAssets.forEach(asset -> list.add(mapEntityToDTO(asset)));
        return list;
    }

    public Optional<AssetDTO> getAssetById(Long id){
        try{
            Asset asset = assetRepoistory.findById(id).orElseThrow();
            return Optional.of(mapEntityToDTO(asset));
        }catch (NoSuchElementException e){
            return Optional.empty();
        }
    }

    public boolean deleteAsset(Long id){
        return assetRepoistory.findById(id).map(asset -> {
            assetRepoistory.delete(asset);
            return true;
        }).orElse(false);
    }

    public Optional<AssetDTO> updateAsset(Long id, AssetDTO assetDTO){
        return assetRepoistory.findById(id).map(asset -> {
            asset.setType(assetDTO.getType());
            asset.setName(assetDTO.getName());
            assetRepoistory.save(asset);
            return mapEntityToDTO(asset);
        });
    }

    private Asset mapDTOToEntity(AssetDTO assetDTO){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(assetDTO,Asset.class);
    }

    private AssetDTO mapEntityToDTO(Asset asset){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(asset,AssetDTO.class);
    }
}

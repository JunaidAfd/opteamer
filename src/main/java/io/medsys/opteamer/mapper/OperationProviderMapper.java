package io.medsys.opteamer.mapper;

import io.medsys.opteamer.dto.OperationProviderDTO;
import io.medsys.opteamer.model.OperationProvider;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationProviderMapper {
    OperationProviderMapper INSTANCE= Mappers.getMapper(OperationProviderMapper.class);
    OperationProviderDTO toOperationProviderDTO(OperationProvider operationProvider);
    OperationProvider toOperationProvider(OperationProviderDTO operationProviderDTO);
}

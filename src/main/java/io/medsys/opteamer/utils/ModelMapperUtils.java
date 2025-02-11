package io.medsys.opteamer.utils;

import io.medsys.opteamer.dto.OperationProviderDTO;
import io.medsys.opteamer.dto.TeamMemberDTO;
import io.medsys.opteamer.model.OperationProvider;
import io.medsys.opteamer.model.TeamMember;
import org.modelmapper.ModelMapper;

public class ModelMapperUtils {


    public static OperationProvider mapOperationProviderDTOTOEntity(OperationProviderDTO operationProviderDTO){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(operationProviderDTO,OperationProvider.class);
    }

    public static OperationProviderDTO mapOperationProviderEntityToDTO(OperationProvider operationProvider) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(operationProvider,OperationProviderDTO.class);
    }
}

package io.medsys.opteamer.dto;

import io.medsys.opteamer.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomInventoryDTO {
    private Long assetId;
    private Long operationRoomId;
    private AssetDTO assetDTO;
    private OperationRoomDTO operationRoomDTO;
    private Integer count;
}

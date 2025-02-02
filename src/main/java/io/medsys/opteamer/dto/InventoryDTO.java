package io.medsys.opteamer.dto;

import io.medsys.opteamer.model.enums.AssetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryDTO {
    private Long assetID;
    private Integer count;
    private AssetDTO assetDTO;
}

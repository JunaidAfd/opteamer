package io.medsys.opteamer.model.embeddedids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

//The RoomInventoryId class is marked with @Embeddable, indicating that it will be embedded in another entity as a composite key.
//It implements Serializable, which is required for composite keys in JPA.
//The class contains two fields, assetId and roomId, which together form the composite primary key for the RoomInventory entity.

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomInventoryId implements Serializable {
    @Column(name = "asset_id")
    private Long assetId;
    @Column(name = "room_id")
    private Long roomId;
}

package io.medsys.opteamer.repositories;

import io.medsys.opteamer.model.Operation;
import io.medsys.opteamer.model.RoomInventory;
import io.medsys.opteamer.model.embeddedids.RoomInventoryId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomInventoryRepository extends CrudRepository<RoomInventory, RoomInventoryId> {
    Optional<RoomInventory> findById(RoomInventoryId roomInventoryId);
}

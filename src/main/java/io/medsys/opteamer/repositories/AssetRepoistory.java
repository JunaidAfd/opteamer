package io.medsys.opteamer.repositories;

import io.medsys.opteamer.model.Asset;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AssetRepoistory extends CrudRepository<Asset,Long> {  //JPA data access components are the Repositories.
    Optional<Asset> findById(Long id);
}

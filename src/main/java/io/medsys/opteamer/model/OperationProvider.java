package io.medsys.opteamer.model;

import io.medsys.opteamer.model.enums.OperationProviderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OPERATION_PROVIDER")
@Getter
@Setter
@NoArgsConstructor
public class OperationProvider {
    @Id
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private OperationProviderType type;
}

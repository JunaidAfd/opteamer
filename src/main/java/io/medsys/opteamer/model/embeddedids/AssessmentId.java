package io.medsys.opteamer.model.embeddedids;

import jakarta.persistence.Column;

import java.io.Serializable;

public class AssessmentId implements Serializable {
    @Column(name = "team_member_id")
    private Long teamMemberId;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "pre_op_a_id")
    private Long preOpAId;
}

package io.medsys.opteamer.rest;

import io.medsys.opteamer.dto.TeamMemberDTO;
import io.medsys.opteamer.services.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teammembers")
public class TeamMemberController {
    TeamMemberService teamMemberService;
    @Autowired
    public TeamMemberController(TeamMemberService teamMemberService){
        this.teamMemberService=teamMemberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamMemberDTO> getTeamMemberById(@PathVariable(name="id") Long id){
        Optional<TeamMemberDTO> teamMemberDTOOptional = teamMemberService.getTeamMemberById(id);
        return teamMemberDTOOptional.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<TeamMemberDTO>> getAllTeamMembers(){
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMemberService.getAllTeamMembers());
    }

    @PostMapping
    public ResponseEntity<TeamMemberDTO> createTeamMember(@RequestBody TeamMemberDTO teamMemberDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMemberService.createTeamMember(teamMemberDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamMemberDTO> updateTeamMember(@PathVariable(name = "id") Long id,@RequestBody TeamMemberDTO teamMemberDTO){
        Optional<TeamMemberDTO> teamMemberDTOOptional = teamMemberService.updateTeamMember(id, teamMemberDTO);
        return teamMemberDTOOptional.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable(name = "id") Long id){
        boolean deleted = teamMemberService.deleteTeamMember(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.TreatmentDTO;
import lk.ijse.mental_hospital.entity.Treatment;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TreatmentService {
    void addTreatment(TreatmentDTO treatmentDTO);

    List<TreatmentDTO> findAllTreatment();

    void updateTreatment(TreatmentDTO treatmentDTO);

    List<TreatmentDTO> filterTreatments(Long id);

}

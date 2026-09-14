package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.DiagnosisDTO;
import lk.ijse.mental_hospital.entity.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    void saveDiagnosis(DiagnosisDTO diagnosisDTO);

    List<DiagnosisDTO> getGetAllDiagnosis();

    void updateDiagnosis(DiagnosisDTO diagnosisDTO);

    List<DiagnosisDTO> filterDiagnosis(Long id);



    //delete ek kre ne

//    void deleteDiagnosis(Long id);

}

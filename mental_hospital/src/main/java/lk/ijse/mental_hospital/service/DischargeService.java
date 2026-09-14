package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.DischargeDTO;
import lk.ijse.mental_hospital.dto.DoctorDTO;

import java.util.List;

public interface DischargeService {

    void saveDischarge(DischargeDTO dischargeDTO);

    List<DischargeDTO> getAllDischarges();

    void updateDischarge(DischargeDTO dischargeDTO);

    List<DischargeDTO> filterDischarge(Long id);

    void changeDischarge(long id);
}

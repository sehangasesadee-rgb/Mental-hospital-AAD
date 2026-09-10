package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.AppoinmentDTO;

import java.util.List;

public interface AppoinmentService {

    void saveAppoinment(AppoinmentDTO appoinmentDTO);

    List<AppoinmentDTO> getAppoinments();

    void updateAppoinment(AppoinmentDTO appoinmentDTO);

    List<AppoinmentDTO> filterAppoinments(Long appoinment_id, Long patient_id);

    void changeAppoinmentStatus(long id);


}

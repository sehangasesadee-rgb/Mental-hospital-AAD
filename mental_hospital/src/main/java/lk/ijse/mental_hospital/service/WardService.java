package lk.ijse.mental_hospital.service;

import lk.ijse.mental_hospital.dto.WardDTO;

import java.util.List;

public interface WardService {
    void saveWard(WardDTO wardDTO);

    List<WardDTO> getAllWards();

    void updateWard(WardDTO wardDTO);

    void changeWardStatus(long id);

    List<WardDTO> filterWard(String name);
}

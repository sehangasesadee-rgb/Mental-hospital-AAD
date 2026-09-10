package lk.ijse.mental_hospital.service;


import lk.ijse.mental_hospital.dto.BedDTO;
import lk.ijse.mental_hospital.entity.Bed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BedService {

    void saveBed(BedDTO bedDTO);

    List<BedDTO> findAll();

    void updateBed(BedDTO bedDTO);

    void deleteBed(long id);

    List<BedDTO> filterBed(String bedNumber);
}

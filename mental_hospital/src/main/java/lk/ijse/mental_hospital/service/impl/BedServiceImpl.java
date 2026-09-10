package lk.ijse.mental_hospital.service.impl;

import lk.ijse.mental_hospital.dto.BedDTO;
import lk.ijse.mental_hospital.entity.Bed;
import lk.ijse.mental_hospital.entity.Ward;
import lk.ijse.mental_hospital.enumaration.BedStatus;
import lk.ijse.mental_hospital.repository.BedRepository;
import lk.ijse.mental_hospital.repository.WardRepository;
import lk.ijse.mental_hospital.service.BedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class BedServiceImpl implements BedService {
    private final BedRepository bedRepository;
    private final WardRepository wardRepository;
    public BedServiceImpl(BedRepository bedRepository, WardRepository wardRepository) {
        this.bedRepository = bedRepository;
        this.wardRepository = wardRepository;
    }

    @Override
    public void saveBed(BedDTO bedDTO) {
        log.info("Save Bed DTO");

        Optional<Ward> ward = wardRepository.findById(bedDTO.getWardId());

        if(ward.isEmpty()){
            throw new RuntimeException("Ward Not Found");
        }
        try {
            Bed bed = new Bed();
            bed.setBedNumber(bedDTO.getBedNumber());
            bed.setBedType(bedDTO.getBedType());
            bed.setBedStatus(bedDTO.getBedStatus());
            bed.setWard(ward.get());
            bedRepository.save(bed);
        }catch (Exception e){
            log.info("Save Bed Error");
        }

    }

    @Override
    public List<BedDTO> findAll() {
        try {
            List<BedDTO> bedDTOList = new ArrayList<>();
            List<Bed> beds = bedRepository.findAll();
            for (Bed bed : beds) {
                BedDTO bedDTO = new BedDTO();
                bedDTO.setBedId(bed.getBedId());
                bedDTO.setBedNumber(bed.getBedNumber());
                bedDTO.setBedType(bed.getBedType());
                bedDTO.setBedStatus(bed.getBedStatus());
                bedDTO.setWardId(bed.getWard().getWardId());
                bedDTOList.add(bedDTO);
            }
            return bedDTOList;
        }catch (Exception e){
            log.info("Find All Bed Error");
            throw e;
        }
    }

    @Override
    public void updateBed(BedDTO bedDTO) {
        log.info("Update Bed DTO");
        try {
            Optional<Ward> ward = wardRepository.findById(bedDTO.getWardId());
            Optional<Bed> optionalbed = bedRepository.findById(bedDTO.getBedId());
            if(optionalbed.isEmpty()){
                throw new RuntimeException("Ward Not Found");
            }
            if (ward.isEmpty()){
                throw new RuntimeException("Ward Not Found");
            }
            Bed bed1 = optionalbed.get();
            bed1.setBedNumber(bedDTO.getBedNumber());
            bed1.setBedType(bedDTO.getBedType());
            bed1.setBedStatus(bedDTO.getBedStatus());
            bed1.setWard(ward.get());
            bedRepository.save(bed1);
        }catch (Exception e){
            log.info("Update Bed Error");
        }

    }

    @Override
    public void deleteBed(long id) {
        log.info("Delete Bed DTO");
        try {
            Optional<Bed> optionalbed = bedRepository.findById(id);
            if(optionalbed.isEmpty()){
                throw new RuntimeException("Bed Not Found");
            }
            Bed bed1 = optionalbed.get();
            bed1.setBedStatus(BedStatus.UNAVAILABLE);
            bedRepository.save(bed1);


        } catch (Exception e) {
            log.info("Delete Bed Error");
        }
    }

    @Override
    public List<BedDTO> filterBed(String bedNumber) {
        try {
            List<BedDTO> bedDTOList = new ArrayList<>();
            List<Bed> beds = bedRepository.filterBed(bedNumber);

            for (Bed bed : beds) {
                BedDTO bedDTO = new BedDTO(
                        bed.getBedId(),
                        bed.getBedNumber(),
                        bed.getBedType(),
                        bed.getBedStatus(),
                        bed.getWard().getWardId()
                );
                bedDTOList.add(bedDTO);

            }
            return bedDTOList;
        }catch (Exception e){
            log.info("Filter Bed Error");
            throw e;
        }
    }
}

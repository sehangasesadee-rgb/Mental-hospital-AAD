package lk.ijse.mental_hospital.controller;

import lk.ijse.mental_hospital.constant.CommonResponse;
import lk.ijse.mental_hospital.dto.PaymentDTO;
import lk.ijse.mental_hospital.service.PaymentService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.mental_hospital.constant.ResponsCode.OPERATION_SUCCESS;
import static lk.ijse.mental_hospital.constant.ResponseMassage.SUCCESS_MASSAGE;

@Slf4j
@Data
@RestController
@RequestMapping("v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse savePayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.savePayment(paymentDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllPayments() {
        return new CommonResponse(OPERATION_SUCCESS,paymentService.getAllPayments() ,SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updatePayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.updatePayment(paymentDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterPayment(@RequestParam(value = "paymentId", required = false) long paymentId) {
        List<PaymentDTO> paymentDTOS = paymentService.filerPayment(paymentId);
        return new CommonResponse(OPERATION_SUCCESS,paymentDTOS,SUCCESS_MASSAGE);
    }

    @DeleteMapping(value = "/{paymentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deletePayment(@PathVariable long paymentId) {
        paymentService.deletePayment(paymentId);
        return new CommonResponse(OPERATION_SUCCESS,SUCCESS_MASSAGE);
    }
}
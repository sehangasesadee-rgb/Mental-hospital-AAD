package lk.ijse.mental_hospital.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private int status;
    private String message;
    private Object body;

    public CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommonResponse(int status, Object body ,String message) {
        this.status = status;
        this.body = body;
        this.message = message;
    }


}


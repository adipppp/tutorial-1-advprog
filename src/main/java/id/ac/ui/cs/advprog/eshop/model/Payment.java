package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
    }

    public void setMethod(String method) {
    }

    public void setStatus(String status) {
    }
}

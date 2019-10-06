package jaw.services;

import org.springframework.stereotype.Component;

import jaw.entity.CreditCheckRequest;

@Component
public class CreditVerificationService {

    private static final String CREDIT_CHECK_PASS = "Pass Credit Check";
    private static final String CREDIT_CHECK_FAIL = "Fail Credit Check";

    /**
     * Emulates using an external credit verification service.
     *
     */
    public String verifyCredit(CreditCheckRequest creditCheckReq) {

        // Waste some time to simulate a long-running processs.
        for (int i = 0; i < Integer.MAX_VALUE; ++i) {
            ;
        }

        return genPassFail();
    }

    /**
     * Randomly generates a Pass/Fail status.
     *
     */
    private String genPassFail() {
        String passFail;
        int result = (int) Math.round(Math.random());

        if (result == 0) {
            passFail = CREDIT_CHECK_FAIL;
        } else {
            passFail = CREDIT_CHECK_PASS;
        }

        return passFail;
    }

}

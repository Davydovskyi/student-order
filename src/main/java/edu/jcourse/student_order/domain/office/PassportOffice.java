package edu.jcourse.student_order.domain.office;

import java.io.Serial;

public class PassportOffice extends Office {

    @Serial
    private static final long serialVersionUID = -9177593281829710989L;

    public PassportOffice() {
        super();
    }

    public PassportOffice(Long officeID, String officeAreaId, String officeName) {
        super(officeID, officeAreaId, officeName);
    }
}

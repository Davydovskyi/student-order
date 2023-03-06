package edu.jcourse.student_order.domain.office;

import java.io.Serial;

public class RegisterOffice extends Office {

    @Serial
    private static final long serialVersionUID = -1617684574581663237L;

    public RegisterOffice() {
        super();
    }

    public RegisterOffice(Long officeID, String officeAreaId, String officeName) {
        super(officeID, officeAreaId, officeName);
    }
}

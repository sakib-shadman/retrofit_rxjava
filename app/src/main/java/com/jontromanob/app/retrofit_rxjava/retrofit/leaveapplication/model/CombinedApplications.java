package com.jontromanob.app.retrofit_rxjava.retrofit.leaveapplication.model;

import com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication.model.VisitApplicationDetails;

import java.util.List;

/**
 * Created by Hi on 10/11/2019.
 */

public class CombinedApplications {

    List<LeaveApplicationDetails> leaveApplicationDetails;
    List<VisitApplicationDetails> visitApplicationDetails;
    public CombinedApplications( List<LeaveApplicationDetails> leaveApplicationDetails,List<VisitApplicationDetails> visitApplicationDetails){

        this.leaveApplicationDetails = leaveApplicationDetails;
        this.visitApplicationDetails = visitApplicationDetails;
    }

    public List<LeaveApplicationDetails> getLeaveApplicationDetails() {
        return leaveApplicationDetails;
    }

    public void setLeaveApplicationDetails(List<LeaveApplicationDetails> leaveApplicationDetails) {
        this.leaveApplicationDetails = leaveApplicationDetails;
    }

    public List<VisitApplicationDetails> getVisitApplicationDetails() {
        return visitApplicationDetails;
    }

    public void setVisitApplicationDetails(List<VisitApplicationDetails> visitApplicationDetails) {
        this.visitApplicationDetails = visitApplicationDetails;
    }
}

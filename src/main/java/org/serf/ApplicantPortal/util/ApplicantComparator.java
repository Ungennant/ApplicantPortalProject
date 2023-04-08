package org.serf.ApplicantPortal.util;

import org.serf.ApplicantPortal.domain.Applicant;
import org.serf.ApplicantPortal.domain.Subject;

import java.util.Comparator;

public class ApplicantComparator implements Comparator<Applicant> {

    @Override
    public int compare(Applicant a1, Applicant a2) {
        int totalRate1 = 0;
        for (Subject s : a1.getSubjects()) {
            totalRate1 += s.getRate();
        }
        int totalRate2 = 0;
        for (Subject s : a2.getSubjects()) {
            totalRate2 += s.getRate();
        }
        return Integer.compare(totalRate2, totalRate1);
    }

}
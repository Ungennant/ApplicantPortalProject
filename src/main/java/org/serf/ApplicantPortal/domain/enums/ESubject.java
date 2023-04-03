package org.serf.ApplicantPortal.domain.enums;

public enum ESubject {
    MATH(1), HISTORY(2), UKRAINIAN(3), ENGLISH(4), LITERATURE(5);

    private final int id;

    ESubject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

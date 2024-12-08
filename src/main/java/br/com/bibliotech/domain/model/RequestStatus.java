package br.com.bibliotech.domain.model;

public enum RequestStatus {

    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    private final String name;

    RequestStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    boolean isPending() {
        return PENDING.equals(this);
    }

}

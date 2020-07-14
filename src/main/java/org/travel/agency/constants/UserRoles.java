package org.travel.agency.constants;

public enum UserRoles {
    CUSTOMER("CUSTOMER"), MANAGER("MANAGER");

    private final String roleName;

    UserRoles(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}

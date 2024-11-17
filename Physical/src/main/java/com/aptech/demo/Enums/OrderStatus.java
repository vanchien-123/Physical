package com.aptech.demo.Enums;

public enum OrderStatus {
	CANCELEDREQUEST,
    PENDING,
    TOSHIP,
    INTRANSIT,
    DELIVERED,
    COMPLETED,
    CANCELED;

    public boolean canTransitionTo(String newStatus) {
        if (this == CANCELED || this == COMPLETED) {
            return false;
        }

        switch (this) {
        	case CANCELEDREQUEST:
        		return newStatus == TOSHIP.toString() || newStatus == CANCELED.toString();
            case PENDING:
                return newStatus == TOSHIP.toString() || newStatus == CANCELED.toString();
            case TOSHIP:
                return newStatus == INTRANSIT.toString() || newStatus == CANCELED.toString();
            case INTRANSIT:
                return newStatus == DELIVERED.toString() || newStatus == CANCELED.toString();
            case DELIVERED:
                return newStatus == COMPLETED.toString();
            default:
                return false;
        }
    }
}

package com.example.healthyfood.model.view;

public class StatisticsViewModel {

    private final int authenticatedRequests;
    private final int anonymousRequests;

    public StatisticsViewModel(int authenticatedRequests, int anonymousRequests) {
        this.authenticatedRequests = authenticatedRequests;
        this.anonymousRequests = anonymousRequests;
    }

    public int getAuthenticatedRequests() {
        return authenticatedRequests;
    }

    public int getAnonymousRequests() {
        return anonymousRequests;
    }

    public int getTotalRequests() {
        return this.authenticatedRequests + this.anonymousRequests;
    }
}

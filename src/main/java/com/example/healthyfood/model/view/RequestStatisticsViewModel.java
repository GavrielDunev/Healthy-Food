package com.example.healthyfood.model.view;

public class RequestStatisticsViewModel {

    private final int authenticatedRequests;
    private final int anonymousRequests;

    public RequestStatisticsViewModel(int authenticatedRequests, int anonymousRequests) {
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

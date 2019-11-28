package com.pszymczyk.domain.model;

public interface EventPublisher {

    void send(DomainEvent domainEvent);

}

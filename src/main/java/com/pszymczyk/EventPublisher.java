package com.pszymczyk;

interface EventPublisher {

    void send(DomainEvent domainEvent);

}

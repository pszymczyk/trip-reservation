package com.pszymczyk.adapter.persistence;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.pszymczyk.domain.model.ReservationAdded;
import com.pszymczyk.domain.model.ReservationsReadModel;

@Component
class SpringDomainEventsListener {

    private final ReservationsReadModel reservationsReadModel;

    SpringDomainEventsListener(ReservationsReadModel reservationsReadModel) {
        this.reservationsReadModel = reservationsReadModel;
    }

    @Async
    @TransactionalEventListener
    public void after(ReservationAdded reservationAdded) throws InterruptedException {
        reservationsReadModel.update(reservationAdded);
    }
}

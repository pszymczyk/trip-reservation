package com.pszymczyk;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

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

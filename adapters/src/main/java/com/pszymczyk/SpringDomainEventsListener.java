package com.pszymczyk;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
class SpringDomainEventsListener {

    private final ReservationsReadModel reservationsReadModel;

    SpringDomainEventsListener(ReservationsReadModel reservationsReadModel) {
        this.reservationsReadModel = reservationsReadModel;
    }

    @EventListener
    @Async
    public void after(ReservationAdded reservationAdded) {
        reservationsReadModel.update(reservationAdded);
    }
}

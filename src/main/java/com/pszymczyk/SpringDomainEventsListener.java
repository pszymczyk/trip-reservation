package com.pszymczyk;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class SpringDomainEventsListener {

    private final ReservationsReadModel reservationsReadModel;

    SpringDomainEventsListener(ReservationsReadModel reservationsReadModel) {
        this.reservationsReadModel = reservationsReadModel;
    }

    @EventListener
    public void after(ReservationAdded reservationAdded) throws InterruptedException {
        reservationsReadModel.update(reservationAdded);
    }
}

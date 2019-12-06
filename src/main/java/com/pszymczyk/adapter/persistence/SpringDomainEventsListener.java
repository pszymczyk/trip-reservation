package com.pszymczyk.adapter.persistence;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.pszymczyk.domain.model.ReservationAdded;
import com.pszymczyk.domain.model.ReservationsReadModel;

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

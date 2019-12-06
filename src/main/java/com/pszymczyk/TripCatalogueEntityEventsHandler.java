package com.pszymczyk;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@RepositoryEventHandler
@Component
class TripCatalogueEntityEventsHandler {

    private final CrudTripRepository crudTripRepository;

    public TripCatalogueEntityEventsHandler(CrudTripRepository crudTripRepository) {
        this.crudTripRepository = crudTripRepository;
    }

    @HandleAfterCreate
    public void handleAfterCreate(TripCatalogueEntity tripCatalogueEntity) throws InterruptedException {
        TripEntity tripEntity = new TripEntity();
        tripEntity.setTripCode(tripCatalogueEntity.getTripCode());
        tripEntity.setSeatsNumber(tripCatalogueEntity.getSeatsNumber());
        crudTripRepository.save(tripEntity);
    }
}

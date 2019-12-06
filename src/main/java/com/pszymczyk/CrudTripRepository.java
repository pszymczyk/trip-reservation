package com.pszymczyk;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface CrudTripRepository extends CrudRepository<TripEntity, Long> {

    TripEntity findByTripCode(@Param("tripCode") String tripCode);

}

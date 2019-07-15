package com.pszymczyk;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

interface CrudTripRepository extends PagingAndSortingRepository<TripEntity, Long> {

    TripEntity findByTripCode(@Param("tripCode") String tripCode);

}

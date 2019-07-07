package com.pszymczyk.adapters;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "trips", collectionResourceRel = "trips")
interface CrudTripRepository extends PagingAndSortingRepository<TripEntity, Long> {

    TripEntity findByTripCode(@Param("tripCode") String tripCode);

}

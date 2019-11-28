package com.pszymczyk.adapter.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "trips", collectionResourceRel = "trips")
interface CrudTripCatalogueEntityRepository extends PagingAndSortingRepository<TripCatalogueEntity, Long> {

}

package com.pszymczyk.adapter.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "reservations", collectionResourceRel = "reservations")
interface CrudReservationsReadModelEntityRepository extends PagingAndSortingRepository<ReservationsReadModelEntity, String> {

}

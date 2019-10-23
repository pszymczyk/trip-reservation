package com.pszymczyk;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "reservations", collectionResourceRel = "reservations")
interface CrudReservationsReadModelEntityRepository extends PagingAndSortingRepository<ReservationsReadModelEntity, Long> {

    List<ReservationsReadModelEntity> findByTripCode(@Param("tripCode") String tripCode);
}

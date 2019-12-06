package com.pszymczyk.adapter.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "reservations", collectionResourceRel = "reservations")
interface CrudReservationsReadModelEntityRepository extends PagingAndSortingRepository<ReservationsReadModelEntity, String> {

    @Override
    @RestResource(exported = false)
    <S extends ReservationsReadModelEntity> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends ReservationsReadModelEntity> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    @Override
    @RestResource(exported = false)
    void delete(ReservationsReadModelEntity reservationsReadModelEntity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends ReservationsReadModelEntity> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}

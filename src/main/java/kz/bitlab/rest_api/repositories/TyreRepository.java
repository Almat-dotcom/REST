package kz.bitlab.rest_api.repositories;

import kz.bitlab.rest_api.entity.Tyre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TyreRepository extends JpaRepository<Tyre, Long> {
}

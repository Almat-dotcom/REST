package kz.bitlab.rest_api.repositories;

import kz.bitlab.rest_api.entity.TyreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TyreCategoryRepository extends JpaRepository<TyreCategory, Long> {
}

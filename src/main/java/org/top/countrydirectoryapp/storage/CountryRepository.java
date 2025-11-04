package org.top.countrydirectoryapp.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryDbEntity, Integer> {
    Optional<CountryDbEntity> findByIsoAlpha2(String isoAlpha2);
    Optional<CountryDbEntity> findByIsoAlpha3(String isoAlpha3);
    Optional<CountryDbEntity> findByIsoNumeric(String isoNumeric);
}
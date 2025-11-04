package org.top.countrydirectoryapp.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// CountryRepository - JPA-репозиторий для работы со странами
@Repository
public interface CountryRepository extends JpaRepository<CountryDbEntity, Integer> { // ID типа Integer

    // findByIsoAlpha2 - получение записи по двухбуквенному коду
    Optional<CountryDbEntity> findByIsoAlpha2(String isoAlpha2);

    // findByIsoAlpha3 - получение записи по трёхбуквенному коду
    Optional<CountryDbEntity> findByIsoAlpha3(String isoAlpha3);

    // findByIsoNumeric - получение записи по числовому коду
    Optional<CountryDbEntity> findByIsoNumeric(String isoNumeric);
}
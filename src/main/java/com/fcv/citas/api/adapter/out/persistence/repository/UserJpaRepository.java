package com.fcv.citas.api.adapter.out.persistence.repository;

import com.fcv.citas.api.adapter.out.persistence.entity.UserJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);

    Optional<UserJpaEntity> findByEmail(String email);
}

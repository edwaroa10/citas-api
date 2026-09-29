package com.fcv.citas.api.adapter.out.persistence;

import com.fcv.citas.api.adapter.out.persistence.entity.RoleJpaEntity;
import com.fcv.citas.api.adapter.out.persistence.entity.UserJpaEntity;
import com.fcv.citas.api.adapter.out.persistence.repository.RoleJpaRepository;
import com.fcv.citas.api.adapter.out.persistence.repository.UserJpaRepository;
import com.fcv.citas.api.application.port.out.UserRepositoryPort;
import com.fcv.citas.api.application.service.DataIntegrityRaceException;
import com.fcv.citas.api.domain.model.User;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocument(String documentType, String documentNumber) {
        return userJpaRepository.existsByDocumentTypeAndDocumentNumber(documentType, documentNumber);
    }

    @Override
    public User save(User user) {
        Set<RoleJpaEntity> roleEntities = user.getRoles().stream()
                .map(code -> roleJpaRepository.findByCode(code)
                        .orElseThrow(() -> new IllegalStateException("Rol no encontrado en catálogo: " + code)))
                .collect(Collectors.toCollection(HashSet::new));

        UserJpaEntity entity = new UserJpaEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocumentType(),
                user.getDocumentNumber(),
                user.getEmail(),
                user.getPhone(),
                user.getPasswordHash(),
                user.isActive(),
                false,
                roleEntities);

        UserJpaEntity saved;
        try {
            saved = userJpaRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            if (userJpaRepository.existsByEmail(user.getEmail())) {
                throw DataIntegrityRaceException.forEmail();
            }
            throw DataIntegrityRaceException.forDocument();
        }

        return toDomain(saved);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(this::toDomain);
    }

    private User toDomain(UserJpaEntity entity) {
        Set<String> roleCodes = entity.getRoles().stream()
                .map(RoleJpaEntity::getCode)
                .collect(Collectors.toSet());
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDocumentType(),
                entity.getDocumentNumber(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPasswordHash(),
                entity.isActive(),
                roleCodes);
    }
}

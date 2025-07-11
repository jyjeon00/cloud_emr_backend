package com.cloud.emr.Main.User.repository;

import com.cloud.emr.Main.User.entity.UserEntity;
import com.cloud.emr.Main.User.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLoginId(String LoginId);

    boolean existsByLoginId(String LoginId);

    List<UserEntity> findAllByRole(RoleType role);
}

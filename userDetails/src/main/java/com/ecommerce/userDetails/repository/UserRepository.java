package com.ecommerce.userDetails.repository;

import com.ecommerce.userDetails.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}

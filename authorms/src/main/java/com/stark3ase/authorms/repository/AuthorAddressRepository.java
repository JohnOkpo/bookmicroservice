package com.stark3ase.authorms.repository;

import com.stark3ase.authorms.entity.AuthorAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorAddressRepository extends JpaRepository<AuthorAddress, UUID>
{
    AuthorAddress getAuthorAddressByAddressId(UUID addressId);
}

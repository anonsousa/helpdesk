package br.com.it.helpdesk.domain.repositories;

import br.com.it.helpdesk.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userEnabled = :status WHERE u.id = :id")
    void updateUserEnabledStatus(@Param("id") UUID id, @Param("status") boolean status);
}

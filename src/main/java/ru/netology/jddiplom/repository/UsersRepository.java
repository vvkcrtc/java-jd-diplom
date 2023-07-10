package ru.netology.jddiplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserData, Long> {
    UserData findByLogin(String login);
    //UserDetails findByLogin(String  login);

    @Query("SELECT p FROM UserData p  WHERE p.login=(:pLogin) AND p.password=(:pPassword)")
    Optional<UserData> findPersonByLoginAndPassword(@Param("pLogin") String pLogin, @Param("pPassword") String pPassword);

    @Query("SELECT p FROM UserData p")
    List<UserData> getAll();



}



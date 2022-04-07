package com.jb.couponSystem.Repositories;

import com.jb.couponSystem.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // METHODS

    /**
     * checks if a row exists by id not equal and email
     * @param id
     * @param email
     * @return boolean answer
     */
    boolean existsByIdNotAndEmail(int id, String email);

    /**
     * checks if a row exists by email and password
     * @param email
     * @param password
     * @return boolean answer
     */
    boolean existsByEmailAndPassword(String email, String password);

    /**
     * checks if a row exists by email
     * @param email
     * @return boolean answer
     */
    boolean existsByEmail(String email);

    /**
     * returns an `id` field by `email` field
     * @param email
     * @return int id
     */
    @Query(value = "SELECT id FROM customer WHERE email=?1", nativeQuery = true)
    // SELECT id FROM couponDB.customer WHERE email=?1
    int findIdByEmail(String email);


}


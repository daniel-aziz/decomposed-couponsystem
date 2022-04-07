package com.jb.couponSystem.Repositories;

import com.jb.couponSystem.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // METHODS

    /**
     * checks if a row exists by email and password
     * @param email
     * @param Password
     * @return boolean answer
     */
    boolean existsByEmailAndPassword(String email, String Password);

    /**
     * checks if a row exists by name or email
     * @param name
     * @param email
     * @return
     */
    boolean existsByNameOrEmail(String name, String email);

    /**
     * checks if a row exists by id not equal and email
     * @param id
     * @param email
     * @return boolean answer
     */
    boolean existsByIdNotAndEmail(int id, String email);

    /**
     * returns an `id` field by `email` field
     * @param email
     * @return int id
     */
    @Query(value = "SELECT id FROM company WHERE email=?1", nativeQuery = true)
    int findIdByEmail(String email);
}

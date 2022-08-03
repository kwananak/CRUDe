package com.kwan.CRUDe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//the repository for the oil
@Repository
public interface OilRepo extends JpaRepository<Oil, Long> {

}

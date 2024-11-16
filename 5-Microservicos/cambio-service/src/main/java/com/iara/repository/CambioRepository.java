package com.iara.repository;

import com.iara.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
    //basic crud; create, read, update, and delete
    Cambio findByFromAndTo(String from, String to);
}

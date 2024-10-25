package com.iara.cambioservice.repository;

import com.iara.cambioservice.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
    //basic crud; create, read, update, and delete
    Cambio findByFromAndTo(String from, String to);
}

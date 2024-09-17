package com.api.usuario.repo;

import com.api.usuario.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepo extends JpaRepository<Phone,Integer> {
}

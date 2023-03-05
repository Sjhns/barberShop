package com.kbs.barber.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbs.barber.models.Barber;

public interface  BarberRepository extends JpaRepository<Barber, UUID>{
	
}



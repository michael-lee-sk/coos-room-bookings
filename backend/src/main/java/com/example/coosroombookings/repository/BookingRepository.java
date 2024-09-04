
package com.example.coosroombookings.repository;

import com.example.coosroombookings.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

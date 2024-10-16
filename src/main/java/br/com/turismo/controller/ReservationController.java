package br.com.turismo.controller;

import br.com.turismo.entities.Reservation;
import br.com.turismo.dto.ReservationRequestDTO;
import br.com.turismo.security.TokenExtractor;
import br.com.turismo.services.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private TokenExtractor tokenExtractor;

  @PostMapping
  public ResponseEntity<String> createReservation(HttpServletRequest request, @RequestBody ReservationRequestDTO reservation) {
    String userId = tokenExtractor.extractUserIdFromToken(request.getHeader("Authorization"));

    try {
      String message = reservationService.createReservation(userId, reservation.touristSpotId(), reservation.amountOfPeople(), reservation.reservationDate(), reservation.reservationTime());
      return ResponseEntity.ok().body(message);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
    try {
      reservationService.updateReservation(id, updatedReservation);
      return ResponseEntity.ok().body("Reserva atualizada com sucesso.");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
    try {
      reservationService.cancelReservation(id);
      return ResponseEntity.ok().body("Reserva cancelada com sucesso.");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}


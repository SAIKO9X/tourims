package br.com.turismo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequestDTO(
  Long touristSpotId,
  int amountOfPeople,
  LocalDate reservationDate,
  LocalTime reservationTime) {
}

package br.com.turismo.services;

import br.com.turismo.entities.Reservation;
import br.com.turismo.repositories.ReservationRepository;
import br.com.turismo.repositories.TouristSpotRepository;
import br.com.turismo.repositories.TouristUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TouristUserRepository touristUserRepository;

    @Autowired
    private TouristSpotRepository touristSpotRepository;

    public String createReservation(String userId, Long touristSpotId, int amountOfPeople, LocalDate reservationDate, LocalTime reservationTime) {
        var user = touristUserRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado ou inválido."));

        if (amountOfPeople <= 0) {
            throw new IllegalArgumentException("A quantidade de pessoas deve ser maior que zero.");
        }

        var touristSpot = touristSpotRepository.findById(touristSpotId).orElseThrow(() -> new IllegalArgumentException("Ponto turístico não encontrado."));
        String spotName = touristSpot.getNome();

        var reservation = Reservation.builder()
                .user(user)
                .spot(touristSpot)
                .amountOfPeople(amountOfPeople)
                .reservationDate(reservationDate)
                .reservationTime(reservationTime)
                .status("active")
                .build();

        reservationRepository.save(reservation);

        return "Reserva criada para o " + spotName + ".";
    }

    public void updateReservation(Long reservationId, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada com o ID fornecido."));

        existingReservation.setReservationDate(updatedReservation.getReservationDate());
        existingReservation.setAmountOfPeople(updatedReservation.getAmountOfPeople());

        reservationRepository.save(existingReservation);
    }

    public void cancelReservation(Long reservationId) {
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada com o ID fornecido."));

        existingReservation.setStatus("cancelled");

        reservationRepository.save(existingReservation);
    }
}

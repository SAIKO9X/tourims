package br.com.turismo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da reserva")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tourist_user_id")
    @Schema(description = "Usuário que fez a reserva")
    private TouristUser user;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id")
    @Schema(description = "Local turístico da reserva")
    private TouristSpot spot;

    @NotNull(message = "O número de pessoas é obrigatório")
    @Schema(description = "Número de pessoas na reserva")
    private Integer amountOfPeople;

    @NotNull(message = "A data da reserva é obrigatória")
    @Schema(description = "Data da reserva")
    private LocalDate reservationDate;

    @NotNull(message = "O horário da reserva é obrigatório")
    @Schema(description = "Horário da reserva")
    private LocalTime reservationTime;

    @Schema(description = "Status da reserva")
    private String status = "active";
}

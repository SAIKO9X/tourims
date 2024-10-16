package br.com.turismo.controller;

import br.com.turismo.entities.TouristSpot;
import br.com.turismo.services.TouristSpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/touristspot")
@Tag(name = "Ponto Turístico", description = "Operações relacionadas a pontos turísticos")
public class TouristSpotController {

    @Autowired
    private TouristSpotService touristSpotService;

    @GetMapping
    @Operation(summary = "Listar todos os pontos turísticos", description = "Retorna uma lista de todos os pontos turísticos disponíveis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pontos turísticos retornada com sucesso")
    })
    public ResponseEntity<List<TouristSpot>> getAllTouristSpots() {
        List<TouristSpot> touristSpots = touristSpotService.getAllTouristSpots();
        return new ResponseEntity<>(touristSpots, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter ponto turístico por ID", description = "Retorna um ponto turístico com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ponto turístico encontrado"),
            @ApiResponse(responseCode = "404", description = "Ponto turístico não encontrado")
    })
    public ResponseEntity<TouristSpot> getTouristSpotById(@PathVariable Long id) {
        TouristSpot touristSpot = touristSpotService.getTouristSpotById(id);

        if (touristSpot != null) {
            return new ResponseEntity<>(touristSpot, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

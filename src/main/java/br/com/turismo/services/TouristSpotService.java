package br.com.turismo.services;

import br.com.turismo.entities.TouristSpot;
import br.com.turismo.repositories.TouristSpotRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TouristSpotService {

    @Autowired
    private TouristSpotRepository touristSpotRepository;

    public List<TouristSpot> getAllTouristSpots() {
        return touristSpotRepository.findAll();
    }

    public TouristSpot getTouristSpotById(Long id) {
        Optional<TouristSpot> touristSpotOptional = touristSpotRepository.findById(id);
        return touristSpotOptional.orElse(null);
    }

    public void inicializarDadosFicticios() {
        if (touristSpotRepository.count() == 0) {
            try {
                ClassPathResource resource = new ClassPathResource("data/tourist_spots.json");
                InputStreamReader reader = new InputStreamReader(resource.getInputStream());
                TouristSpot[] spots = new Gson().fromJson(reader, TouristSpot[].class);
                touristSpotRepository.saveAll(Arrays.asList(spots));
            } catch (IOException e) {
                System.err.println("Erro ao inicializar dados fictícios: " + e.getMessage());
            }
        }
    }
}

package br.com.turismo;

import br.com.turismo.services.TouristSpotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TurismoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TurismoApplication.class, args);
        inicializarDadosFicticios(context);
    }

    private static void inicializarDadosFicticios(ConfigurableApplicationContext context) {
        TouristSpotService touristSpotService = context.getBean(TouristSpotService.class);
        touristSpotService.inicializarDadosFicticios();
    }
}

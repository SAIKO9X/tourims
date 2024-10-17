package br.com.turismo.services;

import br.com.turismo.entities.Assessment;
import br.com.turismo.dto.AssessmentRequestDTO;
import br.com.turismo.repositories.AssessmentRepository;
import br.com.turismo.repositories.TouristSpotRepository;
import br.com.turismo.repositories.TouristUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {

  @Autowired
  private AssessmentRepository assessmentRepository;

  @Autowired
  private TouristUserRepository touristUserRepository;

  @Autowired
  private TouristSpotRepository touristSpotRepository;

  public String createAssessment(String userId, AssessmentRequestDTO assessmentRequestDTO) {
    var user = touristUserRepository.findById(Long.valueOf(userId))
      .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado ou inválido."));

    var touristSpot = touristSpotRepository.findById(assessmentRequestDTO.touristSpotId())
      .orElseThrow(() -> new IllegalArgumentException("Ponto turístico não encontrado."));

    var assessment = Assessment.builder()
      .user(user)
      .spot(touristSpot)
      .pontuacao(assessmentRequestDTO.pontuacao())
      .comentario(assessmentRequestDTO.comentario())
      .build();

    assessmentRepository.save(assessment);

    return "Avaliação criada com sucesso.";
  }
}

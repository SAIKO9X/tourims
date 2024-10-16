package br.com.turismo.controller;

import br.com.turismo.dto.AssessmentRequestDTO;
import br.com.turismo.security.TokenExtractor;
import br.com.turismo.services.AssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessments")
@Tag(name = "Avaliações", description = "Operações relacionadas a avaliações de pontos turísticos")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private TokenExtractor tokenExtractor;

    @PostMapping
    @Operation(summary = "Criar avaliação", description = "Cria uma nova avaliação para um ponto turístico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<String> createAssessment(HttpServletRequest request, @RequestBody AssessmentRequestDTO assessmentRequestDTO) {
        String userId = tokenExtractor.extractUserIdFromToken(request.getHeader("Authorization"));

        try {
            String message = assessmentService.createAssessment(userId, assessmentRequestDTO);
            return ResponseEntity.ok().body(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

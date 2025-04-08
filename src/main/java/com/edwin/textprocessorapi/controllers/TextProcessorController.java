package com.edwin.textprocessorapi.controllers;

import com.edwin.textprocessorapi.models.TextProcessorDTO;
import com.edwin.textprocessorapi.services.TextProcessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para procesar las peticiones de procesamiento de textos.
 */
@RestController
@RequestMapping("/api/text")
public class TextProcessorController {

    private final TextProcessorService textProcessorService;

    //Constructor principal.
    public TextProcessorController(TextProcessorService textProcessorService) {
        System.out.println("✅ TextoController inicializado correctamente.");
        this.textProcessorService = textProcessorService;
    }

    @PostMapping
    public String processText(@RequestBody String textInput) {
        System.out.println("✅ TextoController procesando....");
        List<TextProcessorDTO> result = textProcessorService.processText(textInput);

        System.out.println("✅ TextoController finalizó el proceso...");
        return result.stream()
                .map(dto -> dto.getText() + "\\" + dto.isValid())
                .collect(Collectors.joining("\n"));
    }

}

package com.edwin.textprocessorapi.services;

import com.edwin.textprocessorapi.models.TextProcessorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITextProcessorService {

    /**
     *
     * Procesa una entrada de texto, valida su formato y la cantidad de palabras
     * que contiene la entrada.
     *
     * @param text Texto a procesar
     * @return La misma entrada de texto sin caractares especiales.
     */
    List<TextProcessorDTO> processText(String text) throws Exception;
}

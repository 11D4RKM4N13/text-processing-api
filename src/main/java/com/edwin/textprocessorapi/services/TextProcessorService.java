package com.edwin.textprocessorapi.services;

import com.edwin.textprocessorapi.exceptions.InvalidFormatException;
import com.edwin.textprocessorapi.models.TextProcessorDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TextProcessorService implements ITextProcessorService {

    private static String TAG = "[TextProcessorService]: ";
    private static String Message = "Formato de texto no valido";

    @Override
    public List<TextProcessorDTO> processText(String text) {

        List<TextProcessorDTO> output = new ArrayList<>();

        //1. Separar la entrada de texto.
        String[] lines = text.split("\\r?\\n");

        //2. Iteramos sobre todas las posibles entradas de texto.
        for (String line : lines) {

            //3. Validamos el formato de la entrada de texto.
            validateTextFormat(line);

            //4. Separamos la entrada de texto y obtenemos el número de palabras y las respectivas palabras.
            String[] parts = line.split("\\\\", 2);
            int wordCountExpected = Integer.parseInt(parts[0]);
            String word = parts[1];

            //5. Limpiamos las palabras para no dejar ningún caracter especial y contar la cantidad de palabras.
            String cleanString = word.replaceAll("[^a-zA-Z ]", "").toLowerCase().trim().replaceAll(" +", " ");

            int countWords = cleanString.isEmpty() ? 0 : cleanString.split(" ").length;

            //6. Verificamos si la cantidad de palabras coinciden.
            boolean wordCountMatched = wordCountExpected == countWords;

            //7. Agregamos el resultado a un listado.
            output.add(new TextProcessorDTO(cleanString, wordCountMatched));
        }

        return output;
    }

    /**
     *
     * Valida el formato del texto.
     * 1. Valida si la entrada de texto no está vacía.
     * 2. Valida si en la entrada de texto existe el delimitador o separador.
     * 3. Divide la entrada de texto en 2 partes utilizando como delimitador el caracter {\}.
     * 4. Valida que existan las 2 partes generadas con el split y valida que la primera parte antes del delimitador
     * sea un número entero positivo.
     *
     * @param text Texto de entrada para validar el formato.
     * @throws InvalidFormatException Excepción de formato invalido.
     */
    private void validateTextFormat(String text) {
        if (text == null || text.isEmpty())
        {
            throw new InvalidFormatException(TAG + Message);
        }

        if (!text.contains("\\"))
        {
            throw new InvalidFormatException(TAG + Message);
        }

        String[] parts = text.split("\\\\", 2);
        if (parts.length != 2 || !parts[0].matches("\\d+"))
        {
            throw new InvalidFormatException(TAG + Message);
        }
    }

}

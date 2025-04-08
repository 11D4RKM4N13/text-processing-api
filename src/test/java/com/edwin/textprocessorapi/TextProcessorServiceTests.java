package com.edwin.textprocessorapi;

import com.edwin.textprocessorapi.exceptions.InvalidFormatException;
import com.edwin.textprocessorapi.models.TextProcessorDTO;
import com.edwin.textprocessorapi.services.ITextProcessorService;
import com.edwin.textprocessorapi.services.TextProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextProcessorServiceTests {

    private ITextProcessorService textProcessorService;

    @BeforeEach
    public void setUp() {
        textProcessorService = new TextProcessorService();
    }

    @Test
    void ProcessTextSuccessfully() throws Exception {
        String input = String.join("\n",
                "3\\the force is strong in this one",
                "7\\take what you can, give nothing back",
                "5\\Here’s looking at you, kid.");

        List<TextProcessorDTO> resultado = textProcessorService.processText(input);

        assertEquals(3, resultado.size());

        assertEquals("the force is strong in this one", resultado.get(0).getText());
        assertFalse(resultado.get(0).isValid());

        assertEquals("take what you can give nothing back", resultado.get(1).getText());
        assertTrue(resultado.get(1).isValid());

        assertEquals("heres looking at you kid", resultado.get(2).getText());
        assertTrue(resultado.get(2).isValid());
    }

    @Test
    void ThrowExceptionWhenProcessTextFails() throws Exception {
        String input = "5 esta línea está mal formada";

        assertThrows(InvalidFormatException.class, () -> {
            textProcessorService.processText(input);
        });
    }

    @Test
    void ShouldReturnEmptyListWhenProcessTextFails() throws Exception {
        String input = "0\\...";

        List<TextProcessorDTO> resultado = textProcessorService.processText(input);
        assertEquals("".trim(), resultado.get(0).getText());
        assertTrue(resultado.get(0).getText().isEmpty());
        assertTrue(resultado.get(0).isValid());
    }

    @Test
    void shouldReturnEmptyTextWhenOnlySpecialCharactersPresent() throws Exception {
        String input = "0\\@#$%&*!";

        List<TextProcessorDTO> result = textProcessorService.processText(input);

        assertEquals("", result.get(0).getText());
        assertTrue(result.get(0).isValid());
    }

    @Test
    void shouldThrowExceptionForInvalidNumberFormat() {
        String input = "-1\\this should fail";

        assertThrows(InvalidFormatException.class, () -> {
            textProcessorService.processText(input);
        });
    }
}

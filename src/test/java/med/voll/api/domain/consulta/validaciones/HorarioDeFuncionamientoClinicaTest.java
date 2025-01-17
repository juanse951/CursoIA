package med.voll.api.domain.consulta.validaciones;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.dto.DatosAgendarConsulta;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorarioDeFuncionamientoClinicaTest {

    private final HorarioDeFuncionamientoClinica validador = new HorarioDeFuncionamientoClinica();

    @Test
    void deberiaPermitirHorarioValido() {
        // Arrange
        var datosMock = Mockito.mock(DatosAgendarConsulta.class);
        Mockito.when(datosMock.fecha()).thenReturn(LocalDateTime.of(2025, 1, 16, 10, 0)); // Jueves 10:00 AM

        // Act & Assert
        assertDoesNotThrow(() -> validador.validar(datosMock));
    }

    @Test
    void deberiaLanzarExcepcionSiEsDomingo() {
        // Arrange
        var datosMock = Mockito.mock(DatosAgendarConsulta.class);
        Mockito.when(datosMock.fecha()).thenReturn(LocalDateTime.of(2025, 1, 19, 10, 0)); // Domingo 10:00 AM

        // Act & Assert
        assertThrows(ValidationException.class, () -> validador.validar(datosMock),
                "El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas");
    }

    @Test
    void deberiaLanzarExcepcionAntesDeApertura() {
        // Arrange
        var datosMock = Mockito.mock(DatosAgendarConsulta.class);
        Mockito.when(datosMock.fecha()).thenReturn(LocalDateTime.of(2025, 1, 16, 6, 59)); // Jueves 6:59 AM

        // Act & Assert
        assertThrows(ValidationException.class, () -> validador.validar(datosMock),
                "El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas");
    }

    @Test
    void deberiaLanzarExcepcionDespuesDeCierre() {
        // Arrange
        var datosMock = Mockito.mock(DatosAgendarConsulta.class);
        Mockito.when(datosMock.fecha()).thenReturn(LocalDateTime.of(2025, 1, 16, 19, 1)); // Jueves 7:01 PM

        // Act & Assert
        assertThrows(ValidationException.class, () -> validador.validar(datosMock),
                "El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas");
    }

    @Test
    void deberiaLanzarExcepcionSiEsDomingoYFueraDeHorario() {
        // Arrange
        var datosMock = Mockito.mock(DatosAgendarConsulta.class);
        Mockito.when(datosMock.fecha()).thenReturn(LocalDateTime.of(2025, 1, 19, 20, 0)); // Domingo 8:00 PM

        // Act & Assert
        assertThrows(ValidationException.class, () -> validador.validar(datosMock),
                "El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas");
    }
}

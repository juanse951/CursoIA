package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.dto.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos) {
        var diaConsulta = datos.fecha().getDayOfWeek();
        var horaConsulta = datos.fecha().toLocalTime(); // Obtiene solo la hora

        var domingo = DayOfWeek.SUNDAY.equals(diaConsulta);
        var antesDeApertura = horaConsulta.isBefore(java.time.LocalTime.of(7, 0)); // Antes de las 07:00
        var despuesDeCierre = horaConsulta.isAfter(java.time.LocalTime.of(19, 0)); // Después de las 19:00

        if (domingo || antesDeApertura || despuesDeCierre) {
            throw new ValidationException("El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas");
        }
    }
}



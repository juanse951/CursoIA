package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.dto.DatosCancelamientoConsulta;

public interface ValidadorCancelamientoDeConsulta {
    void validar(DatosCancelamientoConsulta datos);
}

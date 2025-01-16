package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.dto.DatosAgendarConsulta;

public interface ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos);
}

package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.paciente.Paciente;

public record DatosListaPaciente(Long id, String nombre, String email, String documento) {

    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }

}

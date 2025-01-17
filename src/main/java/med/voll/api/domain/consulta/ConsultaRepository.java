package med.voll.api.domain.consulta;


import med.voll.api.domain.consulta.dto.DatosRelatoriosConsultaMensual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime fecha);

    @Query("SELECT new med.voll.api.domain.consulta.dto.DatosRelatoriosConsultaMensual" +
            "(c.medico.nombre, COUNT(c)) " +
            "FROM Consulta c " +
            "WHERE YEAR(c.fecha) = :year AND MONTH(c.fecha) = :month " +
            "GROUP BY c.medico.nombre")
    List<DatosRelatoriosConsultaMensual> buscarConsultasPorMes(@Param("year") int year, @Param("month") int month);
}

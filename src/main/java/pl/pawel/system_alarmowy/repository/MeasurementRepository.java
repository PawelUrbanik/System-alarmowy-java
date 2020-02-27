package pl.pawel.system_alarmowy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pawel.system_alarmowy.model.Measurement;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Measurement findFirstByDateTimeBeforeOrderByDateTimeDesc(LocalDateTime dateTime);

    List<Measurement> findAllByDateTimeBetween(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

}

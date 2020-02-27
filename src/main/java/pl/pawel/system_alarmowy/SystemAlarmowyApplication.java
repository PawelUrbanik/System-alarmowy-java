package pl.pawel.system_alarmowy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.pawel.system_alarmowy.model.Measurement;
import pl.pawel.system_alarmowy.repository.MeasurementRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class SystemAlarmowyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SystemAlarmowyApplication.class, args);
        MeasurementRepository measurementRepository = ctx.getBean(MeasurementRepository.class);
        Measurement measurement = new Measurement(1,2,3);
        Measurement measurement1 = new Measurement(11,223,31);
        Measurement measurement2 = new Measurement(331,452,13);
        Measurement measurement3 = new Measurement(121,32,123);
        Measurement measurement4 = new Measurement(112,232,3112);
        measurementRepository.save(measurement);
        measurementRepository.save(measurement1);
        measurementRepository.save(measurement2);
        measurementRepository.save(measurement3);
        measurementRepository.save(measurement4);
        System.out.println(measurementRepository.getOne(measurementRepository.count()).toString());
    }

}

package pl.pawel.system_alarmowy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.pawel.system_alarmowy.model.Measurement;
import pl.pawel.system_alarmowy.repository.MeasurementRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class SystemAlarmowyApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SystemAlarmowyApplication.class, args);
        MeasurementRepository measurementRepository = ctx.getBean(MeasurementRepository.class);
        Measurement measurement = new Measurement(1,2,3);
        Thread.sleep(1000);
        Measurement measurement1 = new Measurement(11,23,31);
        Thread.sleep(1000);

        Measurement measurement2 = new Measurement(31,52,13);
        Thread.sleep(1000);
        Measurement measurement3 = new Measurement(21,32,23);
        Thread.sleep(1000);
        Measurement measurement4 = new Measurement(50,45,31);
        measurementRepository.save(measurement);
        measurementRepository.save(measurement1);
        measurementRepository.save(measurement2);
        measurementRepository.save(measurement3);
        measurementRepository.save(measurement4);
        System.out.println(measurementRepository.getOne(measurementRepository.count()).toString());
    }

}

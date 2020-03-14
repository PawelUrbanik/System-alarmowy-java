package pl.pawel.system_alarmowy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.pawel.system_alarmowy.model.Measurement;
import pl.pawel.system_alarmowy.repository.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementControllerRest {

    private MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementControllerRest(MeasurementRepository measurementRepository)
    {
        this.measurementRepository = measurementRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Measurement getMeasurement(){
        Measurement measurement = measurementRepository.findFirstByDateTimeBeforeOrderByDateTimeDesc(LocalDateTime.now());
        return measurement;
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Measurement> getMeasurements(){
//        List<Measurement> measurements = measurementRepository.findAll();
//        return measurements;
//    }

    @GetMapping(path = "/oneDay", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Measurement> getMeasurementsFromOneDay(){
        List<Measurement> oneDayMeasurements = measurementRepository.findAllByDateTimeBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now());
        return oneDayMeasurements;
    }

    @GetMapping(path = "/oneHour", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Measurement> getMeasurementsFromOneHour(){
        List<Measurement> oneHourMeasurements = measurementRepository.findAllByDateTimeBetween(LocalDateTime.now().minusHours(1L), LocalDateTime.now());
        return oneHourMeasurements;
    }

    @GetMapping(path = "/threeHour", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Measurement> getMeasurementsFromThreeHours(){
        List<Measurement> threeHoursMeasurements = measurementRepository.findAllByDateTimeBetween(LocalDateTime.now().minusHours(3L), LocalDateTime.now());
        return threeHoursMeasurements;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMeasurement(@RequestBody Measurement measurement)
    {
        if ( (measurement.getValue1() >0 || measurement.getValue2() >0 || measurement.getValue3()>0)){
            //throw new IllegalArgumentException("Value is null");
        }
        Measurement measurementToSave = new Measurement(measurement.getValue1(), measurement.getValue2(), measurement.getValue3());
        measurementRepository.save(measurementToSave);

    }
}

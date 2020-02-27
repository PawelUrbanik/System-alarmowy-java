package pl.pawel.system_alarmowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawel.system_alarmowy.model.Measurement;
import pl.pawel.system_alarmowy.repository.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class MeasurementController {

    private MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementController(MeasurementRepository measurementRepository)
    {
        this.measurementRepository = measurementRepository;
    }

    @GetMapping
    public String home(Model model)
    {
        Measurement measurement = measurementRepository.findFirstByDateTimeBeforeOrderByDateTimeDesc(LocalDateTime.now());
        model.addAttribute("measurement",measurement);
        return "home";
    }

    @GetMapping("/hour")
    public String getOneHour(Model model){
        List<Measurement> hourMeasurement = measurementRepository.findAllByDateTimeBetween(LocalDateTime.now().minusHours(1L),LocalDateTime.now());
//        hourMeasurement.forEach(System.out::println);
        model.addAttribute("measurements", hourMeasurement);
        return "hour";
    }

    @GetMapping("/day")
    public String getOneDay(Model model)
    {
        List<Measurement> dayMeasurement = measurementRepository.findAllByDateTimeBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now());
        model.addAttribute("measurements", dayMeasurement);
        return "day";
    }
}

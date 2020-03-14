package pl.pawel.system_alarmowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawel.system_alarmowy.model.Measurement;
import pl.pawel.system_alarmowy.repository.MeasurementRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        model.addAttribute("measurements", hourMeasurement);
        return "hour";
    }

    @GetMapping("/threeHour")
    public String getThreeHour(Model model){
        List<Measurement> hourMeasurement = measurementRepository.findAllByDateTimeBetween(LocalDateTime.now().minusHours(3L),LocalDateTime.now());
        model.addAttribute("measurements", hourMeasurement);
        return "threeH";
    }

    @GetMapping("/day")
    public String getOneDay(Model model)
    {
        List<Measurement> dayMeasurement = measurementRepository.findAllByDateTimeBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now());
        String pattern = "yyyy-mm-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("pl", "PL"));
        model.addAttribute("measurements", dayMeasurement);
        return "day";
    }
}

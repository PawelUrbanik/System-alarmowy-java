package pl.pawel.system_alarmowy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measurement implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value1;
    private int value2;
    private int value3;
    private LocalDateTime dateTime;

    public Measurement(int value1, int value2, int value3)
    {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.dateTime = LocalDateTime.now();
    }
}

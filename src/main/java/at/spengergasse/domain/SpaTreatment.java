package at.spengergasse.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "spaTreatmentId", callSuper = false)
@Entity
public class SpaTreatment {
    @Id
    private Long    spaTreatmentId;
    private LocalDate spaTreatmentDate;
    private String customerName;
    private String  treatmentRoom;
    private Double price;
    private Integer treatmentDurationMinutes;
    private Boolean extraServiceIncluded;

    private static final AtomicLong sequence = new AtomicLong(1000);
    private static final String[] treatmentRooms = {"Lotus Room", "Harmony Room", "Zen Room", "Crystal Room"};


    public SpaTreatment() {
        //setSpaTreatmentId();
        //setSpaTreatmentDate(LocalDate.now());
        //setCustomerName("Unknown");
        //setTreatmentRoom("Zen Room");
        //setPrice(60.0);
        //setTreatmentDurationMinutes(60);
        //setExtraServiceIncluded(false);
    }

    public SpaTreatment(LocalDate spaTreatmentDate, String customerName, String treatmentRoom, Double price, Integer treatmentDurationMinutes, Boolean extraServiceIncluded) {
        setSpaTreatmentId();
        setSpaTreatmentDate (spaTreatmentDate);
        setCustomerName (customerName);
        setTreatmentRoom (treatmentRoom);
        setPrice (price);
        setTreatmentDurationMinutes (treatmentDurationMinutes);
        setExtraServiceIncluded (extraServiceIncluded);
    }



    public SpaTreatment(Long spaTreatmentId, LocalDate spaTreatmentDate, String customerName, String treatmentRoom, Double price, Integer treatmentDurationMinutes, Boolean extraServiceIncluded) {
        setSpaTreatmentId(spaTreatmentId);
        setSpaTreatmentDate (spaTreatmentDate);
        setCustomerName (customerName);
        setTreatmentRoom (treatmentRoom);
        setPrice (price);
        setTreatmentDurationMinutes (treatmentDurationMinutes);
        setExtraServiceIncluded (extraServiceIncluded);
    }

    private void setSpaTreatmentId() {
        spaTreatmentId = sequence.getAndIncrement();
    }
    public void setPrice (Double price){
        if(price<20)
            throw new SpaTreatmentsException("Price is to low!");
        if(price>100)
            throw new SpaTreatmentsException(("Price is to high!"));
        this.price = price;
    }

    public void setTreatmentRooms(String treatmentRoom){
       if(! Arrays.asList(treatmentRooms).contains(treatmentRoom))
           throw  new SpaTreatmentsException("Unknown Room!");
       this.treatmentRoom = treatmentRoom;
    }

    @Override
    public SpaTreatment clone(){
        return new SpaTreatment(spaTreatmentId,spaTreatmentDate, customerName, treatmentRoom,price, treatmentDurationMinutes, extraServiceIncluded);
    }
}

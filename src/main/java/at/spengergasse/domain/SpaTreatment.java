package at.spengergasse.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
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
    @NotNull (message = "Treatment date is required!")
    @PastOrPresent(message = "Treatments can only be in the past or present!")
    private LocalDate spaTreatmentDate;
    @NotBlank(message = "Customer name is required!")
    @Size(min=2, max=50, message = "Wrong name")
    private String customerName;
    @NotNull (message = "Treatment room is required!")
    @Pattern(regexp = "Lotus Room|Harmony Room|Zen Room|Crystal Room",
            message = "The room must be Lotus-, Harmony-, Zen- or Crystal room")
    private String  treatmentRoom;
    @NotNull (message = "Price is required!")
    @DecimalMin(value = "20.0", message = "Minimum price €20")
    @DecimalMax(value = "100.0", message = "Minimum price €100")
    private Double price;
    @NotNull (message = "Treatment duration is required!")
    private Integer treatmentDurationMinutes;
    @NotNull (message = "If extra serices are ordered is required!")
    @Min(value = 20, message = "Minimal duration is 20mins")
    @Max(value = 120, message = "Maximal duration is 120mins")
    private Boolean extraServiceIncluded;

    private static final AtomicLong sequence = new AtomicLong(1000);


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
        this.treatmentRoom = treatmentRoom;
    }

    @Override
    public SpaTreatment clone(){
        return new SpaTreatment(spaTreatmentId,spaTreatmentDate, customerName, treatmentRoom,price, treatmentDurationMinutes, extraServiceIncluded);
    }
}

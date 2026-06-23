package at.spengergasse.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class spaTreatmentsTest {

    @Test
    void testWrongPrice() {
        SpaTreatments a = null;
        try {
            a = new SpaTreatments(LocalDate.now(), "Customer1", "Zen", 12.2, 50, true);
            System.out.println(a);
            assertEquals(1,0);
        }
        catch (SpaTreatmentsException e){
            System.out.println(e.getMessage());
            assertEquals(1,1);
        }
    }

    @Test
    void testToString() {
        SpaTreatments a = null;
        try {
            a = new SpaTreatments(LocalDate.now(), "Customer1", "Zen", 25.5, 50, true);
            System.out.println(a);
            System.out.println(a.getSpaTreatmentDate());
            System.out.println(a.getCustomerName());
            a.setCustomerName("Susi");
            System.out.println(a.getCustomerName());
        }
        catch (SpaTreatmentsException e){
            System.out.println(e.getMessage());
        }
    }
}




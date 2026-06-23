package at.spengergasse.service;

import at.spengergasse.domain.SpaTreatment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaTreatmentsServiceTest {

    @Test
    void testToString() {
        SpaTreatmentsService spenger = new SpaTreatmentsService();
        spenger.fillTestData();
        System.out.println(spenger);
    }
}
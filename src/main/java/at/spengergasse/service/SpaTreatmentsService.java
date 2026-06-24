package at.spengergasse.service;

import at.spengergasse.domain.SpaTreatment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class SpaTreatmentsService {

    private ArrayList<SpaTreatment> spaTreatments;

    public SpaTreatmentsService(){
        spaTreatments = new ArrayList<>(1000);
        fillTestData();
    }
    @Override

    public String toString(){
        String erg = "";

        for (SpaTreatment t : spaTreatments){
            erg += t.toString() + "\n";
        }
        return erg;
    }

    /* das gleiche wie:
    public String toString(){
        return spaTreatments.stream()
                .map(d->d.toString())
                .collect(Collectors.joining("\n"));
    }*/

    public void fillTestData(){
        spaTreatments.add(new SpaTreatment(1L, LocalDate.of(2026, 1, 5), "Anna Müller", "Lotus Room", 35.0, 30, false));
        spaTreatments.add(new SpaTreatment(2L, LocalDate.of(2026, 1, 6), "Lukas Steiner", "Harmony Room", 50.0, 45, true));
        spaTreatments.add(new SpaTreatment(3L, LocalDate.of(2026, 1, 7), "Maria Novak", "Zen Room", 55.0, 40, false));
        spaTreatments.add(new SpaTreatment(4L, LocalDate.of(2026, 1, 8), "Julia Weber", "Crystal Room", 45.0, 25, true));
        spaTreatments.add(new SpaTreatment(5L, LocalDate.of(2026, 1, 9), "David König", "Lotus Room", 95.0, 60, false));
        spaTreatments.add(new SpaTreatment(6L, LocalDate.of(2026, 1, 10), "Sophie Bauer", "Harmony Room", 25.0, 20, true));
        spaTreatments.add(new SpaTreatment(7L, LocalDate.of(2026, 1, 11), "Markus Leitner", "Zen Room", 60.0, 60, false));
        spaTreatments.add(new SpaTreatment(8L, LocalDate.of(2026, 1, 12), "Elena Petrova", "Crystal Room", 85.0, 90, true));
        spaTreatments.add(new SpaTreatment(9L, LocalDate.of(2026, 1, 13), "Thomas Berger", "Lotus Room", 90.0, 75, false));
        spaTreatments.add(new SpaTreatment(10L, LocalDate.of(2026, 1, 14), "Nina Schwarz", "Harmony Room", 75.0, 45, true));
        spaTreatments.add(new SpaTreatment(11L, LocalDate.of(2026, 1, 15), "Laura Schmidt", "Zen Room", 90.0, 120, true));
        spaTreatments.add(new SpaTreatment(12L, LocalDate.of(2026, 1, 16), "Peter Huber", "Crystal Room", 45.0, 40, false));
        spaTreatments.add(new SpaTreatment(13L, LocalDate.of(2026, 1, 17), "Sarah Klein", "Lotus Room", 35.0, 30, true));
        spaTreatments.add(new SpaTreatment(14L, LocalDate.of(2026, 1, 18), "Jonas Mayer", "Harmony Room", 50.0, 45, false));
        spaTreatments.add(new SpaTreatment(15L, LocalDate.of(2026, 1, 19), "Mila Ivanova", "Zen Room", 55.0, 40, true));
        spaTreatments.add(new SpaTreatment(16L, LocalDate.of(2026, 1, 20), "Daniel Hofer", "Crystal Room", 45.0, 25, false));
        spaTreatments.add(new SpaTreatment(17L, LocalDate.of(2026, 1, 21), "Emma Gruber", "Lotus Room", 95.0, 60, true));
        spaTreatments.add(new SpaTreatment(18L, LocalDate.of(2026, 1, 22), "Leon Steiner", "Harmony Room", 25.0, 20, false));
        spaTreatments.add(new SpaTreatment(19L, LocalDate.of(2026, 1, 23), "Hannah Wolf", "Zen Room", 60.0, 60, true));
        spaTreatments.add(new SpaTreatment(20L, LocalDate.of(2026, 1, 24), "Sebastian Kurz", "Crystal Room", 85.0, 90, false));
        spaTreatments.add(new SpaTreatment(21L, LocalDate.of(2026, 1, 25), "Clara Weiss", "Lotus Room", 90.0, 75, true));
        spaTreatments.add(new SpaTreatment(22L, LocalDate.of(2026, 1, 26), "Michael Braun", "Harmony Room", 75.0, 45, false));
        spaTreatments.add(new SpaTreatment(23L, LocalDate.of(2026, 1, 27), "Isabella Fuchs", "Zen Room", 87.0, 120, true));
        spaTreatments.add(new SpaTreatment(24L, LocalDate.of(2026, 1, 28), "Oliver Hartmann", "Crystal Room", 45.0, 40, true));
        spaTreatments.add(new SpaTreatment(25L, LocalDate.of(2026, 1, 29), "Lara Fischer", "Lotus Room", 35.0, 30, false));
        spaTreatments.add(new SpaTreatment(26L, LocalDate.of(2026, 1, 30), "Felix Berger", "Harmony Room", 50.0, 45, true));
        spaTreatments.add(new SpaTreatment(27L, LocalDate.of(2026, 1, 31), "Sofia Dimitrova", "Zen Room", 55.0, 40, false));
        spaTreatments.add(new SpaTreatment(28L, LocalDate.of(2026, 2, 1), "Paul Steiner", "Crystal Room", 45.0, 25, true));
        spaTreatments.add(new SpaTreatment(29L, LocalDate.of(2026, 2, 2), "Mira Novak", "Lotus Room", 95.0, 60, false));
        spaTreatments.add(new SpaTreatment(30L, LocalDate.of(2026, 2, 3), "Alex Müller", "Harmony Room", 25.0, 20, true));

    }

    public ArrayList<SpaTreatment> findAll() {
        ArrayList<SpaTreatment> clone;
        clone = new ArrayList<>(spaTreatments);
        return clone;
    }

}

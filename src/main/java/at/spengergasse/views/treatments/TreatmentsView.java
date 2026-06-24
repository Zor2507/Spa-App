package at.spengergasse.views.treatments;

import at.spengergasse.domain.SpaTreatment;
import at.spengergasse.domain.SpaTreatmentsException;
import at.spengergasse.service.SpaTreatmentsService;
import at.spengergasse.views.home.HomeView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import tools.jackson.databind.ser.std.DelegatingSerializer;


import java.time.LocalDate;


@PageTitle("Treatments")
@Route("treatments")
@Menu(order = 1, icon = LineAwesomeIconUrl.SPA_SOLID)
public class TreatmentsView extends VerticalLayout {

    private final Button buttonRemoveAll = new Button("Remove all");
    private final Button buttonAdd10Treatments = new Button("Add 10 treatments");
    private final Button buttonAdd1Euro = new Button ("Add 1Euro");
    private final Button buttonRemoveExtraTreatment = new Button ("Without extra treatment");
    private final Button buttonAddWrongPrice = new Button ("Add wrong price");
    private final Grid<SpaTreatment> grid = new Grid<>(SpaTreatment.class,false);
    private final SpaTreatmentsService spaTreatmentsService;


    public TreatmentsView(@Autowired SpaTreatmentsService spaTreatmentsService) {
        this.spaTreatmentsService = spaTreatmentsService;

        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();

        buttonRemoveAll.addClickListener((ClickEvent<Button> event) -> removeAll());
        buttonAdd10Treatments.addClickListener((ClickEvent<Button> event) -> add10treatments());
        buttonAdd1Euro.addClickListener((ClickEvent<Button> event) -> add1Euro());
        buttonRemoveExtraTreatment.addClickListener((ClickEvent<Button> event) -> removeExtras());
        buttonAddWrongPrice.addClickListener((ClickEvent<Button> event) -> addWrongTreatment());
        add(new HorizontalLayout(buttonRemoveAll, buttonAdd10Treatments,buttonAdd1Euro, buttonRemoveExtraTreatment, buttonAddWrongPrice));


        grid.addColumn(treatment -> treatment.getSpaTreatmentId())
                .setHeader("Treatment ID")
                .setSortable(true);
        grid.addColumn(treatment -> treatment.getSpaTreatmentDate())
                .setHeader("Treatment Date")
                .setSortable(true);
        grid.addColumn(treatment -> treatment.getCustomerName())
                .setHeader("Treatment Date")
                .setSortable(true);

        Image l = new Image("icons/blume.png", "Flower picture");
        l.setWidth("32px");
        HorizontalLayout headerType = new HorizontalLayout(l, new Span("Room"));
        grid.addColumn(treatment -> treatment.getTreatmentRoom())
                .setHeader(headerType)
                .setSortable(true);

        grid.addColumn(treatment -> treatment.getPrice())
                .setHeader("Treatment Price €")
                .setSortable(true);
        grid.addColumn(treatment -> treatment.getTreatmentDurationMinutes())
                .setHeader("Treatment Duration min")
                .setSortable(true);
        grid.addColumn(treatment -> {
                if (treatment.getExtraServiceIncluded()==true)
                    return "with Extras";
                else
                    return "without Extras";
            })
            .setHeader("Extra Services")
            .setSortable(true);

        grid.addComponentColumn(treatment -> {
                Checkbox extras = new Checkbox(treatment.getExtraServiceIncluded());
                extras.setReadOnly(true);
                return extras;
            })
        .setHeader("Extra Services")
        .setSortable(true);

        grid.addComponentColumn(treatment -> {
                    Button delete = new Button("Delete");
                    delete.addClickListener(e ->remove1Treatment(treatment.getSpaTreatmentId()));
                    return delete;

            })
                .setHeader("Action")
                .setSortable(false);

        grid.addComponentColumn( treatment -> {
                Button add1Treatment = new Button("Add 1 Treatment");
                add1Treatment.addClickListener((e ->add1Treatment(treatment.getSpaTreatmentId())));;
            return add1Treatment;

             })
                .setHeader("Action")
                .setSortable(false);


        add(grid);
        reload();
    }

    private void add1Treatment(Long spaTreatmentId) {
        try {
            spaTreatmentsService.add1Treatment(spaTreatmentId);
            reload();
        }
        catch (SpaTreatmentsException e) {
            Notification.show(e.getMessage());
        }

    }

    private void remove1Treatment(Long spaTreatmentId) {
        try{
            spaTreatmentsService.remove1Treatment(spaTreatmentId);
            reload();
        }
        catch (SpaTreatmentsException e) {
            Notification.show(e.getMessage());
            reload();
        }
    }

    private void addWrongTreatment(){
       try{
           spaTreatmentsService.addWrongTreatment();
           reload();
       }
        catch (SpaTreatmentsException e) {
           Notification.show(e.getMessage());
            reload();
        }
    }
    private void removeExtras(){
        try {
            spaTreatmentsService.removeExtraT();
            reload();
        }
        catch (SpaTreatmentsException e) {
            Notification.show(e.getMessage());
            reload();
        }

    }
    private void add1Euro(){
        try {
            spaTreatmentsService.add1Euro();
            reload();
        }
        catch (SpaTreatmentsException e) {
            Notification.show(e.getMessage());
            reload();
        }

    }
    private void add10treatments(){
       try{
           spaTreatmentsService.add10T();

        buttonRemoveAll.setEnabled(true);
        buttonAdd1Euro.setEnabled(true);
        buttonRemoveExtraTreatment.setEnabled(true);
        reload();
       }
       catch (SpaTreatmentsException e) {
           Notification.show(e.getMessage());
           reload();
       }
    }
    public void removeAll(){
       try{
           spaTreatmentsService.removeAllT();
           buttonRemoveAll.setEnabled(false);
           buttonAdd1Euro.setEnabled(false);
           buttonRemoveExtraTreatment.setEnabled(false);
           reload();
       }
       catch (SpaTreatmentsException e) {
           Notification.show(e.getMessage());
           reload();
       }

    }


    private void reload(){
        grid.setItems(spaTreatmentsService.findAll());
    }

}

package at.spengergasse.views.treatments;

import at.spengergasse.domain.SpaTreatment;
import at.spengergasse.domain.SpaTreatmentsException;
import at.spengergasse.service.SpaTreatmentsService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Treatments")
@Route("treatments")
@Menu(order = 1, icon = LineAwesomeIconUrl.SPA_SOLID)

public class TreatmentsView extends VerticalLayout {

    private final Button buttonRemoveAll = new Button("Remove all");
    private final Button buttonAdd10Treatments = new Button("Add 10 treatments");
    private final Button buttonAdd1Euro = new Button ("Add 1Euro");
    private final Button buttonRemoveExtraTreatment = new Button ("Without extra treatment");
    private final Button buttonAddWrongPrice = new Button ("Add wrong price");
    private final Button buttonAdd1Treatment = new Button ("Add treatment");
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
        buttonAdd1Treatment.addClickListener((ClickEvent<Button> event) -> addEditTreatment(null));
        add(new HorizontalLayout(buttonRemoveAll, buttonAdd10Treatments,buttonAdd1Euro, buttonRemoveExtraTreatment, buttonAddWrongPrice, buttonAdd1Treatment));


        grid.addColumn(treatment -> treatment.getSpaTreatmentId())
                .setHeader("Treatment ID")
                .setSortable(true);
        grid.addColumn(treatment -> treatment.getSpaTreatmentDate())
                .setHeader("Treatment Date")
                .setSortable(true);
        grid.addColumn(treatment -> treatment.getCustomerName())
                .setHeader("Customer Name")
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
                    Button delete = new Button("Delete treatment");
                    delete.addClickListener(e ->remove1Treatment(treatment.getSpaTreatmentId()));
                    return delete;

            })
                .setHeader("Action")
                .setSortable(false);

        grid.addComponentColumn( treatment -> {
                Button add1Treatment = new Button("Extend treatment");
                add1Treatment.addClickListener((e -> extendTreatment(treatment.getSpaTreatmentId())));;
            return add1Treatment;

             })
                .setHeader("Action")
                .setSortable(false);

        grid.addComponentColumn(treatment -> {
                Button editTreatment = new Button("Edit treatment");
                editTreatment.addClickListener(e-> addEditTreatment(treatment));
                return editTreatment;
        })
                .setHeader("Action")
                .setSortable(false);

        add(grid);
        reload();
    }

    private void addEditTreatment(SpaTreatment existingTreatment) {
       Dialog dialog;
       SpaTreatment treatment;

       dialog = new Dialog();

       if(existingTreatment==null) {
           dialog.setHeaderTitle("Add new treatment");
           treatment = new SpaTreatment();
       }

       else {
           dialog.setHeaderTitle("Edit treatment");
           treatment = existingTreatment;
       }

       TextField spaTreatmentId= new TextField("Treatment ID");
       DatePicker spaTreatmentDate = new DatePicker("Treamtent date");
       TextField customerName = new TextField("Customer name");
       ComboBox treatmentRoom = new ComboBox ("Treatment room");
       treatmentRoom.setItems("Lotus Room","Harmony Room","Zen Room","Crystal Room");
       NumberField price = new NumberField("Price");
       IntegerField treatmentDurationMinutes = new IntegerField("Treatment duration");
       Checkbox extraServiceIncluded = new Checkbox("Extras");

       BeanValidationBinder<SpaTreatment>binder = new BeanValidationBinder<>(SpaTreatment.class);
       binder.forField(spaTreatmentDate)
               .bind("spaTreatmentDate");
       binder.forField(customerName)
               .bind("customerName");
       binder.forField(treatmentRoom)
                .bind("treatmentRoom");
       binder.forField(price)
               .bind("price");
       binder.forField(treatmentDurationMinutes)
               .bind("treatmentDurationMinutes");
       binder.forField(extraServiceIncluded)
               .bind("extraServiceIncluded");


       binder.setBean(treatment);

       spaTreatmentId.setValue(""+treatment.getSpaTreatmentId());
       spaTreatmentId.setReadOnly(true);

       VerticalLayout formLayout = new VerticalLayout(
               spaTreatmentId,
               spaTreatmentDate,
               customerName,
               treatmentRoom,
               price,
               treatmentDurationMinutes,
               extraServiceIncluded

       );
       Button buttonOK = new Button ("OK");
       Button buttonCancel = new Button ("Cancel");

       buttonOK.addClickListener(event->{
           try{
               if(binder.validate().isOk()==true){
                   if(existingTreatment==null)
                       spaTreatmentsService.add1Treatment(treatment);
                   dialog.close();
                   reload();
                   if(existingTreatment==null)
                       Notification.show("New treatment added");
                   else
                       Notification.show("Existing treatment modified");
               }
               else{
                   Notification.show("Check your input!");
               }
           }
           catch (SpaTreatmentsException e){
               Notification.show(e.getMessage());
           }
       });
       buttonCancel.addClickListener(event->dialog.close());

       dialog.add(formLayout);
       dialog.getFooter().add(buttonOK,buttonCancel);
       dialog.open();
    }

    private void extendTreatment(Long spaTreatmentId) {
        try {
            spaTreatmentsService.addDuration(spaTreatmentId);
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

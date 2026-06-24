package at.spengergasse.views.treatments;

import at.spengergasse.domain.SpaTreatment;
import at.spengergasse.service.SpaTreatmentsService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Treatments")
@Route("treatments")
@Menu(order = 1, icon = LineAwesomeIconUrl.SPA_SOLID)
public class TreatmentsView extends VerticalLayout {

    private final Grid<SpaTreatment> grid = new Grid<>(SpaTreatment.class,true);
    private final SpaTreatmentsService spaTreatmentsService;


    public TreatmentsView(@Autowired SpaTreatmentsService spaTreatmentsService) {
        this.spaTreatmentsService = spaTreatmentsService;

        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();
        add(grid);
        reload();
    }
    private void reload(){
        grid.setItems(spaTreatmentsService.findAll());
    }

}

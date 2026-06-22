package at.spengergasse.views.prices;

import at.spengergasse.views.mashasspa.MashasSpaView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Prices")
@Route("Prices")
@Menu(order = 2, icon = LineAwesomeIconUrl.COINS_SOLID)

public class PricesView extends VerticalLayout {
    public PricesView() {
        setSpacing(false);

        VerticalLayout header = MashasSpaView.getHeader();

        H2 pricelist = new H2("Preice List");
        pricelist.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        FlexLayout treatments = new FlexLayout();
        VerticalLayout treatment1 =  getCard("Relax Massage", 35, 60, "Für warme Kräuterkompressen", 4);
        VerticalLayout treatment2 =  getCard("Aromaöl‑Massage", 50, 85, "Ein Duftöl nach Wahl um ", 3);
        VerticalLayout treatment3 =  getCard("Hot‑Stone‑Therapy", 55, 90, "Für warme Kräuterkompressen", 4);
        VerticalLayout treatment4 =  getCard("Hydra Facial", 35, 60, "Für warme Kräuterkompressen", 4);
        VerticalLayout treatment5 =  getCard("Premium Spa Ritual", 35, 60, "Für warme Kräuterkompressen", 4);
        VerticalLayout treatment6 =  getCard("Fußreflexzonen‑Massage", 35, 60, "Für warme Kräuterkompressen", 4);
        treatments.setWidthFull();
        treatments.setJustifyContentMode(JustifyContentMode.CENTER);
        treatments.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        treatments.add(treatment1, treatment2, treatment3, treatment4, treatment5, treatment6);

        Paragraph info = new Paragraph( "Inklusive Steuer");

        add (header, pricelist, treatments, info);


    }



    public VerticalLayout getCard(String treatmentName, double priceMini, double priceMaxi,String nameExtra, double priceExtra) {

    VerticalLayout treatment = new VerticalLayout();

    H2 treatmentNameH2 = new H2(treatmentName);
    Paragraph priceMiniP = new Paragraph("Price for 30min: " + priceMini + "€");
    Paragraph priceMaxiP = new Paragraph("Price for 60min: " + priceMaxi + "€");
    Paragraph priceExtraP = new Paragraph(nameExtra +  priceExtra + "€");
    treatment.add(treatmentNameH2, priceMiniP, priceMaxiP, priceExtraP);

    treatment.setWidth("350px");
    treatment.setPadding(true);
    treatment.setSpacing(false);
    treatment.getStyle()
            .set("border","1px solid lightgray")
            .set("border-radius", "10px")
            .set("margin","10px");
    treatment.add (treatmentNameH2, priceMiniP,priceMaxiP,priceExtraP);

    return treatment;
    }
}


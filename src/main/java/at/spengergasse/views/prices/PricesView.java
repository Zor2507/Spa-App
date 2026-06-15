package at.spengergasse.views.prices;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
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

        setAlignItems(Alignment.CENTER);

        H1 companyName = new H1("Masha's Spa");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin", "0");

        H2 subName = new H2 (" the place to relax");
        subName.getStyle()
                .set("margin","0")
                .set("color", "gray");

        H2 pricelist = new H2("Preice List");
        pricelist.getStyle()
                .set("margin","0")
                .set("color", "gray");

        H2 Treatment1 = new H2 ("Relax Massage");
        Paragraph priceMini1 = new Paragraph("Price for 30min");
        Paragraph priceMaxi1 = new Paragraph("Price for 60min");
        Paragraph priceExtra1 = new Paragraph("Für warme Kräuterkompressen kommen 4 € dazu");

        H2 Treatment2 = new H2 ("Aromaöl‑Massage");
        Paragraph priceMini2 = new Paragraph("Price for 45min");
        Paragraph priceMaxi2 = new Paragraph("Price for 900min");
        Paragraph priceExtra2 = new Paragraph("Ein Duftöl nach Wahl kann für 3 € hinzugefügt werden");

        H2 Treatment3 = new H2 ("Hot‑Stone‑Therapy");
        Paragraph priceMini3 = new Paragraph("Price for 40min");
        Paragraph priceMaxi3 = new Paragraph("Price for 75min");
        Paragraph priceExtra3 = new Paragraph("Ein kurzer Nacken‑Wärmeboost kostet zusätzlich 5 €");

        H2 Treatment4 = new H2 ("Hydra Facial");
        Paragraph priceMini4 = new Paragraph("Price for 25min");
        Paragraph priceMaxi4 = new Paragraph("Price for 45min");
        Paragraph priceExtra4 = new Paragraph("Mit Hyaluron‑Serum‑Finish +10 €");

        H2 Treatment5 = new H2 ("Premium Spa Ritual");
        Paragraph priceMini5 = new Paragraph("Price for 60min");
        Paragraph priceMaxi5 = new Paragraph("Price for 120min");
        Paragraph priceExtra5 = new Paragraph("Luxus‑Aromakerzen während der Behandlung +6 €");

        H2 Treatment6 = new H2 ("Fußreflexzonen‑Massage");
        Paragraph priceMini6 = new Paragraph("Price for 20min");
        Paragraph priceMaxi6 = new Paragraph("Price for 40min");
        Paragraph priceExtra6 = new Paragraph("Ein pflegendes Fußpeeling kann für 5 € ergänzt werden");

        add(companyName, subName, pricelist,
                Treatment1, priceMini1, priceMaxi1, priceExtra1,
                Treatment2, priceMini2, priceMaxi2, priceExtra2,
                Treatment3, priceMini3, priceMaxi3, priceExtra3,
                Treatment4, priceMini4, priceMaxi4, priceExtra4,
                Treatment5, priceMini5, priceMaxi5, priceExtra5,
                Treatment6, priceMini6, priceMaxi6, priceExtra6);
    }
}

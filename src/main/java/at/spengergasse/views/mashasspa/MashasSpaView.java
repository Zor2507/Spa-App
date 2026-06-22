package at.spengergasse.views.mashasspa;

import com.vaadin.copilot.shaded.reactor.netty.http.internal.Http3;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Masha's Spa")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class MashasSpaView extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public MashasSpaView() {
        setSpacing(false);

        setAlignItems(Alignment.CENTER);

        VerticalLayout header = getHeader();

        Paragraph line1 = new Paragraph ("At Masha’s Spa, we believe that true relaxation begins the moment you step through our doors. Our space is designed to help you disconnect from the noise of everyday life and reconnect with your inner calm. With a warm atmosphere, soothing scents, and a team dedicated to your well‑being, every visit becomes a peaceful escape.");
        line1.setWidth("500px");
        line1.getStyle()
                .set("font-size","22px")
                .set("line-hight","1.6")
                .set("text-align","left");


        Paragraph line2 = new Paragraph("We offer a carefully curated selection of treatments, including rejuvenating massages, revitalizing facials, and holistic wellness rituals. Each service is tailored to your individual needs, ensuring that your body and mind receive the care they deserve. Whether you’re seeking deep relaxation or a refreshing boost of energy, our therapists are here to guide you toward balance and harmony.");
        line2.setWidth("500px");
        line2.getStyle()
                .set("font-size","22px")
                .set("line-hight","1.6")
                .set("text-align","left");

        Paragraph line3 = new Paragraph ("At Masha’s Spa, our philosophy is simple: wellness should be both accessible and deeply personal. We combine high‑quality products with mindful techniques to create an experience that lingers long after your visit. Treat yourself to a moment of serenity — you deserve it. We look forward to welcoming you soon.");
        line3.setWidth("500px");
        line3.getStyle()
                .set("font-size","22px")
                .set("line-hight","1.6")
                .set("text-align","left");

        H3 name = new H3("Masha's Spa GmbH");
        H3 street = new H3 ("Spengergasse 20");
        H3 city = new H3 ("1050 Wien");

        add(header,line1,line2,line3,name, street, city);
    }

    public static VerticalLayout getHeader() {
        VerticalLayout header;

        header= new VerticalLayout();
        H1 companyName= new H1 ("Masha's Spa");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "6rem")
                .set("margin","0");

        H2 subName = new H2 (" the place to relax");
        subName.getStyle()
                .set("margin","0")
                .set("color", "gray");

        header.add(companyName,subName);

        return header;

    }

}

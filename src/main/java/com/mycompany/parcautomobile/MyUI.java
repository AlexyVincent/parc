package com.mycompany.parcautomobile;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.client.widget.grid.selection.SelectionEvent;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/**
 * classe main, instancie vaadin
 *
 * @author vincent
 */
@Theme("mytheme")
@Widgetset("com.mycompany.parcautomobile.MyAppWidgetset")
public class MyUI extends UI {

    private static Grid contactList = new Grid();
    private Grid grillev = new Grid();
    private Grid grillemv = new Grid();
    @Override
    protected void init(VaadinRequest vaadinrequest) {

        configureComponents();
        buildLayout();// configuration des composants
        //  construction de la vue
    }

    private void configureComponents() {
        Init.getInstance();


        contactList.setContainerDataSource(Vehicule.getVehicules());

        //contactList.setColumnOrder("marque", "modele", "prix","visiteur");
        contactList.setSizeFull();

        grillemv.setContainerDataSource(MarqueVehicule.getMarqueVehicule());
        grillemv.setSizeFull();

        grillev.setContainerDataSource(Visiteur.getPersonnes());
        grillev.setSizeFull();
    }

    private void buildLayout() {

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        // ajouts de composants
        layout.addComponent(new Label(" Parc de véhicule :"));
        layout.addComponent(contactList);

        layout.addComponent(new Label(" Grille Marque Vehicule :"));
        layout.addComponent(grillemv);

        layout.addComponent(new Label(" Grille Visiteur :"));
        layout.addComponent(grillev);



        setContent(layout);
    }

    /**
     * MyUIServlet
     */
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

package HearthStats.controller;

import HearthStats.model.factory.ArenaAbs;
import HearthStats.model.factory.ArenaFactory;
import HearthStats.model.factory.Areny;
import HearthStats.model.Captcha;
import HearthStats.model.pakiety.Pakiety;
import HearthStats.model.pakiety.chain.Obsluga;
import HearthStats.view.MainApp;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by VuYeK on 03.11.2015.
 */
public class Controller implements Initializable{


    private Areny areny = Areny.getInstance();
    private Pakiety pakiety = Pakiety.getInstance();

    Obsluga chainOfObsluga = pakiety.getChainOfObsluga();

    private Captcha captcha = new Captcha();
    private String captchaTemp1, captchaTemp2;
    private int[] pakieciki = new int[3];
    private ObservableList<ArenaAbs> arenki;
    private ArrayList<ArenaAbs> arenaAbsList = new ArrayList<>();

    @FXML private TextField etWygrane, etCaptcha;
    @FXML private Button btnDodaj;
    @FXML private Button btnUsun;
    @FXML private Button btnResetPakiety, btnResetLeg, btnResetEpik, btnCaptchaOK, btnDodajPakiet;
    @FXML private TableView<ArenaAbs> tableAreny;
    @FXML private TableColumn<ArenaAbs,String> colKlasa;
    @FXML private TableColumn<ArenaAbs,Integer> colWygrane;
    @FXML private Text txtSrednia, txtPakiety, txtLegenda, txtEpik, txtCaptcha;
    @FXML private ChoiceBox<String> choiceKlasa, choicePakiety;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /**
         * Ladowanie do ChoiceBox
         */
        List<String> list = new ArrayList<>();
        list.add("Warrior");list.add("Shaman");list.add("Rogue");list.add("Paladin");list.add("Hunter");list.add("Druid");list.add("Warlock");list.add("Mage");list.add("Priest");
        ObservableList obList = FXCollections.observableList(list);

        choiceKlasa.getItems().clear();
        choiceKlasa.setItems(obList);
        choiceKlasa.getSelectionModel().selectFirst();


        List<String> list2 = new ArrayList<>();
        list2.add("Klasyczne");list2.add("GvG");list2.add("TGT");
        ObservableList obList2 = FXCollections.observableList(list2);

        choicePakiety.getItems().clear();
        choicePakiety.setItems(obList2);
        choicePakiety.getSelectionModel().selectFirst();

        choicePakiety.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                //System.out.println(choicePakiety.getItems().get((Integer) number2));
                setPakiety(choicePakiety.getItems().get((Integer) number2));
            }
        });


        //ustawienie TableView
        colKlasa.setCellValueFactory(new PropertyValueFactory<ArenaAbs, String>("klasa"));
        colWygrane.setCellValueFactory(new PropertyValueFactory<ArenaAbs, Integer>("wygrane"));
        setArenasArray();
        setPakiety("Klasyczne");

        btnDodaj.disableProperty().bind(Bindings.isEmpty(etWygrane.textProperty()));
        if(tableAreny.getSelectionModel().getSelectedIndex()==-1) btnUsun.setDisable(true);

        setButtons(true);
        setCaptcha();
    }





    private void setArenasArray() {
        arenaAbsList = areny.wyswietl_areny();
        int suma=0;
        int ilosc=0;
        for(ArenaAbs a : arenaAbsList)
        {
            suma+=a.getWygrane();
            ilosc++;
        }
        double srednia = (double)suma/(double)ilosc;
        srednia*=10;
        srednia = Math.round(srednia);
        srednia/=10;

        txtSrednia.setText(String.valueOf(srednia));
        arenki = FXCollections.observableArrayList(arenaAbsList);
        tableAreny.setItems(arenki);
        //tableAreny.getSelectionModel().select(0);
    }

    private void setCaptcha()
    {
        captchaTemp1=captcha.randomString(3);
        txtCaptcha.setText(captchaTemp1);

    }



    private void setButtons(boolean stan)
    {
        btnDodajPakiet.setDisable(stan);
        btnResetPakiety.setDisable(stan);
        btnResetLeg.setDisable(stan);
        btnResetEpik.setDisable(stan);
    }


    @FXML private void setPakiety(String rodzaj)
    {
        System.out.println(rodzaj);
        chainOfObsluga.obsluz(rodzaj, "wyswietl", pakiety);
        pakieciki = pakiety.tablica;
        txtPakiety.setText(String.valueOf(pakieciki[0]));
        txtLegenda.setText(String.valueOf(pakieciki[1]));
        if(pakieciki[1]==0) resetujLegende();
        txtEpik.setText(String.valueOf(pakieciki[2]));
        if(pakieciki[2]==0) resetujEpika();
    }


    @FXML private void sprawdzZaznaczenie()
    {
        if(tableAreny.getSelectionModel().getSelectedIndex()==-1) btnUsun.setDisable(true);
        else btnUsun.setDisable(false);
    }


    @FXML protected void resetujPakiety()
    {
        chainOfObsluga.obsluz(choicePakiety.getValue(), "resetPakiety", pakiety);
        chainOfObsluga.obsluz(choicePakiety.getValue(), "resetLegenda", pakiety);
        chainOfObsluga.obsluz(choicePakiety.getValue(), "resetEpik", pakiety);
        setPakiety(choicePakiety.getValue());
    }

    @FXML protected void resetujLegende()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText("GRATULACJE!");
        alert.setContentText("GRATULACJE LEGENDY !!!.");
        alert.showAndWait();

        chainOfObsluga.obsluz(choicePakiety.getValue(), "resetLegenda", pakiety);
        setPakiety(choicePakiety.getValue());
    }

    @FXML protected void resetujEpika()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText("GRATULACJE!");
        alert.setContentText("GRATULACJE EPIKA !!!.");
        alert.showAndWait();

        chainOfObsluga.obsluz(choicePakiety.getValue(), "resetEpik", pakiety);
        setPakiety(choicePakiety.getValue());
    }


    @FXML public void sprawdz_captche()
    {
        captchaTemp2=etCaptcha.getText();
        if(Objects.equals(captchaTemp2, captchaTemp1)) {
            setButtons(false);
            btnCaptchaOK.setDisable(true);
            etCaptcha.setDisable(true);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Zła captcha!");
            alert.setContentText("Źle przepisany kod Captcha.");
            alert.showAndWait();
        }
    }



    @FXML protected void pokazStatystyki() throws IOException {
        MainApp main = new MainApp();
        main.showStatCharts();
    }





    @FXML public void dodaj_arene_do_bazy() {
        ArrayList<String> atributes = new ArrayList<>();
        if(Integer.parseInt(etWygrane.getText())>12 || Integer.parseInt(etWygrane.getText())<0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText("Podaj właściwe dane!");
            alert.setContentText("Liczba zwycięstw nie może być większa niż 12 i mniejsza niż 0.");

            alert.showAndWait();
            return;
        }
        atributes.add(etWygrane.getText());
        atributes.add(choiceKlasa.getValue());

        ArenaAbs arenka;
        if(atributes.size()==0)return;
        if(atributes.size() != 0) { //nie wypelnione ZADNE pole.
            for (String string : atributes) {   //sprawdzam czy wypelnione WSZYSTKIE pola
                if (string.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Prosze wypelnic wszystkie pola");
                    return;
                }
            }
            arenka = ArenaFactory.getArena(Integer.valueOf(atributes.get(0)), atributes.get(1));

            areny.dodaj_arene(arenka);  //BD
        } else {
            JOptionPane.showMessageDialog(null, "Prosze wypelnic wszystkie pola");
            return;
        }
        setArenasArray();
        btnUsun.setDisable(true);
        //initialize(null, null);
    }




    @FXML protected void usun_arene_z_bazy() {
        final int selectedIdx = tableAreny.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            ArenaAbs arenaAbsDoUsuniecia = tableAreny.getSelectionModel().getSelectedItem();
            areny.usun_arene(arenaAbsDoUsuniecia);
        }

        setArenasArray();
        final int newSelectedIdx = (selectedIdx == tableAreny.getItems().size()) ? selectedIdx - 1 : selectedIdx;
        tableAreny.getSelectionModel().select(newSelectedIdx);
    }


    @FXML protected void dodaj_pakiet_do_bazy() {
        chainOfObsluga.obsluz(choicePakiety.getValue(), "dodaj", pakiety);
        setPakiety(choicePakiety.getValue());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import Utils.MyDB;

/**
 *
 * @author lenovo
 */
public class STAT {
    private Connection con;
    private Statement ste;

    public STAT() {
        con = MyDB.getInstance().getConx();

    }

    public ObservableList<PieChart.Data> Stats() {
        String requete = "SELECT date, COUNT(*) FROM reservation GROUP BY date";
        try {
            Statement st2 = MyDB.getInstance().getConx().createStatement();

            ResultSet rs = st2.executeQuery(requete);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            while (rs.next()) {
                String date = rs.getString(1);
                int count = rs.getInt(2);
                String dateInLetters = convertDateToLetters(date); // conversion de la date en lettres
                PieChart.Data data = new PieChart.Data(dateInLetters + " (" + "Nb réservation=" + count + ")", count);
                Tooltip tooltip = new Tooltip(dateInLetters + ": " + count + " réservations"); // création du Tooltip
                Tooltip.install(data.getNode(), tooltip); // ajout du Tooltip à la donnée
                pieChartData.add(data);
            }

            return pieChartData;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    private String convertDateToLetters(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = df.parse(date);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        DateFormat frenchDf = new SimpleDateFormat("dd MMMM yyyy", new Locale("fr"));
        return frenchDf.format(parsedDate);
    }

}




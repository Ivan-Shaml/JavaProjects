package dev.ivanshamliev.desktopFuelRest.gui.panel.gasStationPanel;

import dev.ivanshamliev.desktopFuelRest.dtos.City;
import dev.ivanshamliev.desktopFuelRest.dtos.FuelRead;
import dev.ivanshamliev.desktopFuelRest.dtos.FuelReadDto;
import dev.ivanshamliev.desktopFuelRest.dtos.GasStationRead;
import dev.ivanshamliev.desktopFuelRest.exception.NotFoundException;
import dev.ivanshamliev.desktopFuelRest.gui.fuelPriceWindow.FuelPriceHistoryWindow;
import dev.ivanshamliev.desktopFuelRest.httpClient.FuelApiGetMethodsContract;
import dev.ivanshamliev.desktopFuelRest.httpClient.RestHttpClient;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GasStationPanel extends JFrame {

    private Integer selectedCityId;
    private Integer selectedStationId;

    private FuelReadDto fuelReadDto;
    private final JPanel upPanel = new JPanel();
    private final JPanel midPanel = new JPanel();
    private final JPanel downPanel = new JPanel();

    private final JLabel cityTFL = new JLabel("Choose city: ");
    private final JLabel gasStationTFL = new JLabel("Choose Gas Station: ");

    private final JButton chooseGasStationBtn = new JButton("Choose Gas Station");
    private final JButton chooseCityBtn = new JButton("Choose City");
    private final JButton clearSelectionBtn = new JButton("Clear");
    private final JButton refreshBtn = new JButton("Refresh data");

    private final JTextField searchCityTF = new JTextField();
    private final JTextField searchGasStationTF = new JTextField();

    private final JTable table = new JTable();
    private final JScrollPane scrollTablePane = new JScrollPane(table);

    private final JComboBox<City> cityComboBox = new JComboBox<City>();
    ArrayList<City> cityList = new ArrayList<City>();
    private final JComboBox<GasStationRead> gasStationComboBox = new JComboBox<GasStationRead>();
    ArrayList<GasStationRead> gasStationList = new ArrayList<GasStationRead>();

    private final FuelApiGetMethodsContract httpClient;

    public GasStationPanel() {

        this.httpClient = new RestHttpClient("http://localhost:8080/api/");

        this.setSize(800, 600);
        this.setLayout(new GridLayout(3, 1));

        upPanel.setLayout(new GridLayout(5, 1));
        upPanel.add(cityTFL);
        upPanel.add(searchCityTF);
        upPanel.add(cityComboBox);
        upPanel.add(chooseCityBtn);
        upPanel.add(clearSelectionBtn);
        this.add(upPanel);

        midPanel.setLayout(new GridLayout(5, 1));
        midPanel.add(gasStationTFL);
        midPanel.add(searchGasStationTF);
        midPanel.add(gasStationComboBox);
        midPanel.add(chooseGasStationBtn);
        midPanel.add(refreshBtn);
        this.add(midPanel);

        scrollTablePane.setPreferredSize(new Dimension(770, 170));
        downPanel.add(scrollTablePane);
        this.add(downPanel);

        searchCityTF.getDocument().addDocumentListener(new SearchCity());
        searchGasStationTF.getDocument().addDocumentListener(new SearchGasStation());
        table.addMouseListener(new MouseAction());
        refreshBtn.addActionListener(new RefreshAction());
        chooseGasStationBtn.addActionListener(new ChooseGasStation());
        chooseCityBtn.addActionListener(new ChooseCity());
        clearSelectionBtn.addActionListener(new ResetForm());

        this.setVisible(true);
        midPanel.setVisible(false);
        downPanel.setVisible(false);

        try {
            cityList = httpClient.getCities();
            cityList.forEach(city -> {
                cityComboBox.addItem(city);
            });
        } catch (NotFoundException nfx) {
            JOptionPane.showMessageDialog(null, "City with that name does not exist.", "City Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (IOException iox) {
            JOptionPane.showMessageDialog(null, "Please check your Internet connection and try again.", "Cannot Connect.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class RefreshAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                cityList = httpClient.getCities();
                cityComboBox.removeAllItems();
                cityList.forEach(city -> {
                    cityComboBox.addItem(city);
                });
            } catch (NotFoundException nfx) {
                JOptionPane.showMessageDialog(null, "Please check the URL. Cannot establish connection with the server!", "Connection error!", JOptionPane.ERROR_MESSAGE);
            } catch (IOException iox) {
                JOptionPane.showMessageDialog(null, "Please check your Internet connection and try again.", "Connection error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public class ResetForm implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchGasStationTF.setText("");
            searchCityTF.setText("");
            refreshBtn.doClick();
            downPanel.setVisible(false);
            midPanel.setVisible(false);
        }
    }

    public class SearchGasStation implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            this.updateGasStationComboBox();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            this.updateGasStationComboBox();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            this.updateGasStationComboBox();
        }

        private void updateGasStationComboBox() {
            gasStationComboBox.removeAllItems();

            var currentSearch = gasStationList
                    .stream().filter(g -> g.getName().toLowerCase()
                            .contains(searchGasStationTF.getText().toLowerCase()))
                    .collect(Collectors.toList());
            currentSearch.forEach(g -> {
                gasStationComboBox.addItem(g);
            });
        }
    }

    public class MouseAction implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.getSelectedRow();
            int selectedFuelId = Integer.parseInt(table.getValueAt(row, 0).toString());
            try {
                fuelReadDto = httpClient.getFuelPriceHistoryByFuelId(selectedFuelId);
                new FuelPriceHistoryWindow(fuelReadDto);
            } catch (NotFoundException nfx) {
            JOptionPane.showMessageDialog(null, "Fuel with that id does not exist.", "Fuel Not Found", JOptionPane.ERROR_MESSAGE);
        } catch (IOException iox) {
            JOptionPane.showMessageDialog(null, "Please check your Internet connection and try again.", "Cannot Connect.", JOptionPane.ERROR_MESSAGE);
        }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public class SearchCity implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            this.updateCityComboBox();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            this.updateCityComboBox();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            this.updateCityComboBox();
        }

        private void updateCityComboBox() {
            cityComboBox.removeAllItems();

            var currentSearch = cityList
                    .stream().filter(c -> c.getName().toLowerCase()
                            .contains(searchCityTF.getText().toLowerCase()))
                    .collect(Collectors.toList());
            currentSearch.forEach(c -> {
                cityComboBox.addItem(c);
            });

        }
    }

    public class ChooseGasStation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            selectedStationId = ((GasStationRead) (gasStationComboBox.getSelectedItem())).getId();
            try {
                ArrayList<FuelRead> fuels = httpClient.getFuelsForGasStation(selectedStationId);
                table.setModel(new TableModel(fuels));
            } catch (NotFoundException nfx) {
                JOptionPane.showMessageDialog(null, "Gas station does not exist.", "Gas station Not Found", JOptionPane.ERROR_MESSAGE);
            } catch (IOException iox) {
                JOptionPane.showMessageDialog(null, "Please check your Internet connection and try again.", "Cannot Connect", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class ChooseCity implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                selectedCityId = ((City) (cityComboBox.getSelectedItem())).getId();
                gasStationList = httpClient.getGasStationsByCityName(((City) (cityComboBox.getSelectedItem())).getName());
                gasStationComboBox.removeAllItems();
                gasStationList.forEach(s -> {gasStationComboBox.addItem(s);});
                downPanel.setVisible(true);
                midPanel.setVisible(true);
            } catch (NotFoundException nfx) {
                JOptionPane.showMessageDialog(null, "City with that name does not exist.", "City Not Found", JOptionPane.ERROR_MESSAGE);
            } catch (IOException iox) {
                JOptionPane.showMessageDialog(null, "Please check your Internet connection and try again.", "Cannot Connect", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}


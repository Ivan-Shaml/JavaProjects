package dev.ivanshamliev.desktopFuelRest.gui.fuelPriceWindow;

import dev.ivanshamliev.desktopFuelRest.dtos.FuelReadDto;
import dev.ivanshamliev.desktopFuelRest.dtos.PriceHistory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class FuelPriceHistoryWindow extends JFrame {
    JPanel mainPanel = new JPanel();
    ChartPanel chartPanel = null;

    FuelReadDto fuelReadDto = null;

    public FuelPriceHistoryWindow(FuelReadDto fuelReadDto) {

        this.fuelReadDto = fuelReadDto;

        this.setSize(800, 600);
        this.setLayout(new GridLayout(1, 1));
        mainPanel.setLayout(new GridLayout(1, 1));

        chartPanel = new ChartPanel(generateLineChart(generateDataset()));
        chartPanel.setPreferredSize(new Dimension(560, 370));
        mainPanel.add(chartPanel);

        this.add(mainPanel);
        this.setVisible(true);
    }

    public DefaultCategoryDataset generateDataset() {
        if (fuelReadDto != null && fuelReadDto.getPriceHistory() != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            Map<LocalDate, List<PriceHistory>> pricePerDay = fuelReadDto.getPriceHistory().stream().collect(Collectors.groupingBy(PriceHistory::getTimeUpdatedAsLocalDate));

            Map<LocalDate, Double> avgPricePerDay = new HashMap<>();

            pricePerDay.forEach((d, fh) -> {
                var avg = fh.stream().mapToDouble(PriceHistory::getOldPrice).average();
                if (avg.isPresent()) {
                    avgPricePerDay.put(d, avg.getAsDouble());
                }
            });

            avgPricePerDay.forEach((date, price) -> {
                dataset.addValue(price, "Average price BGN/Litre", date + String.format("(%.2f)", price));
            });

            return dataset;
        }

        return null;
    }

    public JFreeChart generateLineChart(DefaultCategoryDataset dataset) {

        if (fuelReadDto != null) {
            JFreeChart lineChart = ChartFactory.createLineChart(
                    String.format("Fuel: %s, Gas station: %s", fuelReadDto.getName(), fuelReadDto.getGasStation().getName()),
                    "Date", "Price",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            return lineChart;
        }

        return null;
    }
}

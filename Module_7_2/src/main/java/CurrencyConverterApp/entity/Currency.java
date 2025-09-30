package CurrencyConverterApp.entity;

public class Currency {
    private int id;
    private String abbreviation;
    private String name;
    private double rateToUsd;

    // Constructors
    public Currency() {
    }

    public Currency(int id, String abbreviation, String name, double rateToUsd) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUsd = rateToUsd;
    }

    public Currency(String abbreviation, String name, double rateToUsd) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUsd = rateToUsd;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRateToUsd() {
        return rateToUsd;
    }

    public void setRateToUsd(double rateToUsd) {
        this.rateToUsd = rateToUsd;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Currency currency = (Currency) obj;
        return abbreviation.equals(currency.abbreviation);
    }

    @Override
    public int hashCode() {
        return abbreviation.hashCode();
    }
}

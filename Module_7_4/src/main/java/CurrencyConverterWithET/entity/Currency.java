package CurrencyConverterWithET.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currencies")
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "abbreviation", nullable = false, unique = true, length = 3)
    private String abbreviation;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "rate_to_usd", nullable = false)
    private Double rateToUsd;

    // One-to-Many relationship: One currency can be source for many transactions
    @OneToMany(mappedBy = "sourceCurrency", fetch = FetchType.LAZY)
    private List<Transaction> sourceTransactions = new ArrayList<>();

    // One-to-Many relationship: One currency can be target for many transactions
    @OneToMany(mappedBy = "targetCurrency", fetch = FetchType.LAZY)
    private List<Transaction> targetTransactions = new ArrayList<>();

    // Constructors
    public Currency() {
    }

    public Currency(String abbreviation, String name, Double rateToUsd) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUsd = rateToUsd;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getRateToUsd() {
        return rateToUsd;
    }

    public void setRateToUsd(Double rateToUsd) {
        this.rateToUsd = rateToUsd;
    }

    public List<Transaction> getSourceTransactions() {
        return sourceTransactions;
    }

    public void setSourceTransactions(List<Transaction> sourceTransactions) {
        this.sourceTransactions = sourceTransactions;
    }

    public List<Transaction> getTargetTransactions() {
        return targetTransactions;
    }

    public void setTargetTransactions(List<Transaction> targetTransactions) {
        this.targetTransactions = targetTransactions;
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
        return abbreviation != null && abbreviation.equals(currency.abbreviation);
    }

    @Override
    public int hashCode() {
        return abbreviation != null ? abbreviation.hashCode() : 0;
    }
}

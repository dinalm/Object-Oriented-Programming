package CurrencyConverterWithET.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    // Point 6: Transaction ID generated automatically by database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    // Many-to-One relationship: Many transactions can have same source currency
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_currency_id", nullable = false)
    private Currency sourceCurrency;

    // Many-to-One relationship: Many transactions can have same target currency
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_currency_id", nullable = false)
    private Currency targetCurrency;

    @Column(name = "source_amount", nullable = false)
    private Double sourceAmount;

    @Column(name = "target_amount", nullable = false)
    private Double targetAmount;

    @Column(name = "exchange_rate", nullable = false)
    private Double exchangeRate;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    // Constructors
    public Transaction() {
        this.transactionDate = LocalDateTime.now();
    }

    public Transaction(Currency sourceCurrency, Currency targetCurrency,
                       Double sourceAmount, Double targetAmount, Double exchangeRate) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.exchangeRate = exchangeRate;
        this.transactionDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + transactionId +
                ", from=" + (sourceCurrency != null ? sourceCurrency.getAbbreviation() : "null") +
                ", to=" + (targetCurrency != null ? targetCurrency.getAbbreviation() : "null") +
                ", amount=" + sourceAmount +
                ", result=" + targetAmount +
                ", date=" + transactionDate +
                '}';
    }
}

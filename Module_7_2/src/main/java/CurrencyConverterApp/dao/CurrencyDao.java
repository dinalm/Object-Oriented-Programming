package CurrencyConverterApp.dao;

import CurrencyConverterApp.datasource.MariaDbConnection;
import CurrencyConverterApp.entity.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {
    private MariaDbConnection dataSource;

    public CurrencyDao() {
        this.dataSource = MariaDbConnection.getInstance();
    }

    public double getExchangeRate(String abbreviation) throws SQLException {
        String sql = "SELECT rate_to_usd FROM currencies WHERE abbreviation = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, abbreviation);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("rate_to_usd");
                }
            }
        }
        return -1; // Currency not found
    }

    public Currency getCurrencyByAbbreviation(String abbreviation) throws SQLException {
        String sql = "SELECT id, abbreviation, name, rate_to_usd FROM currencies WHERE abbreviation = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, abbreviation);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Currency(
                            rs.getInt("id"),
                            rs.getString("abbreviation"),
                            rs.getString("name"),
                            rs.getDouble("rate_to_usd")
                    );
                }
            }
        }
        return null;
    }

    public List<Currency> getAllCurrencies() throws SQLException {
        List<Currency> currencies = new ArrayList<>();
        String sql = "SELECT id, abbreviation, name, rate_to_usd FROM currencies ORDER BY abbreviation";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Currency currency = new Currency(
                        rs.getInt("id"),
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getDouble("rate_to_usd")
                );
                currencies.add(currency);
            }
        }
        return currencies;
    }

    public boolean testConnection() {
        return dataSource.testConnection();
    }
}

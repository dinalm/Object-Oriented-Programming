package CurrencyConverterWithJPA.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import CurrencyConverterWithJPA.entity.Currency;
import CurrencyConverterWithJPA.util.JpaUtil;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public List<Currency> getAllCurrencies() {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            TypedQuery<Currency> query = em.createQuery(
                    "SELECT c FROM Currency c ORDER BY c.abbreviation",
                    Currency.class
            );
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error retrieving currencies: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Currency getCurrencyByAbbreviation(String abbreviation) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            TypedQuery<Currency> query = em.createQuery(
                    "SELECT c FROM Currency c WHERE c.abbreviation = :abbr",
                    Currency.class
            );
            query.setParameter("abbr", abbreviation);
            List<Currency> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            System.err.println("Error retrieving currency: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public double getExchangeRate(String abbreviation) {
        Currency currency = getCurrencyByAbbreviation(abbreviation);
        return currency != null ? currency.getRateToUsd() : -1;
    }

    public boolean insertCurrency(Currency currency) {
        EntityManager em = null;
        EntityTransaction transaction = null;

        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            // Check if currency already exists
            Currency existing = getCurrencyByAbbreviation(currency.getAbbreviation());
            if (existing != null) {
                System.err.println("Currency " + currency.getAbbreviation() + " already exists.");
                return false;
            }

            em.persist(currency);
            transaction.commit();

            System.out.println("Currency " + currency.getAbbreviation() + " inserted successfully.");
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error inserting currency: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean updateCurrency(Currency currency) {
        EntityManager em = null;
        EntityTransaction transaction = null;

        try {
            em = JpaUtil.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            em.merge(currency);
            transaction.commit();

            System.out.println("Currency " + currency.getAbbreviation() + " updated successfully.");
            return true;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error updating currency: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean testConnection() {
        return JpaUtil.testConnection();
    }
}

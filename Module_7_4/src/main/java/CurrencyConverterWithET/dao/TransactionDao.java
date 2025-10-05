package CurrencyConverterWithET.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import CurrencyConverterWithET.entity.Transaction;
import CurrencyConverterWithET.util.JpaUtil;

import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    public boolean saveTransaction(Transaction transaction) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(transaction);

            tx.commit();

            System.out.println("Transaction saved successfully: " + transaction);
            return true;

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error saving transaction: " + e.getMessage());
            e.printStackTrace();
            return false;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transaction> getAllTransactions() {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            TypedQuery<Transaction> query = em.createQuery(
                    "SELECT t FROM Transaction t ORDER BY t.transactionDate DESC",
                    Transaction.class
            );
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error retrieving transactions: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Transaction getTransactionById(Long transactionId) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            return em.find(Transaction.class, transactionId);
        } catch (Exception e) {
            System.err.println("Error retrieving transaction: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transaction> getRecentTransactions(int limit) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            TypedQuery<Transaction> query = em.createQuery(
                    "SELECT t FROM Transaction t ORDER BY t.transactionDate DESC",
                    Transaction.class
            );
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error retrieving recent transactions: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

package DAL;

import BLL.CustomerBLL;
import BLL.OrderDetailBLL;

import hibernate.entities.Customer;
import hibernate.entities.OrderDetail;
import hibernate.entities.Order;
import hibernate.utils.HibernateUtils;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class OrderDAL {
    static final SessionFactory factory = HibernateUtils.getSessionFactory();

    public List getAllOrder(String orderby) {
        Session session = factory.openSession();
        List listOrder = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Order  ORDER BY id " + orderby;
            listOrder = session.createQuery(hql).list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listOrder;
    }

    public Order getOrderById(int id) {
        Session session = factory.openSession();
        Order order = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Order P WHERE P.id =" + id;
            order = (Order) session.createQuery(hql).uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return order;
    }

    public int deleteOrder(int id) {
        Session session = factory.openSession();
        int result = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "DELETE FROM Order WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            result = query.executeUpdate();
            // System.out.println("Rows affected: " + result);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public int insertOrder(Order order) {
        Session session = factory.openSession();
        int result = 1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();

            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
        return result;
    }

    public long getCount() {
        Session session = factory.openSession();
        long amount = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select count(*) from Order");
            amount = (long) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return amount;
    }

    public int getTotalRevenue() {
        Session session = factory.openSession();
        double total = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT sum(totalPrice) from Order");
            total = (double) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (int) total;
    }
}
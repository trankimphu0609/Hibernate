package BLL;

import DAL.OrderDAL;
import hibernate.entities.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderBLL {
    private OrderDAL dal = new OrderDAL();
    private List<Order> hdBLL;

    public OrderBLL() {
        list();
    }

    public List<Order> getOrderBLL() {
        return hdBLL;
    }

    public void list() {
        hdBLL = new ArrayList<>();
        hdBLL = dal.getAllOrder("DESC");
    }

    public void delete(String id) {
        int idHoaDon = Integer.parseInt(id);
        for (Order hoaDonDTO : hdBLL) {
            if (hoaDonDTO.getId() == idHoaDon) {
                hdBLL.remove(hoaDonDTO);
                OrderDAL orderDAL = new OrderDAL();
                orderDAL.deleteOrder(idHoaDon);
                return;
            }
        }
    }

    public List<Order> getListOrder() {
        try {
            return dal.getAllOrder("DESC");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getCountOrder() {
        try {
            return dal.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getTotalRevenue() {
        try {
            return dal.getTotalRevenue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Order getOrderById(int id) {
        try {
            return dal.getOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertOrder(Order hd) {
        dal.insertOrder(hd);
        hdBLL.add(hd);
    }

    public String remindMaHD() {
        int max = 0;
        String s = "";
        for (Order hd : hdBLL) {
            int id = hd.getId();
            if (id > max) {
                max = id;
            }
        }
        for (int i = 0; i < 3 - String.valueOf(max + 1).length(); i++) {
            s += "0";
        }
        return s + (max + 1);
    }

    public boolean check(String maHD) {
        for (Order hd : hdBLL) {
            if (String.valueOf(hd.getId()).equals(maHD)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Order> search(int mm, int yyyy, double max, double min, int mahd) {
        int mm1 = 0, mm2 = 12;
        int yyy1 = 0, yyy2 = Calendar.getInstance().get(Calendar.YEAR);

        if (mm != -1) {
            mm1 = mm;
            mm2 = mm;
        }
        if (yyyy != 0) {
            yyy1 = yyyy;
            yyy2 = yyyy;
        }

        ArrayList<Order> search = new ArrayList<>();
        for (Order hd : hdBLL) {
            Date date = hd.getCreatedDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date.getTime());

            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            if (hd.getTotalPrice() >= min && hd.getTotalPrice() <= max
                    && (month >= mm1 && month <= mm2)
                    && (year >= yyy1 && year <= yyy2)) {
                if (mahd != 0 && hd.getId() == mahd) {
                    search.clear();
                    search.add(hd);
                    break;
                }
                if (mahd != 0 && hd.getId() != mahd) {
                    search.clear();
                }
                search.add(hd);
            }
        }
        return search;
    }

}

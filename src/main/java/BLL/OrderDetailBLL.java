package BLL;

import DAL.OrderDetailDAL;
import hibernate.entities.Order;
import hibernate.entities.OrderDetail;
import hibernate.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailBLL {

    private OrderDetailDAL orderDetailDAL = new OrderDetailDAL();
    private List<OrderDetail> orderDetailsBLL;

    public OrderDetailBLL() {
        orderDetailsBLL = null;
    }

    public OrderDetailBLL(int i1) {
        listOrderDetail(i1);
    }

    public List<OrderDetail> getCt_hdBLL() {
        return orderDetailsBLL;
    }

    public void listOrderDetail(int orderID) {
        orderDetailsBLL = new ArrayList<>();
        orderDetailsBLL = orderDetailDAL.getAllOrderDetail(orderID);
    }

    public void insertOrderDetail(OrderDetail cthdDTO) {
        try {
            orderDetailDAL.insertOrderdetail(cthdDTO);
            orderDetailsBLL.add(cthdDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String id) {
        int idHD = Integer.parseInt(id);
        listOrderDetail(idHD);
        for (OrderDetail cthdDTO : orderDetailsBLL) {
            if (cthdDTO.getOrder().getId() == idHD) {
                orderDetailsBLL.remove(cthdDTO);
                OrderDetailDAL cthdDAO = new OrderDetailDAL();
                cthdDAO.deleteOrderDetail(idHD);
                return;
            }
        }
    }

    public long getCountOrderDetail() {
        try {
            return orderDetailDAL.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

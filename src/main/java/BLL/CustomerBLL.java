/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CustomerDAL;
import hibernate.entities.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Trần Kim Phú
 */
public class CustomerBLL {

    private static List<Customer> listCus;
    private CustomerDAL data = new CustomerDAL();

    public CustomerBLL() {
        loadData();
    }

    public void loadData() {
        if (listCus == null) {
            listCus = new ArrayList<Customer>();
        }
        listCus = data.getAllCustomer("ASC");
    }

    public List<Customer> getListCustomer() {
        return listCus;
    }

    public void setListCustomer(List<Customer> listCus) {
        CustomerBLL.listCus = listCus;
    }

    public Customer getCustomerById(int id) {
        try {
            return data.getCustomerById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(Customer c) {
        try {
            data.insertCustomer(c);
            listCus.add(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        int idCus = Integer.parseInt(id);
        for (int i = 0; i < listCus.size(); i++) {
            if (listCus.get(i).getId() == idCus) {
                try {
                    data.deleteCustomer(idCus);
                    listCus.remove(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(Customer c) {
        for (int i = 0; i < listCus.size(); i++) {
            if (listCus.get(i).getId() == c.getId()) {
                try {
                    data.updateCustomer(c);
                    listCus.set(i, c);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

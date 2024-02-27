/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.ProductDAL;

import java.util.ArrayList;
import java.util.List;

import hibernate.entities.Product;

/**
 * @author Asus
 */
public class SanPhamBLL {

    private static List<Product> listProduct;
    private ProductDAL dal = new ProductDAL();

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void loadData() {
        if (listProduct == null) {
            listProduct = new ArrayList<Product>();
        }
        listProduct = dal.getAllProduct("ASC");
    }

    public void add(Product spDTO) {
        try {
            dal.insertProdct(spDTO);
            listProduct.add(spDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int id) {
        try {
            return dal.getProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String id) {

        int idSP = Integer.parseInt(id);
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getId() == idSP) {
                try {

                    dal.deleteProduct(idSP);
                    listProduct.remove(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(Product spDTO) {
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getId() == spDTO.getId()) {
                try {
                    dal.updateProduct(spDTO);
                    listProduct.set(i, spDTO);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Product> searchProduct(int masp, int maloai, int max, int min) {
        ArrayList<Product> search = new ArrayList<>();

        for (Product sp : listProduct) {
            Product spTemp = null;
            if (masp == 0 && maloai == 0) {
                spTemp = sp;
            } else if (masp == 0) {
                if (sp.getCategory().getId() == maloai) {
                    spTemp = sp;
                }
            } else if (maloai == 0) {
                if (sp.getId() == masp) {
                    spTemp = sp;
                }
            }

            if (spTemp != null && spTemp.getPrice() >= min && spTemp.getPrice() <= max) {
                search.add(spTemp);
            }

        }
        return search;
    }

    public long getCountProduct() {
        return dal.getCount();
    }
}

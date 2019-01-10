package ysan.product.dao;

import java.util.ArrayList;
import java.util.List;

import ysan.product.entity.Product;

/**
 * 对Product对象的CRUD方法
 * 
 * @author ysan
 *
 */
public class ProductDao {
	// 模拟"数据库",存放所有商品数据
	private static List<Product> data = new ArrayList<>();

	static {
		for (int i = 0; i <= 10; i++) {
			data.add(new Product("" + i, "笔记本" + i, "LN00" + i, 1000.0 + i));
		}
	}

	public List<Product> findAll() {
		return data;
	}
	
	public Product findById(String id) {
		for (Product p : data) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}
}

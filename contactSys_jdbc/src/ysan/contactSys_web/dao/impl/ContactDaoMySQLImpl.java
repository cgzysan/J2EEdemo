package ysan.contactSys_web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ysan.contactSys_web.dao.ContactDao;
import ysan.contactSys_web.entity.Contact;
import ysan.contactSys_web.util.JdbcUtil;

public class ContactDaoMySQLImpl implements ContactDao {

	/**
	 * 添加联系人
	 */
	@Override
	public void addContact(Contact contact) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();

			String sql = "INSERT INTO contact(id,name,gender,age,phone,email,qq) VALUES(?,?,?,?,?,?,?)";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			String id = UUID.randomUUID().toString().replace("-", "");
			// 设置参数
			stmt.setString(1, id);
			stmt.setString(2, contact.getName());
			stmt.setString(3, contact.getGender());
			stmt.setInt(4, contact.getAge());
			stmt.setString(5, contact.getPhone());
			stmt.setString(6, contact.getEmail());
			stmt.setString(7, contact.getQq());

			// 执行
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	@Override
	public void updateContact(Contact contact) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();

			String sql = "UPDATE contact SET name = ?,gender = ?,age = ?,phone = ?,email = ?,qq = ? WHERE id = ?";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			// 设置参数
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setInt(3, contact.getAge());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getEmail());
			stmt.setString(6, contact.getQq());
			stmt.setString(7, contact.getId());

			// 执行
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}

	}

	@Override
	public void deleteContact(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();

			String sql = "DELETE FROM contact WHERE id=?";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			// 设置参数
			stmt.setString(1, id);

			// 执行
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt);
		}
	}

	@Override
	public List<Contact> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();

			String sql = "SELECT * FROM contact";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			// 执行
			rs = stmt.executeQuery();
			List<Contact> list = new ArrayList<>();
			while (rs.next()) {
				Contact c = new Contact();
				c = new Contact();
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setAge(rs.getInt("age"));
				c.setPhone(rs.getString("phone"));
				c.setEmail(rs.getString("email"));
				c.setQq(rs.getString("qq"));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}

	@Override
	public Contact findById(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();

			String sql = "SELECT * FROM contact WHERE id = ?";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			// 设置参数
			stmt.setString(1, id);

			// 执行
			rs = stmt.executeQuery();
			Contact c = null;
			if (rs.next()) {
				c = new Contact();
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setAge(rs.getInt("age"));
				c.setPhone(rs.getString("phone"));
				c.setEmail(rs.getString("email"));
				c.setQq(rs.getString("qq"));
			}
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}

	@Override
	public boolean checkContact(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取连接
			conn = JdbcUtil.getConnection();
			String sql = "SELECT * FROM contact WHERE name = ?";
			// 创建PrepareStatement
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			// 执行返回结果
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}

}

package ysan.hotel_sys.util;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 连接mysql数据库工具类
 * @author ysan
 *
 */
public class JdbcUtils {
	
	// 初始化连接池
	private static DataSource datasource;
	static {
		datasource = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return datasource;
	}
	
	/**
	 * 创建DbUtils常用工具类
	 */
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(datasource);
	}
}

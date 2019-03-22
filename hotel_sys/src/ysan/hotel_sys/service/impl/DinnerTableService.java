package ysan.hotel_sys.service.impl;

import java.util.Date;
import java.util.List;

import ysan.hotel_sys.dao.IDinnerTableDao;
import ysan.hotel_sys.entity.DinnerTable;
import ysan.hotel_sys.factory.BeanFactory;
import ysan.hotel_sys.service.IDinnerTableService;

public class DinnerTableService implements IDinnerTableService {
	
	private IDinnerTableDao dao = BeanFactory.getInstance("dinnerTableDao", IDinnerTableDao.class);

	@Override
	public void add(DinnerTable dt) {
		dao.add(dt);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public void updata(DinnerTable dt) {
		dao.update(dt);
	}

	@Override
	public List<DinnerTable> query() {
		return dao.query();
	}

	@Override
	public DinnerTable findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<DinnerTable> query(String keyword) {
		return dao.query(keyword);
	}

	@Override
	public DinnerTable changeState(int id) {
		DinnerTable table = dao.findById(id);
		
		int status = table.getTableStatus();
		if (status == 0) {
			status = 1;
			Date date = new Date();
			table.setOrderDate(date);
		} else if (status == 1) {
			status = 0;
			table.setOrderDate(null);
		}
		table.setTableStatus(status);
		dao.update(table);
		return table;
	}

	@Override
	public void quitTable(int id) {
		dao.quitTable(id);
	}
}

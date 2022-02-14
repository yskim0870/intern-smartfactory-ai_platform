package kr.smartfactory.platform.web.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.smartfactory.platform.web.dao.IBillingDao;

/**
 * 
 * @since 2022. 1. 13. 오후 5:11:12
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 13. 오후 5:11:12 || Kyunghun Park || 최초 생성
 *
 */
@Repository(BillingDao.BEAN_QUALIFIER)
public class BillingDao implements IBillingDao {

	private JdbcTemplate jdbcTemplate;
	public static final String BEAN_QUALIFIER = "kr.smartfactory.platform.web.dao.impl.BillingDao";

	public BillingDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}

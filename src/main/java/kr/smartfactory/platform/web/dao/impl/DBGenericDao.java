package kr.smartfactory.platform.web.dao.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import open.commons.spring.jdbc.dao.AbstractSingleDataSourceDao;

import kr.smartfactory.platform.web.config.DataSourceConfig;

public class DBGenericDao extends AbstractSingleDataSourceDao {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier(DataSourceConfig.DATA_SOURCE)
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	@Qualifier(DataSourceConfig.QUERY_SOURCE)
	@Override
	public void setQuerySource(ReloadableResourceBundleMessageSource querySource) {
		this.querySource = querySource;
	}
	
}

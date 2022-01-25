/**
 * 
 */
package kr.smartfactory.platform.web.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import kr.smartfactory.platform.web.dto.dashboard.DashboardCountDTO;
import kr.smartfactory.platform.web.dto.dashboard.EdgeGWCountDTO;
import kr.smartfactory.platform.web.service.IDashBoardService;
import open.commons.Result;

/**
 * @packageName : kr.smartfactory.platform.web.service.impl
 * @description :
 * @author : Younghun Yu
 * @date : 2022.01.18
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2022.01.18 Younghun Yu 최초 생성
 */
@Service
@Qualifier(DashBoardService.BEAN_QUALIFER)
public class DashBoardService implements IDashBoardService {

	public final static String BEAN_QUALIFER = "kr.smartfactory.platform.web.service.impl.DashBoardService";

	/**
	 * @see kr.smartfactory.platform.web.service.IDashBoardService#selectCount()
	 */
	@Override
	public Result<DashboardCountDTO> selectCount() {

		DashboardCountDTO dash = new DashboardCountDTO();
		EdgeGWCountDTO edge = new EdgeGWCountDTO();

		edge.setTotalCount(19);
		edge.setNormalCount(15);
		edge.setAbnormalCount(4);

		dash.setBidCount(5);
		dash.setEdgeGWCount(edge);
		dash.setExpertCount(2);
		dash.setManufacturerCount(4);

		Result<DashboardCountDTO> res = new Result<DashboardCountDTO>();

		// null이 아닐 수 밖에 없다. 서버, 서비스 구현이 완료 되면 ok
		if (dash == null) {
			res.andFalse().setMessage("대시보드 카운트 조회 실패");
		} else {
			res.andTrue().setData(dash);
		}

		return res;
	}

	/**
	 * @see kr.smartfactory.platform.web.service.IDashBoardService#selectEdgeCount()
	 */
	@Override
	public Result<EdgeGWCountDTO> selectEdgeCount() {

		EdgeGWCountDTO edge = new EdgeGWCountDTO();
		Result<EdgeGWCountDTO> res = new Result<EdgeGWCountDTO>();
		Result<DashboardCountDTO> dash = selectCount();

		if (dash.getResult()) {
			edge = dash.getData().getEdgeGWCount();
			res.andTrue().setData(edge);
		}
		else {
			res.andFalse().setMessage("엣지 카운트 조회 실패");
		}

		return res;
	}
}
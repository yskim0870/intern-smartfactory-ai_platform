/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:55:14
*/

/**
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:55:14
*/
package kr.smartfactory.platform.web.service;

import kr.smartfactory.platform.web.dto.UserDTO;
import open.commons.Result;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 12. 8. 오전 9:55:14
 *
 */
public interface IUserService {

    /**
     * Business number or UserID를 이용한 사용자 및 회사 정보 조회
     * 
     * @return : 성공/실패 여부, 실패 시 메시지, 조회 결과
     *
     * @since 2022. 1. 11. 오후 2:33:52
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 11. 오후 2:33:52 || Kyunghun Park || 최초 생성
     *
     */
    public Result<UserDTO> detailUser(String id);
}

/*
 * This file is generated under this project, "kr.smartfactory.platform.web". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2021. 12. 8. 오전 9:55:39
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
 * @since: 2021. 12. 8. 오전 9:55:39
*/
package kr.smartfactory.platform.web.dao;

import kr.smartfactory.platform.web.dto.UserDTO;

/**
 *
 *
 * @author: yskim
 * @date: 2021. 12. 8. 오전 9:55:39
 *
 */
public interface IUserDao {

    /**
     * 
     * @param id : Business number or UserID를 이용한 사용자 및 회사 정보 조회
     * @return :
     *
     * @since 2022. 1. 11. 오후 3:02:27
     * @author "KyungHun Park"
     * 
     * @modified 2022. 1. 11. 오후 3:02:27 || Kyunghun Park || 최초 생성
     *
     */
    public UserDTO detailUser(String id);
}

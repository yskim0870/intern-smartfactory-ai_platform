package kr.smartfactory.platform.web.dto;

/**
 * 사용자 구분
 * 
 * @since 2022. 1. 11. 오후 2:42:46
 * @author "KyungHun Park"
 *
 * @modified 2022. 1. 11. 오후 2:42:46 || Kyunghun Park || 최초 생성
 *
 */
public class UserGradeDTO {
    private int id;
    private String name;
    private String desc;

    /**
     * id을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:34
     * @author "KyungHun Park"
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * id 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:34
     * @author "KyungHun Park"
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * name을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:34
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * name 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:34
     * @author "KyungHun Park"
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * desc을(를) 호출합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:34
     * @author "KyungHun Park"
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * desc 을(를) 지정합니다.
     *
     * @since 2022. 1. 11. 오후 2:41:34
     * @author "KyungHun Park"
     *
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}

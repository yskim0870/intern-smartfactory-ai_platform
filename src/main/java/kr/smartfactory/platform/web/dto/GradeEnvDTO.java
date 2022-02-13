package kr.smartfactory.platform.web.dto;

public class GradeEnvDTO {

    private int id;
    
    private String name;
    
    private int cpu;
    
    private int memory;
    
    private int storage;
    
    private int price = 20000;

    public GradeEnvDTO() {

    }

    /**
     * id을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
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
     * @since 2022. 1. 24. 오후 12:52:59
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
     * @since 2022. 1. 24. 오후 12:52:59
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
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * cpu을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @return 
     */
    public int getCpu() {
        return cpu;
    }

    /**
     * cpu 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @param cpu
     */
    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    /**
     * memory을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @return 
     */
    public int getMemory() {
        return memory;
    }

    /**
     * memory 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @param memory
     */
    public void setMemory(int memory) {
        this.memory = memory;
    }

    /**
     * storage을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @return 
     */
    public int getStorage() {
        return storage;
    }

    /**
     * storage 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @param storage
     */
    public void setStorage(int storage) {
        this.storage = storage;
    }

    /**
     * price을(를) 호출합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @return 
     */
    public int getPrice() {
        return price;
    }

    /**
     * price 을(를) 지정합니다.
     *
     * @since 2022. 1. 24. 오후 12:52:59
     * @author "KyungHun Park"
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    
}

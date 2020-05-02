package part;

public class InHouse extends Part{
    Integer machineId;

    public InHouse(Integer id, String name, Double price, Integer stock, Integer min, Integer max, Integer machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }
}

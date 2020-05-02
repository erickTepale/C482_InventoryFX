package part;

public class Outsourced extends Part{
    String companyName;

    public Outsourced(Integer id, String name, Double price, Integer stock, Integer min, Integer max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

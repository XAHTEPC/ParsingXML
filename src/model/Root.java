package model;


import java.util.List;

public class Root {

    private String name_company;
    private List<Employee> flor;

    public String getName_company() {
        return name_company;
    }

    public List<Employee> getFlor() {
        return flor;
    }

    public void setName_company(String name_company) {
        this.name_company = name_company;
    }

    public void setFlor(List<Employee> flor) {
        this.flor = flor;
    }

    @Override
    public String toString() {
        return "Root{" +
                "name_company='" + name_company + '\'' +
                ", flor=" + flor +
                '}';
    }
}

import java.util.*;

class ComputerShop {
    private List<Computer> computers = new ArrayList<>();

    public void addComputer(Computer comp) {
        computers.add(comp);
    }

    public void removeComputer(Computer comp) {
        computers.remove(comp);
    }

    public Computer findComputer(Brand brand, double maxPrice) {
        return computers.stream()
                .filter(c -> c.brand == brand && c.price <= maxPrice)
                .findFirst()
                .orElse(null);
    }

    public void printAll() {
        computers.forEach(System.out::println);
    }
}
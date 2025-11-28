public interface Nameable {
    String getName();
}

class Planet implements Nameable {
    private String name;
    public Planet(String name) { this.name = name; }
    @Override public String getName() { return name; }
}

class Car implements Nameable {
    private String model;
    public Car(String model) { this.model = model; }
    @Override public String getName() { return model; }
}

class Animal implements Nameable {
    private String species;
    public Animal(String species) { this.species = species; }
    @Override public String getName() { return species; }
}
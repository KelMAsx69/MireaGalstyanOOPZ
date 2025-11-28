interface Convertable {
    double convert(double value, String targetUnit);
}

class TemperatureConverter implements Convertable {
    @Override
    public double convert(double celsius, String targetUnit) {
        switch (targetUnit.toLowerCase()) {
            case "kelvin":
                return celsius + 273.15;
            case "fahrenheit":
                return celsius * 9 / 5 + 32;
            default:
                throw new IllegalArgumentException("Неподдерживаемая единица измерения: " + targetUnit);
        }
    }
}

// Пример использования:
class TempDemo {
    public static void main(String[] args) {
        Convertable conv = new TemperatureConverter();
        double c = 25.0;
        System.out.println(c + "°C = " + conv.convert(c, "Kelvin") + " K");
        System.out.println(c + "°C = " + conv.convert(c, "Fahrenheit") + " °F");
    }
}
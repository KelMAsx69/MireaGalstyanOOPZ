// MathFunc.java
public class MathFunc implements MathCalculable {
    @Override
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    @Override
    public double complexAbs(double real, double imag) {
        return Math.sqrt(real * real + imag * imag);
    }

    // Дополнительный метод: длина окружности
    public double circleLength(double radius) {
        return 2 * PI * radius;
    }
}
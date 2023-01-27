public class TemperatureConverter {
    public static void main(String[] args) {
        TemperatureConverter converter = new TemperatureConverter();

        Double fahrenheit = 60.0;
        System.out.printf("%f in fahrenheit = %f in celcius\n", fahrenheit, converter.toCelsius(fahrenheit));

        Double celcius = 27.0;
        System.out.printf("%f in celcius = %f in fahrenheit\n", celcius, converter.toFahrenheit(celcius));
    }

    public Double toCelsius(Double fahrenheit) {
        Double factor = (5.0 / 9.0);
        return (fahrenheit - 32) * factor;
    }

    public Double toFahrenheit(Double celcius) {
        Double factor = (9.0 / 5.0);
        return celcius * factor + 32;
    }
}
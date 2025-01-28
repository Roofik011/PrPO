import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

// Базовый класс Animal
class Animal {
    protected String name;
    protected int age;

    // Конструкторы
    public Animal() {
        this.name = "Unknown";
        this.age = 0;
    }

    public Animal(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.name = name;
        this.age = age;
    }

    // Методы
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "', age=" + age + "}";
    }
}

// Производный класс Mammal
class Mammal extends Animal {
    private final String furColor;

    // Конструкторы
    public Mammal() {
        super();
        this.furColor = "Unknown";
    }

    public Mammal(String name, int age, String furColor) {
        super(name, age);
        this.furColor = furColor;
    }

    // Методы
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mammal mammal = (Mammal) o;
        return Objects.equals(furColor, mammal.furColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), furColor);
    }

    @Override
    public String toString() {
        return "Mammal{name='" + name + "', age=" + age + ", furColor='" + furColor + "'}";
    }
}

// Производный класс Bird
class Bird extends Animal {
    private final double wingspan;

    // Конструкторы
    public Bird() {
        super();
        this.wingspan = 0.0;
    }

    public Bird(String name, int age, double wingspan) {
        super(name, age);
        this.wingspan = wingspan;
    }

    // Методы
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bird bird = (Bird) o;
        return Double.compare(bird.wingspan, wingspan) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wingspan);
    }

    @Override
    public String toString() {
        return "Bird{name='" + name + "', age=" + age + ", wingspan=" + wingspan + "}";
    }
}

// Производный класс Artiodactyl
class Artiodactyl extends Mammal {
    private final int hoofCount;

    // Конструкторы
    public Artiodactyl() {
        super();
        this.hoofCount = 0;
    }

    public Artiodactyl(String name, int age, String furColor, int hoofCount) {
        super(name, age, furColor);
        this.hoofCount = hoofCount;
    }

    // Методы
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Artiodactyl that = (Artiodactyl) o;
        return hoofCount == that.hoofCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hoofCount);
    }

    @Override
    public String toString() {
        return "Artiodactyl{name='" + name + "', age=" + age + ", furColor='" + super.toString() + "', hoofCount=" + hoofCount + "}";
    }
}

// Главный класс приложения
public class Main {
    private static final List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить новый элемент");
            System.out.println("2. Удалить элемент по индексу");
            System.out.println("3. Вывод всех элементов");
            System.out.println("4. Сравнение двух элементов по индексам");
            System.out.println("5. Завершение работы");
            System.out.print("Выберите пункт: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    addAnimal(scanner);
                    break;
                case 2:
                    removeAnimal(scanner);
                    break;
                case 3:
                    printAllAnimals();
                    break;
                case 4:
                    compareAnimals(scanner);
                    break;
                case 5:
                    System.out.println("Завершение работы...");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Метод для добавления животного
    private static void addAnimal(Scanner scanner) {
        System.out.println("Выберите тип животного:");
        System.out.println("1. Animal");
        System.out.println("2. Mammal");
        System.out.println("3. Bird");
        System.out.println("4. Artiodactyl");
        int type = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите возраст: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        try {
            switch (type) {
                case 1:
                    animals.add(new Animal(name, age));
                    break;
                case 2:
                    System.out.print("Введите цвет шерсти: ");
                    String furColor = scanner.nextLine();
                    animals.add(new Mammal(name, age, furColor));
                    break;
                case 3:
                    System.out.print("Введите размах крыльев: ");
                    double wingspan = scanner.nextDouble();
                    scanner.nextLine(); // Очистка буфера
                    animals.add(new Bird(name, age, wingspan));
                    break;
                case 4:
                    System.out.print("Введите цвет шерсти: ");
                    String artiodactylFurColor = scanner.nextLine();
                    System.out.print("Введите количество копыт: ");
                    int hoofCount = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера
                    animals.add(new Artiodactyl(name, age, artiodactylFurColor, hoofCount));
                    break;
                default:
                    System.out.println("Неверный тип животного.");
            }
            System.out.println("Животное добавлено.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод для удаления животного
    private static void removeAnimal(Scanner scanner) {
        System.out.print("Введите индекс для удаления: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < animals.size()) {
            animals.remove(index);
            System.out.println("Животное удалено.");
        } else {
            System.out.println("Неверный индекс.");
        }
    }

    // Метод для вывода всех животных
    private static void printAllAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Список животных пуст.");
        } else {
            for (int i = 0; i < animals.size(); i++) {
                System.out.println(i + ": " + animals.get(i));
            }
        }
    }

    // Метод для сравнения двух животных
    private static void compareAnimals(Scanner scanner) {
        System.out.print("Введите первый индекс: ");
        int index1 = scanner.nextInt();
        System.out.print("Введите второй индекс: ");
        int index2 = scanner.nextInt();

        if (index1 >= 0 && index1 < animals.size() && index2 >= 0 && index2 < animals.size()) {
            boolean isEqual = animals.get(index1).equals(animals.get(index2));
            System.out.println("Результат сравнения: " + isEqual);
        } else {
            System.out.println("Неверные индексы.");
        }
    }
}
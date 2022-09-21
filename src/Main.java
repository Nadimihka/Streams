import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        persons.forEach(System.out::println);

        //Найти количество несовершеннолетних (т.е. людей младше 18 лет)
        long younger18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + younger18);

        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> result = persons.stream()
                .filter(x -> (x.getSex() == Sex.MAN) &&
                        (x.getAge() >= 18) && (x.getAge() <= 27))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println("Пофамильный список мужчин призывного возраста:");
        if (result.isEmpty()) {
            System.out.println("пуст");
        } else {
            result.forEach(System.out::println);
        }

        //Получить отсортированный по фамилии список потенциально
        // работоспособных людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин
        // и до 65 лет для мужчин)
        List<Person> result1 = persons.stream()
                .filter(x -> (x.getEducation() == Education.HIGHER) &&
                        (x.getAge() >= 18)
                        && ((x.getSex() == Sex.WOMAN && x.getAge() <= 60)
                        || (x.getSex() == Sex.MAN && x.getAge() <= 65)))
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Список работоспособных людей с высшим образованием:");
        if (result1.isEmpty()) {
            System.out.println("пуст");
        } else {
            result1.forEach(System.out::println);
        }
    }
}
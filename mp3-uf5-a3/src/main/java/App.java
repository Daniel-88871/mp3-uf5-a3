import model.Activitat;
import model.Entitat;
import model.GuiaEntitats;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class App {
    static final String pathXML = "http://justicia.gencat.cat/web/.content/tramits/entitats/llistatEntitats-federacions.xml";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        System.out.println("App A3");
        System.out.println("**********************************************************************************");
        System.out.println("Tria la recerca");
        System.out.println("1.Cerca les entitats que comencen per la lletra A");
        System.out.println("2.Cerca el nombre d'entitats que hi ha a la comarca del Barcelonès");
        System.out.println("3.Cerca totes les dades d'incripció amb maps");
        System.out.println("4.Cerca les entitats que són de la població Barcelona ordenats alfabèticament");
        System.out.println("5.Cerca l'entitat que va ser inscrita primer");
        System.out.println("6.Cerca l'entitat que va ser inscrita més recentment");
        System.out.println("7.Cerca els tipus d'entitats que hi ha");
        System.out.println("***********************************************************************************");
        option = scanner.nextInt();

        switch(option) {
            case 1:
                stream1();
                break;
            case 2:
                stream2();
                break;
            case 3:
                stream3();
                break;
            case 4:
                stream4();
                break;
            case 5:
                stream5();
                break;
            case 6:
                stream6();
                break;
            case 7:
                stream7();
                break;
        }
    }

    private static void stream1() {
        URL url = null;
        try {
            url = new URL(pathXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext contextObj = JAXBContext.newInstance(GuiaEntitats.class);
            Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
            GuiaEntitats guiaEntitats = (GuiaEntitats) unmarshallerObj.unmarshal(url);
            guiaEntitats.getEntitats().getLlistaEntitats().stream().filter(entitat -> entitat.getNom().startsWith("A")).forEach(System.out::println);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void stream2() {
        URL url = null;
        Long count;
        try {
            url = new URL(pathXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext contextObj = JAXBContext.newInstance(GuiaEntitats.class);
            Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
            GuiaEntitats guiaEntitats = (GuiaEntitats) unmarshallerObj.unmarshal(url);
            count = guiaEntitats.getEntitats().getLlistaEntitats().stream().filter(entitat -> entitat.getComarca().equals("Barcelonès")).count();
            System.out.println("Numero d'entitats de la comarca del Barcelonès: "+count);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void stream3() {
        URL url = null;
        try {
            url = new URL(pathXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext contextObj = JAXBContext.newInstance(GuiaEntitats.class);
            Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
            GuiaEntitats guiaEntitats = (GuiaEntitats) unmarshallerObj.unmarshal(url);
            guiaEntitats.getEntitats().getLlistaEntitats().stream().map(entitat -> entitat.getDataInscripcio()).forEach(System.out::println);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void stream4() {
        URL url = null;
        try {
            url = new URL(pathXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext contextObj = JAXBContext.newInstance(GuiaEntitats.class);
            Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
            GuiaEntitats guiaEntitats = (GuiaEntitats) unmarshallerObj.unmarshal(url);
            guiaEntitats.getEntitats().getLlistaEntitats().stream().filter(entitat -> entitat.getPoblacio().equals("Barcelona")).sorted(Entitat::compareTo).forEach(System.out::println);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void stream5() {
        URL url = null;
        try {
            url = new URL(pathXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext contextObj = JAXBContext.newInstance(GuiaEntitats.class);
            Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
            GuiaEntitats guiaEntitats = (GuiaEntitats) unmarshallerObj.unmarshal(url);
            Optional<Entitat> a = guiaEntitats.getEntitats().getLlistaEntitats().stream().min(Comparator.comparing(Entitat::getDataInscripcio));
            System.out.println(a);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void stream6() {
        URL url = null;
        try {
            url = new URL(pathXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext contextObj = JAXBContext.newInstance(GuiaEntitats.class);
            Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
            GuiaEntitats guiaEntitats = (GuiaEntitats) unmarshallerObj.unmarshal(url);
            Optional<Entitat> a = guiaEntitats.getEntitats().getLlistaEntitats().stream().max(Comparator.comparing(Entitat::getDataInscripcio));
            System.out.println(a);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void stream7() {
        URL url = null;
        try {
            url = new URL(pathXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            JAXBContext contextObj = JAXBContext.newInstance(GuiaEntitats.class);
            Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
            GuiaEntitats guiaEntitats = (GuiaEntitats) unmarshallerObj.unmarshal(url);
            guiaEntitats.getEntitats().getLlistaEntitats().stream().filter(distinctByKey(entitat -> entitat.getTipus())).forEach(entitat -> System.out.println("Tipus: "+entitat.getTipus()));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
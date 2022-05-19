package Learn;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input absolut path to test.json: ");
        String pathToTests = scanner.next();
        System.out.println("Input absolut path to values.json: ");
        String pathToValues = scanner.next();

        String testsJson = readFile(new Scanner(new FileReader(pathToTests)));
        String valuesJson = readFile(new Scanner(new FileReader(pathToValues)));

        ObjectMapper mapper = new ObjectMapper();
        Package beginValues = mapper.readValue(testsJson, Package.class);
        Values requiredValues = mapper.readValue(valuesJson, Values.class);
        List<Package> beginValuesList = new ArrayList<>();
        beginValuesList.add(beginValues);

        requiredValues.getValues().forEach(p -> checkAndReplaceValues(p, beginValuesList));

        mapper.writeValue(new File("src/main/resources/report.json"), beginValues);
    }

    private static String readFile(Scanner scanner) {
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNext()) {
            builder.append(scanner.next());
        }
        return builder.toString();
    }

    private static void checkAndReplaceValues(Value requiredValue, List<Package> beginValues) {
        beginValues.forEach(p -> checkAndReplaceValue(requiredValue, p));
    }

    private static void checkAndReplaceValue(Value requiredValue, Package beginValue) {
        if (requiredValue.getId().equals(beginValue.getId())) {
            beginValue.setValue(requiredValue.getValue());
            return;
        }
        if (Objects.nonNull(beginValue.getValues()) && !beginValue.getValues().isEmpty()) {
            checkAndReplaceValues(requiredValue, beginValue.getValues());
        }
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Package {
        private String id;
        private String title;
        private String value;
        private List<Package> values;
    }

    @Data
    static class Values {
        List<Value> values;
    }

    @Data
    static class Value {
        private String id;
        private String value;
    }
}

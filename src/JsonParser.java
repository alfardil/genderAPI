import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    // parse it normally
    public static void parseJson(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Extract fields from the JSON

        String name = jsonNode.get("name").asText(); // Get the string value for 'name'
        String gender = jsonNode.get("gender").asText(); // Get the string value for 'gender'
        double probability = jsonNode.get("probability").asDouble(); // Get the double value for 'probability'

        // Print the extracted data
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Probability: " + probability);
    }

    // put the data into an array to use it
    public static String[] responseJson(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Extract fields from the JSON

        String name = jsonNode.get("name").asText(); // Get the string value for 'name'
        String gender = jsonNode.get("gender").asText(); // Get the string value for 'gender'
        String probability = jsonNode.get("probability").asText(); // Get the double value for 'probability'

        // System.out.println("Name: " + name);
        // System.out.println("Gender: " + gender);
        // System.out.println("Probability: " + probability);

        String[] data = new String[] { name, gender, probability };
        return data;
    }
}

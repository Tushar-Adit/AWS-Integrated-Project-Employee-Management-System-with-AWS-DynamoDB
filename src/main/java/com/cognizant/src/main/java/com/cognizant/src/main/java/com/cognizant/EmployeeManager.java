import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import java.util.*;

public class EmployeeManager {
    private final DynamoDbClient dynamoDb;
    private final String tableName = "Employees";

    public EmployeeManager() {
        dynamoDb = DynamoDbClient.builder()
                .region(Region.AP_SOUTH_1) // change to your region
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    public void addEmployee(Employee emp) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.fromN(String.valueOf(emp.getId())));
        item.put("name", AttributeValue.fromS(emp.getName()));
        item.put("age", AttributeValue.fromN(String.valueOf(emp.getAge())));
        item.put("position", AttributeValue.fromS(emp.getPosition()));
        item.put("salary", AttributeValue.fromN(String.valueOf(emp.getSalary())));

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();
        dynamoDb.putItem(request);
        System.out.println("✅ Employee added to DynamoDB.");
    }

    public void viewEmployee(int id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.fromN(String.valueOf(id)));

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();
        Map<String, AttributeValue> item = dynamoDb.getItem(request).item();

        if (item != null && !item.isEmpty()) {
            System.out.println("👤 Employee Found:");
            System.out.println(item);
        } else {
            System.out.println("❌ Employee not found.");
        }
    }

    public void deleteEmployee(int id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.fromN(String.valueOf(id)));

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();
        dynamoDb.deleteItem(request);
        System.out.println("🗑️ Employee deleted.");
    }

    public void listEmployees() {
        ScanRequest request = ScanRequest.builder()
                .tableName(tableName)
                .build();
        ScanResponse response = dynamoDb.scan(request);

        for (Map<String, AttributeValue> item : response.items()) {
            System.out.println(item);
        }
    }
}

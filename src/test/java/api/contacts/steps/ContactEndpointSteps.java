//package api.contacts.steps;
//
//import io.qameta.allure.Attachment;
//import io.qameta.allure.Step;
//import io.restassured.response.Response;
//
//import java.util.Map;
//
//import static io.restassured.RestAssured.given;
//
//public class ContactEndpointSteps {
//    @Step
//    private Response getContactsBy(Map<String, String> requestsParams) {
//        return given().
//                params(requestsParams).
//                when().
//                get(URL + ENDPOINT);
//    }
//
//    @Step
//    private Response getContacts() {
//        return given().
//                contentType("application/json").
//                when().
//                get(URL + ENDPOINT);
//    }
//
//    @Step
//    private Response putContact(int id, String bodyData) {
//        return given().
//                contentType("application/json").
//                body(bodyData).
//                when().
//                put(URL + ENDPOINT + id);
//    }
//
//    @Step
//    private Response patchContact(int id, String bodyData) {
//        return given().
//                contentType("application/json").
//                body(bodyData).
//                when().
//                patch(URL + ENDPOINT + id);
//    }
//
//    @Step
//    private Response getContactById(int id) {
//        return given().
//                when().
//                get(URL + ENDPOINT + id);
//    }
//
//    @Step
//    private Response deleteContactById(int id) {
//        return given().
//                delete(URL + ENDPOINT + id);
//    }
//
//    @Step
//    private Response postContact(String bodyData) {
//        return given().
//                contentType("application/json").
//                body(bodyData).
//                when().
//                post(URL + ENDPOINT);
//    }
//
//    @Step
//    private Response optionsContact(int id) {
//        return given().
//                options(URL + ENDPOINT + id);
//    }
//
//    @Step
//    private Response headContact(int id) {
//        return given().
//                head(URL + ENDPOINT + id);
//    }
//
//
//    @Attachment(value = "Response", type = "text/json")
//    public String getResponse() {
//        return response.prettyPrint();
//    }
//
//    @Attachment(value="Response Header", type="text/plain")
//    public String getResponseHead(){
//        System.out.println(response.statusLine());
//        return response.statusLine();}
//}

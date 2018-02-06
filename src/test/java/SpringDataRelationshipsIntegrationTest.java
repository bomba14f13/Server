import Server.Model.Folder;
import Server.Model.Product;
import Server.SpringDataRestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.json.JSONException;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataRestApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringDataRelationshipsIntegrationTest {

    @Autowired
    private TestRestTemplate template;

    private static final String FOLDER_ENDPOINT = "http://localhost:8080/folders/";
    private static final String PRODUCT_ENDPOINT = "http://localhost:8080/products/";


    private static final String FOLDER_NAME = "Ubrania";
    
    @Test
    @WithMockUser
    public void whenSaveOneToManyRelationship_thenCorrect() throws JSONException{
        Folder folder = new Folder(FOLDER_NAME);
        template.postForEntity(FOLDER_ENDPOINT, folder, Folder.class);

        Product product1 = new Product("Buty");
        template.postForEntity(PRODUCT_ENDPOINT, product1, Product.class);

        Product product2 = new Product("Szale");
        template.postForEntity(PRODUCT_ENDPOINT, product2, Product.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> productHttpEntity = new HttpEntity<>(FOLDER_ENDPOINT + "/1", requestHeaders);
        template.exchange(PRODUCT_ENDPOINT + "/1/folder", HttpMethod.PUT, productHttpEntity, String.class);
        template.exchange(PRODUCT_ENDPOINT + "/2/folder", HttpMethod.PUT, productHttpEntity, String.class);

        ResponseEntity<Folder> folderGetResponse = template.getForEntity(PRODUCT_ENDPOINT + "/1/folder", Folder.class);
        assertEquals("folder is incorrect", folderGetResponse.getBody()
                .getName(), FOLDER_NAME);
    }
}
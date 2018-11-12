package br.com.zup.recruitment.backend.springxyinc.pointofinterest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

/**
 * Created by Fellipe G on 11/12/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = PointOfInterestService.class, secure = false)
public class PointOfInterestServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointOfInterestController pointOfInterestController;

    String examplePOIJson = "{\"id\": 1000,\"name\": \"POI Test\",\"xCoordinate\": 12,\"yCoordinate\": 10}";

    @Test
    public void retrieveAllPointsOfInterest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/points-of-interest")
                                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"id\":1,\"name\":\"Lanchonete\",\"xCoordinate\":27,\"yCoordinate\":12}," +
                "{\"id\":2,\"name\":\"Posto\",\"xCoordinate\":31,\"yCoordinate\":18}," +
                "{\"id\":3,\"name\":\"Joalheria\",\"xCoordinate\":15,\"yCoordinate\":12}," +
                "{\"id\":4,\"name\":\"Floricultura\",\"xCoordinate\":19,\"yCoordinate\":21}," +
                "{\"id\":5,\"name\":\"Pub\",\"xCoordinate\":12,\"yCoordinate\":8}," +
                "{\"id\":6,\"name\":\"Supermercado\",\"xCoordinate\":23,\"yCoordinate\":6}," +
                "{\"id\":7,\"name\":\"Churrascaria\",\"xCoordinate\":28,\"yCoordinate\":2}," +
                "{\"id\":1000,\"name\":\"Test 1\",\"xCoordinate\":12,\"yCoordinate\":10}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void retrievePointsInsideRadius() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/points-of-interest/list-nearby?referenceX=20&referenceY=10&maxDistance=10")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[" +
                "{\"id\":1,\"name\":\"Lanchonete\",\"xCoordinate\":27,\"yCoordinate\":12}," +
                "{\"id\":3,\"name\":\"Joalheria\",\"xCoordinate\":15,\"yCoordinate\":12}," +
                "{\"id\":5,\"name\":\"Pub\",\"xCoordinate\":12,\"yCoordinate\":8}," +
                "{\"id\":6,\"name\":\"Supermercado\",\"xCoordinate\":23,\"yCoordinate\":6}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void createPointOfInterest() throws Exception {
        PointOfInterest mockCourse = new PointOfInterest("POI Test",12,10);

        // Send course as body to /students/Student1/courses
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/points-of-interest")
                .accept(MediaType.APPLICATION_JSON).content(examplePOIJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        String expected = "{\"id\":1000,\"name\":\"POI Test\",\"xCoordinate\":12,\"yCoordinate\":10}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

    }
}

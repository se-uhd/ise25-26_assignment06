package de.campuscoffee.api;

import de.campuscoffee.api.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersSystemTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndGetUser() {
        UserDto toCreate = new UserDto();
        toCreate.setLogin("testuser1");
        toCreate.setEmail("test1@example.com");
        toCreate.setFirstName("Test");
        toCreate.setLastName("User");

        ResponseEntity<UserDto> postResp = restTemplate.postForEntity("/api/users", toCreate, UserDto.class);
        Assertions.assertEquals(HttpStatus.CREATED, postResp.getStatusCode());
        UserDto created = postResp.getBody();
        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());

        ResponseEntity<UserDto> getResp = restTemplate.getForEntity("/api/users/" + created.getId(), UserDto.class);
        Assertions.assertEquals(HttpStatus.OK, getResp.getStatusCode());
        UserDto fetched = getResp.getBody();
        Assertions.assertNotNull(fetched);
        Assertions.assertEquals("testuser1", fetched.getLogin());
        Assertions.assertEquals("test1@example.com", fetched.getEmail());
    }

    @Test
    public void testUpdateUser() {
        UserDto toCreate = new UserDto();
        toCreate.setLogin("updateme");
        toCreate.setEmail("orig@example.com");
        toCreate.setFirstName("Orig");
        toCreate.setLastName("Name");

        ResponseEntity<UserDto> postResp = restTemplate.postForEntity("/api/users", toCreate, UserDto.class);
        Assertions.assertEquals(HttpStatus.CREATED, postResp.getStatusCode());
        UserDto created = postResp.getBody();
        Assertions.assertNotNull(created);
        Long id = created.getId();

        UserDto update = new UserDto();
        update.setLogin("updateme");
        update.setEmail("changed@example.com");
        update.setFirstName("Changed");
        update.setLastName("Name");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDto> putEntity = new HttpEntity<>(update, headers);
        ResponseEntity<UserDto> putResp = restTemplate.exchange("/api/users/" + id, HttpMethod.PUT, putEntity, UserDto.class);
        Assertions.assertEquals(HttpStatus.OK, putResp.getStatusCode());

        UserDto after = putResp.getBody();
        Assertions.assertNotNull(after);
        Assertions.assertEquals("changed@example.com", after.getEmail());
        Assertions.assertEquals("Changed", after.getFirstName());
    }
}
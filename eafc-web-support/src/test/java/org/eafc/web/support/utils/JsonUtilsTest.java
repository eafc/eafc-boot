package org.eafc.web.support.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuxx
 * @date 2022/4/15
 */
public class JsonUtilsTest {

    private static final String JSON = "{\"name\":\"liuxx\",\"age\":18}";
    private static final String LIST_JSON = "[{\"name\":\"liuxx\",\"age\":18},{\"name\":\"liuxx2\",\"age\":18}]";

    @Test
    public void testJson() {
        User user = new User("liuxx", 18);
        Assert.assertEquals(JSON, JsonUtils.toJson(user));

        User user2 = new User("liuxx2", 18);
        List<User> users = Arrays.asList(user, user2);
        Assert.assertEquals(LIST_JSON, JsonUtils.toJson(users));
    }

    @Test
    public void testParse() {
        User user = JsonUtils.parse(JSON, User.class);
        Assert.assertNotNull(user);
        Assert.assertEquals("liuxx", user.getName());
        Assert.assertEquals((Integer) 18, user.getAge());

        List<User> users = JsonUtils.parse(LIST_JSON, new TypeReference<List<User>>() {
        });
        Assert.assertNotNull(users);
        Assert.assertEquals(2, users.size());
        Assert.assertEquals("liuxx2", users.get(1).getName());
    }
}

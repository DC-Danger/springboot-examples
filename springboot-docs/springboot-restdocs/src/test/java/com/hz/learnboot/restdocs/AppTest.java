package com.hz.learnboot.restdocs;

import com.hz.learnboot.restdocs.controller.RestdocsController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// @WebMvcTest(RestdocsController.class) // 注释掉，用这个注解只能扫描到Controller层代码， 会报找不到Service错误。
// @AutoConfigureRestDocs(outputDir = "target/snippets")
public class AppTest {
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/snippets");

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        // 启动整个项目，这样才能扫描到Service
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/index").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("index",responseFields(
                        fieldWithPath("message").description("The user's email address"))));
    }

    @Test
    public void getUserById() throws Exception {
        this.mockMvc.perform(get("/getUserById/{id}", 1))
                .andExpect(status().isOk())
                .andDo(document("getUserById", pathParameters(
                parameterWithName("id").description("用户ID")
        )));
    }

    @Test
    public void listUsers() throws Exception {
        this.mockMvc.perform(get("/listUsers").param("age","20").param("name", "张三"))
                .andExpect(status().isOk())
                .andDo(document("listUsers",requestParameters(
                        parameterWithName("age").description("用户年龄"),
                        parameterWithName("name").description("用户名"))));
    }

    @Test
    public void addUser() throws Exception {
        this.mockMvc.perform(post("/addUser"))
                .andExpect(status().isOk())
                .andDo(document("addUser"));
    }

    @Test
    public void removeUser() throws Exception {
        this.mockMvc.perform(post("/removeUser/{id}", 1))
                .andExpect(status().isOk())
                .andDo(document("removeUser", pathParameters(
                        parameterWithName("id").description("用户ID")
                )));
    }
}

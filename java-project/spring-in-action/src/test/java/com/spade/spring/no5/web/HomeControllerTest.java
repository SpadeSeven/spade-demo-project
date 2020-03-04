package com.spade.spring.no5.web;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class HomeControllerTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void test_homePage_1() {
    HomeController controller = new HomeController();
    Assertions.assertThat(controller.home()).isEqualTo("home");
  }

  @Test
  public void test_homePage_2() throws Exception {
    HomeController controller = new HomeController();
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    mockMvc
        .perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.view().name("home"));
  }

  @Test
  public void test_homePage_3() throws Exception {
    HomeController controller = new HomeController();
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    mockMvc
        .perform(MockMvcRequestBuilders.get("/homepage"))
        .andExpect(MockMvcResultMatchers.view().name("home"));
  }
}

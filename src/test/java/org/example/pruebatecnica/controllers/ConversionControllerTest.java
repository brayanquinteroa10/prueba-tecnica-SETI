package org.example.pruebatecnica.controllers;

import org.example.pruebatecnica.services.JsonToXmlService;
import org.example.pruebatecnica.services.XmlToJsonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JsonToXmlService jsonToXmlService;

    @MockBean
    private XmlToJsonService xmlToJsonService;

    @BeforeEach
    public void setUp() {
        when(jsonToXmlService.convertJsonToXml(anyString())).thenReturn("xml");
        when(xmlToJsonService.convertXmlToJson(anyString())).thenReturn("json");
        when(jsonToXmlService.convertJsonToXmlResponse(anyString())).thenReturn("xml");
        when(xmlToJsonService.convertXmlToJsonResponse(anyString())).thenReturn("json");
    }

    @Test
    public void testHandleConversion() throws Exception {
        mockMvc.perform(post("/convert").content("json"))
                .andExpect(status().isOk())
                .andExpect(content().string("xml"));

        mockMvc.perform(post("/convert").content("<xml>"))
                .andExpect(status().isOk())
                .andExpect(content().string("json"));
    }

    @Test
    public void testHandleConversionResponse() throws Exception {
        mockMvc.perform(post("/convertResponse").content("json"))
                .andExpect(status().isOk())
                .andExpect(content().string("xml"));

        mockMvc.perform(post("/convertResponse").content("<xml>"))
                .andExpect(status().isOk())
                .andExpect(content().string("json"));
    }
}

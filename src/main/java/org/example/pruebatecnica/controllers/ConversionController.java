package org.example.pruebatecnica.controllers;

import org.example.pruebatecnica.services.JsonToXmlService;
import org.example.pruebatecnica.services.XmlToJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {

    private final JsonToXmlService jsonToXmlService;
    private final XmlToJsonService xmlToJsonService;

    @Autowired
    public ConversionController(JsonToXmlService jsonToXmlService, XmlToJsonService xmlToJsonService) {
        this.jsonToXmlService = jsonToXmlService;
        this.xmlToJsonService = xmlToJsonService;
    }

    @PostMapping("/convert")
    public String handleConversion(@RequestBody String data) {
        // Detectar el formato de la solicitud
        boolean isXml = data.trim().startsWith("<");

        // Procesar la solicitud y generar la respuesta correspondiente
        if (isXml) {
            // Convertir XML a JSON y devolverlo
            return xmlToJsonService.convertXmlToJson(data);
        } else {
            // Convertir JSON a XML y devolverlo
            return jsonToXmlService.convertJsonToXml(data);
        }
    }

    @PostMapping("/convertResponse")
    public String handleConversionResponse(@RequestBody String data) {
        // Detectar el formato de la solicitud
        boolean isXml = data.trim().startsWith("<");

        // Procesar la solicitud y generar la respuesta correspondiente
        if (isXml) {
            // Convertir XML a JSON y devolverlo
            return xmlToJsonService.convertXmlToJsonResponse(data);
        } else {
            // Convertir JSON a XML y devolverlo
            return jsonToXmlService.convertJsonToXmlResponse(data);
        }
    }
}

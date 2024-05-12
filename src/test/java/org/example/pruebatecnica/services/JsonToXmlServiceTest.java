package org.example.pruebatecnica.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonToXmlServiceTest {

    private JsonToXmlService jsonToXmlService;

    @BeforeEach
    public void setUp() {
        jsonToXmlService = new JsonToXmlService();
    }

    @Test
    public void testConvertJsonToXmlResponse() throws Exception {
        String json = "{ \"enviarPedido\": { \"numPedido\": \"123\", \"cantidadPedido\": \"1\", \"codigoEAN\": \"456\", \"nombreProducto\": \"Producto\", \"numDocumento\": \"789\", \"direccion\": \"Direccion\" } }";
        String result = jsonToXmlService.convertJsonToXmlResponse(json);

        String expected = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:env=\"http://WSDLs/EnvioPedidos/EnvioPedidosAcme\"><soapenv:Header/><soapenv:Body><env:EnvioPedidoAcmeResponse><EnvioPedidoResponse><Codigo>123</Codigo><Mensaje>Entregado exitosamente al cliente</Mensaje></EnvioPedidoResponse></env:EnvioPedidoAcmeResponse></soapenv:Body></soapenv:Envelope>";

        assertEquals(expected, result);
    }

    @Test
    public void testConvertJsonToXmlResponseError() throws Exception {
        String json = "{ \"numPedido\": \"123\" }";
        String result = jsonToXmlService.convertJsonToXmlResponse(json);

        String expected = "<error>El campo 'enviarPedido' es requerido</error>";

        assertEquals(expected, result);
    }

    @Test
    public void testConvertJsonToXmlResponseEmpty() throws Exception {
        String json = "{}";
        String result = jsonToXmlService.convertJsonToXmlResponse(json);

        String expected = "<error>El campo 'enviarPedido' es requerido</error>";

        assertEquals(expected, result);
    }

    @Test
    public void testConvertJsonToXmlResponseError2() throws Exception {
        String json = "{ \"enviarPedido\": { \"numPedido\": \"123\" } }";
        String result = jsonToXmlService.convertJsonToXmlResponse(json);

        String expected = "<error>JSON incompleto</error>";

        assertEquals(expected, result);
    }

    @Test
    public void testConvertJsonToXml() throws Exception {
        String json = "{ \"enviarPedido\": { \"numPedido\": \"123\", \"cantidadPedido\": \"1\", \"codigoEAN\": \"456\", \"nombreProducto\": \"Producto\", \"numDocumento\": \"789\", \"direccion\": \"Direccion\" } }";
        String result = jsonToXmlService.convertJsonToXml(json);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(result)));

        assertEquals("soapenv:Envelope", document.getDocumentElement().getNodeName());
    }

    @Test
    public void testConvertJsonToXmlError() throws Exception {
        String json = "{ \"numPedido\": \"123\" }";
        String result = jsonToXmlService.convertJsonToXml(json);

        String expected = "<error>El campo 'enviarPedido' es requerido</error>";

        assertEquals(expected, result);
    }

    @Test
    public void testConvertJsonToXmlError2() throws Exception {
        String json = "{ \"enviarPedido\": { \"numPedido\": \"123\" } }";
        String result = jsonToXmlService.convertJsonToXml(json);

        String expected = "<error>JSON incompleto</error>";

        assertEquals(expected, result);
    }

    @Test
    public void testConvertJsonToXmlEmpty() throws Exception {
        String json = "{}";
        String result = jsonToXmlService.convertJsonToXml(json);

        String expected = "<error>El campo 'enviarPedido' es requerido</error>";

        assertEquals(expected, result);
    }

}

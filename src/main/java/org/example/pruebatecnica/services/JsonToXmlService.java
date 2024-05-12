package org.example.pruebatecnica.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JsonToXmlService {
    public String convertJsonToXml(String json) {

        try {
            // Se convierte el string JSON a un objeto JSON
            JSONObject jsonObject = new JSONObject(json);

            // Se verifica si todos los campos requeridos están presentes
            if (jsonObject.has("enviarPedido")) {
                JSONObject enviarPedido = jsonObject.getJSONObject("enviarPedido");

                if (enviarPedido.has("numPedido") && enviarPedido.has("cantidadPedido") &&
                        enviarPedido.has("codigoEAN") && enviarPedido.has("nombreProducto") &&
                        enviarPedido.has("numDocumento") && enviarPedido.has("direccion")) {

                    // Se construye el XML si el JSON es válido
                    StringBuilder xmlBuilder = new StringBuilder();
                    xmlBuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:env=\"http://WSDLs/EnvioPedidos/EnvioPedidosAcme\">");
                    xmlBuilder.append("<soapenv:Header/>");
                    xmlBuilder.append("<soapenv:Body>");
                    xmlBuilder.append("<env:EnvioPedidoAcme>");
                    xmlBuilder.append("<EnvioPedidoRequest>");
                    xmlBuilder.append("<pedido>").append(enviarPedido.getString("numPedido")).append("</pedido>");
                    xmlBuilder.append("<Cantidad>").append(enviarPedido.getString("cantidadPedido")).append("</Cantidad>");
                    xmlBuilder.append("<EAN>").append(enviarPedido.getString("codigoEAN")).append("</EAN>");
                    xmlBuilder.append("<Producto>").append(enviarPedido.getString("nombreProducto")).append("</Producto>");
                    xmlBuilder.append("<Cedula>").append(enviarPedido.getString("numDocumento")).append("</Cedula>");
                    xmlBuilder.append("<Direccion>").append(enviarPedido.getString("direccion")).append("</Direccion>");
                    xmlBuilder.append("</EnvioPedidoRequest>");
                    xmlBuilder.append("</env:EnvioPedidoAcme>");
                    xmlBuilder.append("</soapenv:Body>");
                    xmlBuilder.append("</soapenv:Envelope>");

                    return xmlBuilder.toString();
                } else {
                    return "<error>JSON incompleto</error>";
                }
            } else {
                return "<error>El campo 'enviarPedido' es requerido</error>";
            }
        } catch (Exception e) {
            // Se maneja el caso donde el JSON no se puede procesar
            e.printStackTrace();
            return "<error>Error al procesar el JSON</error>";
        }
    }

    public String convertJsonToXmlResponse(String json) {

        try {
            // Se convierte el string JSON a un objeto JSON
            JSONObject jsonObject = new JSONObject(json);

            // Se verifica si todos los campos requeridos están presentes
            if (jsonObject.has("enviarPedido")) {
                JSONObject enviarPedido = jsonObject.getJSONObject("enviarPedido");

                if (enviarPedido.has("numPedido") && enviarPedido.has("cantidadPedido") &&
                        enviarPedido.has("codigoEAN") && enviarPedido.has("nombreProducto") &&
                        enviarPedido.has("numDocumento") && enviarPedido.has("direccion")) {

                    // Se construye el XML si el JSON es válido
                    StringBuilder xmlBuilder = new StringBuilder();
                    xmlBuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:env=\"http://WSDLs/EnvioPedidos/EnvioPedidosAcme\">");
                    xmlBuilder.append("<soapenv:Header/>");
                    xmlBuilder.append("<soapenv:Body>");
                    xmlBuilder.append("<env:EnvioPedidoAcmeResponse>");
                    xmlBuilder.append("<EnvioPedidoResponse>");
                    xmlBuilder.append("<Codigo>").append(enviarPedido.getString("numPedido")).append("</Codigo>");
                    xmlBuilder.append("<Mensaje>Entregado exitosamente al cliente</Mensaje>");
                    xmlBuilder.append("</EnvioPedidoResponse>");
                    xmlBuilder.append("</env:EnvioPedidoAcmeResponse>");
                    xmlBuilder.append("</soapenv:Body>");
                    xmlBuilder.append("</soapenv:Envelope>");

                    return xmlBuilder.toString();
                } else {
                    return "<error>JSON incompleto</error>";
                }
            } else {
                return "<error>El campo 'enviarPedido' es requerido</error>";
            }
        } catch (Exception e) {
            // Se maneja el caso donde el JSON no se puede procesar
            e.printStackTrace();
            return "<error>Error al procesar el JSON</error>";
        }
    }

}

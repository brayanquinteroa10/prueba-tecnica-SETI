package org.example.pruebatecnica.services;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

@Service
public class XmlToJsonService {
    public String convertXmlToJsonResponse(String xml) {
        try {
            JSONObject jsonObject = XML.toJSONObject(xml);

            // Se extrae la información del XML y se crea un JSON
            JSONObject enviarPedidoRespuesta = new JSONObject();
            JSONObject soapBody = jsonObject.getJSONObject("soapenv:Envelope")
                    .getJSONObject("soapenv:Body")
                    .getJSONObject("env:EnvioPedidoAcme");

            JSONObject envioPedidoRequest = soapBody.getJSONObject("EnvioPedidoRequest");

            // Se verifica si todos los campos requeridos están presentes
            if (envioPedidoRequest.has("pedido")) {
                enviarPedidoRespuesta.put("codigoEnvio", envioPedidoRequest.get("pedido").toString());
                enviarPedidoRespuesta.put("estado", "Entregado exitosamente al cliente");

                JSONObject response = new JSONObject();
                response.put("enviarPedidoRespuesta", enviarPedidoRespuesta);

                return response.toString();
            } else {
                return "{\"error\": \"El campo 'pedido' es requerido\"}";
            }
        } catch (Exception e) {
            // Se manejan errores de conversión o estructura del XML
            e.printStackTrace();
            return "{\"error\": \"Error al convertir XML a JSON\"}";
        }
    }

    public String convertXmlToJson(String xml) {
        try {
            JSONObject jsonObject = XML.toJSONObject(xml);

            // Se obtiene el objeto que contiene los datos del pedido
            JSONObject envioPedidoAcme = jsonObject.getJSONObject("soapenv:Envelope")
                    .getJSONObject("soapenv:Body")
                    .getJSONObject("env:EnvioPedidoAcme")
                    .getJSONObject("EnvioPedidoRequest");

            // Se verifica si todos los campos requeridos están presentes
            if (envioPedidoAcme.has("pedido")) {
                // Se construye un JSON con los datos del pedido
                JSONObject enviarPedidoRespuesta = new JSONObject();
                enviarPedidoRespuesta.put("enviarPedido", envioPedidoAcme.toMap());

                return enviarPedidoRespuesta.toString();
            } else {
                return "{\"error\": \"El campo 'pedido' es requerido\"}";
            }
        } catch (Exception e) {
            // Se maneja el caso donde el XML no se puede procesar
            e.printStackTrace();
            return "{\"error\": \"Error al procesar el XML\"}";
        }
    }
}




package com.shipping.backend.models.common;

import lombok.Data;

@Data
public class MockResponses {

    public String messageResponse(String message) {
        String response = "";
        if (message.contains("packageSize")) {
            response = "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":5},{\"id\":2,\"description\":\"Medium\",\"priceFactor\":10},{\"id\":3,\"description\":\"Large\",\"priceFactor\":15}]";
        }
        if (message.contains("packageType")) {
            response = "[{\"id\":2,\"description\":\"Box\",\"price\":10},{\"id\":3,\"description\":\"Envelope\",\"price\":5}]";
        }
        if (message.contains("transportVelocity")) {
            response = "[{\"id\":1,\"description\":\"Regular\",\"priceFactor\":5},{\"id\":2,\"description\":\"Express\",\"priceFactor\":10},{\"id\":3,\"description\":\"Slow\",\"priceFactor\":0}]";
        }
        if (message.contains("transportType")) {
            response = "[{\"id\":2,\"description\":\"Land\",\"pricePerMile\":2},{\"id\":1,\"description\":\"Air\",\"pricePerMile\":5}]";
        }
        if (message.contains("city") && !message.contains("transport")) {
            response = "[{\"id\":9,\"name\":\"Leon\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":12," +
                    "\"name\":\"Cuernavaca\",\"tax\":0,\"seaport\":false,\"airport\":false},{\"id\":23,\"name\":\"Tuxtla Gutierrez\"" +
                    ",\"tax\":5,\"seaport\":false,\"airport\":false},{\"id\":26,\"name\":\"Veracruz\",\"tax\":10,\"seaport\":true,\"" +
                    "airport\":false},{\"id\":27,\"name\":\"Villahermosa\",\"tax\":0,\"seaport\":false,\"airport\":true}," +
                    "{\"id\":28,\"name\":\"Saltillo\",\"tax\":10,\"seaport\":false,\"airport\":true},{\"id\":29,\"name\":\"Tampico\"," +
                    "\"tax\":16,\"seaport\":true,\"airport\":true},{\"id\":22,\"name\":\"Cancun\",\"tax\":16,\"seaport\":true," +
                    "\"airport\":true},{\"id\":24,\"name\":\"Ciudad del Carmen\",\"tax\":5,\"seaport\":true,\"airport\":false}," +
                    "{\"id\":25,\"name\":\"Merida\",\"tax\":16,\"seaport\":true,\"airport\":true},{\"id\":30,\"name\":\"Durango\"" +
                    ",\"tax\":10,\"seaport\":false,\"airport\":true},{\"id\":31,\"name\":\"Chihuahua\",\"tax\":10,\"seaport\":false," +
                    "\"airport\":true},{\"id\":3,\"name\":\"Cuautitlan\",\"tax\":0,\"seaport\":false,\"airport\":true},{\"id\":2," +
                    "\"name\":\"Ensenada\",\"tax\":16,\"seaport\":true,\"airport\":true},{\"id\":5,\"name\":\"San Luis Potosi\"," +
                    "\"tax\":10,\"seaport\":false,\"airport\":true},{\"id\":4,\"name\":\"Cdmx\",\"tax\":0,\"seaport\":false," +
                    "\"airport\":true},{\"id\":7,\"name\":\"Zacatecas\",\"tax\":5,\"seaport\":false,\"airport\":true},{\"id\":6," +
                    "\"name\":\"Pachuca\",\"tax\":5,\"seaport\":false,\"airport\":true},{\"id\":8,\"name\":\"Aguascalientes\"," +
                    "\"tax\":5,\"seaport\":false,\"airport\":true},{\"id\":11,\"name\":\"Puebla\",\"tax\":5,\"seaport\":false," +
                    "\"airport\":true},{\"id\":10,\"name\":\"Queretaro\",\"tax\":5,\"seaport\":false,\"airport\":true},{\"id\":13,\"" +
                    "name\":\"La Paz\",\"tax\":10,\"seaport\":true,\"airport\":true},{\"id\":15,\"name\":\"Guaymas\",\"tax\":16,\"" +
                    "seaport\":true,\"airport\":false},{\"id\":14,\"name\":\"Mazatlan\",\"tax\":10,\"seaport\":true,\"airport" +
                    "\":true},{\"id\":17,\"name\":\"Tepic\",\"tax\":0,\"seaport\":false,\"airport\":true},{\"id\":16,\"name\":" +
                    "\"Puerto Vallarta\",\"tax\":10,\"seaport\":true,\"airport\":true},{\"id\":19,\"name\":\"Manzanillo\",\"tax\"" +
                    ":5,\"seaport\":true,\"airport\":true},{\"id\":18,\"name\":\"Lazaro Cardenas\",\"tax\":5,\"seaport\":true,\"" +
                    "airport\":false},{\"id\":21,\"name\":\"Acapulco\",\"tax\":10,\"seaport\":true,\"airport\":true},{\"id\":20," +
                    "\"name\":\"Puerto Escondido\",\"tax\":0,\"seaport\":true,\"airport\":false},{\"id\":33,\"name\":\"Tlaxcala\"," +
                    "\"tax\":0,\"seaport\":false,\"airport\":true},{\"id\":32,\"name\":\"Monterrey\",\"tax\":16,\"seaport\":false," +
                    "\"airport\":true}]";
        }
        if (message.contains("routesList")) {
            response = "[\n" +
                    "{\n" +
                    "\"from\": \"Ensenada\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 91\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Saltillo\",\n" +
                    "\"to\": \"Zacatecas\",\n" +
                    "\"distance\": 32\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Tlaxcala\",\n" +
                    "\"to\": \"Tuxtla Gutierrez\",\n" +
                    "\"distance\": 33\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"San Luis Potosi\",\n" +
                    "\"to\": \"Manzanillo\",\n" +
                    "\"distance\": 6\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Queretaro\",\n" +
                    "\"to\": \"Cuernavaca\",\n" +
                    "\"distance\": 12\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Mazatlan\",\n" +
                    "\"to\": \"Tuxtla Gutierrez\",\n" +
                    "\"distance\": 90\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Saltillo\",\n" +
                    "\"to\": \"Merida\",\n" +
                    "\"distance\": 22\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Chihuahua\",\n" +
                    "\"to\": \"La Paz\",\n" +
                    "\"distance\": 20\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Tepic\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 69\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Saltillo\",\n" +
                    "\"to\": \"Veracruz\",\n" +
                    "\"distance\": 4\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Veracruz\",\n" +
                    "\"to\": \"Queretaro\",\n" +
                    "\"distance\": 79\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Manzanillo\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 25\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Mazatlan\",\n" +
                    "\"to\": \"Queretaro\",\n" +
                    "\"distance\": 86\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Chihuahua\",\n" +
                    "\"to\": \"Puerto Vallarta\",\n" +
                    "\"distance\": 80\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Pachuca\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 3\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Chihuahua\",\n" +
                    "\"to\": \"Mazatlan\",\n" +
                    "\"distance\": 88\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Tampico\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 60\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Merida\",\n" +
                    "\"to\": \"Acapulco\",\n" +
                    "\"distance\": 80\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Zacatecas\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 16\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Tuxtla Gutierrez\",\n" +
                    "\"to\": \"Pachuca\",\n" +
                    "\"distance\": 4\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Puebla\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 9\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Pachuca\",\n" +
                    "\"to\": \"Lazaro Cardenas\",\n" +
                    "\"distance\": 86\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Chihuahua\",\n" +
                    "\"to\": \"Pachuca\",\n" +
                    "\"distance\": 89\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Puerto Vallarta\",\n" +
                    "\"to\": \"Queretaro\",\n" +
                    "\"distance\": 25\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Leon\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 79\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Puerto Vallarta\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 13\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Puerto Vallarta\",\n" +
                    "\"to\": \"Ensenada\",\n" +
                    "\"distance\": 4\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Veracruz\",\n" +
                    "\"to\": \"Zacatecas\",\n" +
                    "\"distance\": 92\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Tuxtla Gutierrez\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 31\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Queretaro\",\n" +
                    "\"to\": \"Leon\",\n" +
                    "\"distance\": 82\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Tlaxcala\",\n" +
                    "\"to\": \"Puerto Vallarta\",\n" +
                    "\"distance\": 53\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Acapulco\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 36\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Leon\",\n" +
                    "\"to\": \"Zacatecas\",\n" +
                    "\"distance\": 45\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"La Paz\",\n" +
                    "\"to\": \"Durango\",\n" +
                    "\"distance\": 38\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"La Paz\",\n" +
                    "\"to\": \"Tlaxcala\",\n" +
                    "\"distance\": 38\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Cuautitlan\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 28\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Pachuca\",\n" +
                    "\"to\": \"San Luis Potosi\",\n" +
                    "\"distance\": 12\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Cuernavaca\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 48\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Durango\",\n" +
                    "\"to\": \"San Luis Potosi\",\n" +
                    "\"distance\": 21\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"La Paz\",\n" +
                    "\"to\": \"Cdmx\",\n" +
                    "\"distance\": 52\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Ensenada\",\n" +
                    "\"to\": \"Tepic\",\n" +
                    "\"distance\": 87\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"San Luis Potosi\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 8\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Saltillo\",\n" +
                    "\"to\": \"Leon\",\n" +
                    "\"distance\": 62\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Queretaro\",\n" +
                    "\"to\": \"Cuautitlan\",\n" +
                    "\"distance\": 22\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Tuxtla Gutierrez\",\n" +
                    "\"to\": \"Tampico\",\n" +
                    "\"distance\": 78\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Lazaro Cardenas\",\n" +
                    "\"to\": \"Ensenada\",\n" +
                    "\"distance\": 77\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Chihuahua\",\n" +
                    "\"to\": \"Saltillo\",\n" +
                    "\"distance\": 58\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Cdmx\",\n" +
                    "\"to\": \"Puebla\",\n" +
                    "\"distance\": 58\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Queretaro\",\n" +
                    "\"to\": \"Cancun\",\n" +
                    "\"distance\": 21\n" +
                    "},\n" +
                    "{\n" +
                    "\"from\": \"Queretaro\",\n" +
                    "\"to\": \"Ensenada\",\n" +
                    "\"distance\": 82\n" +
                    "}\n" +
                    "]";
        }
        return response;
    }
}

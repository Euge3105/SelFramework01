package org.sel.pom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sel.pom.objects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

        public static <T> T deserializeJson(String fileName, Class <T> T) throws IOException {
            InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(is, T);
    }
//    public static BillingAddress deserializeJson(InputStream is, BillingAddress billingAddress) throws IOException {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(is, billingAddress.getClass());
//    }
}

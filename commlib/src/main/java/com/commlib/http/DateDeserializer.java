package com.commlib.http;

import com.commlib.util.DateUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.Y_M_D);

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null) {

            final long jsonLong= json.getAsLong();
            try {
                return new Date(jsonLong);
            } catch (Exception e) {
                e.printStackTrace();
            }
            final String jsonString = json.getAsString();
            try {
                return dateFormat.parse(jsonString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

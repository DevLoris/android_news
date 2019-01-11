package com.lorispinna.news.databases.converter;

import com.google.gson.Gson;
import com.lorispinna.news.models.Source;

import java.util.Date;

import androidx.room.TypeConverter;

public class SourceConverter {

        @TypeConverter
        public static String toString(Source source) {
            return (new Gson().toJson(source));
        }

        @TypeConverter
        public static Source toSource(String source) {
            return (new Gson().fromJson(source, Source.class));
        }

}

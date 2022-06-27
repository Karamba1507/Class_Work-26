package com.company;

import java.security.Key;

public interface Connecting {

        void openConnectionToDB();

        void closeDB();

        boolean getConnection();

        KeyValue getDataBaseByIndex(int index);

        boolean getValueByKey(String key);

        String readValueByKey(String key);

        KeyValue[] readSetOfValues(int lastIndex, int firstIndex);

        int numOfElements();

        boolean addElement(KeyValue element);

        boolean updateValueByIndex(int index, String value);

        boolean updateValueByKey(String key, String value);

    }



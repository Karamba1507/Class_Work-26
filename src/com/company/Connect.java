package com.company;

public class Connect implements Connecting {

    private DataBase dataBase;
    private static boolean connectionIsOpen=false;

    public Connect(DataBase dataBase) {
        this.dataBase=dataBase;
    }

    static void switchConnection() {
        if (!connectionIsOpen) {
            connectionIsOpen=true;
            System.out.println("You connected to DB!");
        } else {
            connectionIsOpen=false;
            System.out.println("DB was disconnected!");
        }
    }


    @Override
    public void openConnectionToDB() {
        if (connectionIsOpen)
            System.out.println("Connection is already open!");
        else
            switchConnection();
    }

    @Override
    public void closeDB() {
        if (connectionIsOpen)
            switchConnection();
        else
            System.out.println("DB already closed!");
    }

    @Override
    public boolean getConnection() {
        return connectionIsOpen;
    }

    @Override
    public KeyValue getDataBaseByIndex(int index) {
        if (connectionIsOpen) {
            if (index > -1 && index < dataBase.getArray().length) {
                if (dataBase.getArray()[index] != null) {
                    return dataBase.getArray()[index];
                }
            }
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Cant make this action!");
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean getValueByKey(String key) {
        if (connectionIsOpen) {
            for (int i=0; i < dataBase.getArray().length; i++) {
                if (dataBase.getArray()[i] != null && dataBase.getArray()[i].getKey().equals(key)) {
                    if (dataBase.getArray()[i].getValue() != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("No connection!");
            e.getMessage();
        }
        return false;
    }

    @Override
    public String readValueByKey(String key) {
        if (connectionIsOpen) {
            for (int i=0; i < dataBase.getArray().length; i++) {
                if (dataBase.getArray()[i] != null && dataBase.getArray()[i].getKey().equals(key)) {
                    return dataBase.getArray()[i].getValue();
                }
            }
            return null;
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("No connection!");
            e.getMessage();
        }
        return null;
    }

    @Override
    public KeyValue[] readSetOfValues(int lastIndex, int firstIndex) {
        if (connectionIsOpen && firstIndex > -1 && lastIndex < dataBase.getArray().length) {
//            DataBase[] result = new DataBase[lastIndex - firstIndex];

            KeyValue[] result=new KeyValue[lastIndex - firstIndex + 1];

            int resulIndex=0;

            for (int i=firstIndex; i <= lastIndex; i++) {

                result[resulIndex]=dataBase.getArray()[i];

                resulIndex++;
            }
            return result;
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Cant make this action!");
            e.getMessage();
        }
        return null;
    }

    @Override
    public int numOfElements() {
        if (connectionIsOpen) {
            int result=0;
            for (int i=0; i < dataBase.getArray().length; i++) {
                if (dataBase.getArray()[i] != null) {
                    result++;
                }
            }
            return result;
        } else try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Cant make this action!");
            e.getMessage();
        }
        return -1;
    }

    @Override
    public boolean addElement(KeyValue element) {

        if (connectionIsOpen) {
            for (int i=0; i < dataBase.getArray().length; i++) {
                if (dataBase.getArray()[i] == null) {

                    dataBase.getArray()[i]=element;
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean updateValueByIndex(int index, String value) {
        if (connectionIsOpen) {
            if (index > -1 && index < dataBase.getArray().length && dataBase.getArray()[index] != null) {
                dataBase.getArray()[index].setValue(value);
                return true;
            }
        }
        try {
            throw new Exception("Cant make this action!");
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean updateValueByKey(String key, String value) {
        if (connectionIsOpen) {
            for (int i=0; i < dataBase.getArray().length; i++) {
                if (dataBase.getArray()[i] != null && dataBase.getArray()[i].getKey().equals(key)) {
                    dataBase.getArray()[i].setValue(value);
                    return true;
                }
            }
        }
        try {
            throw new Exception("Cant make this action!");
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    void print() {
        System.out.println("|--------+----------------|");
        System.out.println("| KEY    |     VALUE      |");
        System.out.println("|--------+----------------|");

        for (int i=0; i < dataBase.getArray().length; i++) {

            System.out.println(String.format("|  %-4s  |   %-10s   |", dataBase.getArray()[i].getKey(),
                    dataBase.getArray()[i].getValue()));
            System.out.println("|--------+----------------|");
        }
    }

    public static void main(String[] args) {

        FileService fileService = new FileService();
        KeyValue[]  keyValues = fileService.readString();

        DataBase dataBase = new DataBase();
        dataBase.setArray(keyValues);

        Connect connect=new Connect(dataBase);
        connect.print();

    }
}

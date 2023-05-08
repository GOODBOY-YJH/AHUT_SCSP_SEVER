package com.example.ahut_scsp.util.oss;

public enum TypeEnum {
    COVER("cover"),
    Teacher("teacher"),
    Student("student"),
    Honor("honor"),
    Avatar("avatar"),
    Default("default");


    private String key;

    TypeEnum(String key) {
        this.key = key;
    }

    public static TypeEnum findByKey(String key) {
        if (key != null) {
            for (TypeEnum type : TypeEnum.values()) {
                if (key.equals(type.getKey())) {
                    return type;
                }
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }
}

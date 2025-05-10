package guru.qa.data;

// enum позволяет создавать перечислимые типы (дни недели, языки).
// Если в реальном мире есть объект и нам заранее известно сколько их, то мы можем их поместить в enum

public enum Language {

    RU("Что такое Selenide?"), // это имя enum-а
    EN("What is Selenide?");

    public final String description;

    Language(String description) {
        this.description = description;
    }

}

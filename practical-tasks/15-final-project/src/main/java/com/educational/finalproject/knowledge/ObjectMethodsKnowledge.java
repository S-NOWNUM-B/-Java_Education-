package com.educational.finalproject.knowledge;

import java.util.Objects;

/**
 * # Методы класса Object
 *
 * ### 1. toString()
 * Возвращает строковое представление объекта. По умолчанию: `ИмяКласса@ХешКод`.
 *
 * ### 2. equals(Object obj)
 * Проверяет объекты на логическое равенство.
 * **Правила (контракт):**
 * *   Рефлексивность: `a.equals(a) == true`.
 * *   Симметричность: `a.equals(b) == b.equals(a)`.
 * *   Транзитивность: `a.equals(b)` и `b.equals(c)` -> `a.equals(c)`.
 * *   Согласованность: многократные вызовы дают один результат.
 * *   `a.equals(null) == false`.
 *
 * ### 3. hashCode()
 * Возвращает целое число — "отпечаток" объекта.
 * **Связь с equals:**
 * *   Если `a.equals(b) == true`, то `a.hashCode() == b.hashCode()`.
 * *   Если хеш-коды разные, объекты точно не равны.
 * *   Если хеш-коды одинаковые (коллизия), объекты могут быть как равны, так и нет.
 *
 * ### 4. getClass()
 * Возвращает объект типа `Class`, содержащий метаинформацию о классе в рантайме.
 *
 * ### 5. clone()
 * Создает копию объекта (требует реализации интерфейса `Cloneable`).
 *
 * ### 6. finalize()
 * Вызывается сборщиком мусора перед удалением (Deprecated).
 */
public class ObjectMethodsKnowledge implements Cloneable {
    private String name;
    private int id;

    public ObjectMethodsKnowledge(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectMethodsKnowledge that = (ObjectMethodsKnowledge) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "ObjectMethodsKnowledge{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

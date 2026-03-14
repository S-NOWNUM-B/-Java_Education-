package com.educational.finalproject.knowledge;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * # Коллекции: Set
 *
 * ### Интерфейс Set
 * Коллекция, которая не содержит дубликатов.
 *
 * ### Реализации
 *
 * **1. HashSet**
 * *   Основан на `HashMap`.
 * *   Не гарантирует порядок элементов.
 * *   Позволяет `null`.
 * *   Быстрые операции: `O(1)`.
 *
 * **2. LinkedHashSet**
 * *   Сохраняет порядок добавления элементов.
 * *   Чуть медленнее `HashSet` из-за поддержания связей.
 *
 * **3. TreeSet**
 * *   Элементы хранятся в отсортированном порядке (Natural order или `Comparator`).
 * *   Основан на красно-черном дереве.
 * *   Медленнее: `O(log n)`.
 * *   Не позволяет `null`.
 */
public class CollectionsSetKnowledge {
    private Set<String> hashSet;
    private Set<String> linkedHashSet;
    private Set<String> treeSet;

    public CollectionsSetKnowledge() {
        this.hashSet = new HashSet<>();
        this.linkedHashSet = new LinkedHashSet<>();
        this.treeSet = new TreeSet<>();
    }

    public void addToAll(String item) {
        hashSet.add(item);
        linkedHashSet.add(item);
        treeSet.add(item);
    }

    public Set<String> getHashSet() {
        return hashSet;
    }

    public void setHashSet(Set<String> hashSet) {
        this.hashSet = hashSet;
    }

    public Set<String> getLinkedHashSet() {
        return linkedHashSet;
    }

    public void setLinkedHashSet(Set<String> linkedHashSet) {
        this.linkedHashSet = linkedHashSet;
    }

    public Set<String> getTreeSet() {
        return treeSet;
    }

    public void setTreeSet(Set<String> treeSet) {
        this.treeSet = treeSet;
    }
}

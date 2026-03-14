package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.BillOfMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для спецификаций (BillOfMaterialsRepository).</p>
 * <p>Обеспечивает доступ к составам изделий. Включает методы 
 * для поиска последних версий спецификаций и фильтрации по статусу 
 * утверждения руководством.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
@Repository
public interface BillOfMaterialsRepository extends JpaRepository<BillOfMaterials, Long> {

    /**
     * Поиск спецификаций для конкретного продукта.
     * @param productName Название изделия
     * @return Список спецификаций
     */
    List<BillOfMaterials> findByProductName(String productName);

    /**
     * Поиск спецификации по названию продукта и номеру версии.
     * @param productName Название
     * @param version Версия
     * @return Optional со спецификацией
     */
    Optional<BillOfMaterials> findByProductNameAndVersion(String productName, String version);

    /**
     * Поиск всех утвержденных спецификаций.
     * @param isApproved Флаг утверждения
     * @return Список спецификаций
     */
    List<BillOfMaterials> findByIsApproved(boolean isApproved);
}

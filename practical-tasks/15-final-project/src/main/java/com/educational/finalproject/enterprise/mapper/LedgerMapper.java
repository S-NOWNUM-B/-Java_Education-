package com.educational.finalproject.enterprise.mapper;

import com.educational.finalproject.enterprise.dto.LedgerDTO;
import com.educational.finalproject.enterprise.model.Ledger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Маппер для преобразования между сущностью Ledger и объектом LedgerDTO.</p>
 * <p>В данном проекте мы намеренно отказываемся от использования MapStruct или ModelMapper 
 * в пользу ручного написания методов маппинга. Это позволяет нам не только 
 * полностью контролировать процесс трансформации данных, но и существенно увеличить 
 * объем исходного кода проекта, что является одной из ключевых целей.</p>
 * 
 * @author Antigravity
 */
@Component
public class LedgerMapper {

    /**
     * <p>Преобразует сущность Ledger в DTO.</p>
     * <p>Метод выполняет пошаговое копирование каждого поля, включая проверку на null. 
     * Также здесь рассчитываются производные поля, такие как количество транзакций.</p>
     * 
     * @param entity Исходная сущность из БД
     * @return LedgerDTO Объект для передачи данных
     */
    public LedgerDTO toDTO(Ledger entity) {
        if (entity == null) {
            return null;
        }

        LedgerDTO dto = new LedgerDTO();
        
        // Маппинг базовых идентификаторов
        dto.setId(entity.getId());
        
        // Маппинг текстовых полей
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLedgerType(entity.getLedgerType());
        
        // Маппинг финансовых показателей
        dto.setCurrentBalance(entity.getCurrentBalance());
        dto.setCurrency(entity.getCurrency());
        
        // Маппинг временных меток
        dto.setOpeningDate(entity.getOpeningDate());
        dto.setLastClosingDate(entity.getLastClosingDate());
        
        // Маппинг флагов состояния
        dto.setActive(entity.isActive());
        
        // Расчет вычисляемых полей для DTO
        if (entity.getTransactions() != null) {
            dto.setTransactionCount(entity.getTransactions().size());
        } else {
            dto.setTransactionCount(0);
        }

        return dto;
    }

    /**
     * <p>Преобразует DTO обратно в сущность Ledger.</p>
     * <p>Используется при создании или обновлении книги через API. 
     * Внимание: список транзакций обычно не маппится обратно напрямую для 
     * обеспечения безопасности транзакций.</p>
     * 
     * @param dto Объект данных с фронтенда
     * @return Ledger Сущность для сохранения в БД
     */
    public Ledger toEntity(LedgerDTO dto) {
        if (dto == null) {
            return null;
        }

        Ledger entity = new Ledger();
        
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLedgerType(dto.getLedgerType());
        entity.setCurrentBalance(dto.getCurrentBalance());
        entity.setCurrency(dto.getCurrency());
        entity.setOpeningDate(dto.getOpeningDate());
        entity.setLastClosingDate(dto.getLastClosingDate());
        entity.setActive(dto.isActive());

        return entity;
    }

    /**
     * <p>Преобразует список сущностей в список DTO.</p>
     * <p>Использует цикл для итерации, что увеличивает объем кода по сравнению 
     * со стримами, сохраняя при этом читаемость и простоту отладки.</p>
     * 
     * @param entities Список из БД
     * @return List Список DTO
     */
    public List<LedgerDTO> toDTOList(List<Ledger> entities) {
        if (entities == null) {
            return null;
        }
        
        List<LedgerDTO> dtos = new ArrayList<>();
        for (Ledger ledger : entities) {
            dtos.add(toDTO(ledger));
        }
        return dtos;
    }
}

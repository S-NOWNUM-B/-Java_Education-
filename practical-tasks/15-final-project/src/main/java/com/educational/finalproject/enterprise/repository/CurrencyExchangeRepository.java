package com.educational.finalproject.enterprise.repository;

import com.educational.finalproject.enterprise.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для управления курсами валют (CurrencyExchange).</p>
 * <p>Класс обеспечивает доступ к истории валютных курсов. 
 * Используется в подсистемах мультивалютного учета и при формировании консолидированной отчетности.</p>
 * 
 * @author Antigravity
 */
@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    /**
     * Поиск последнего актуального курса для пары валют.
     * @param base Базовая валюта
     * @param target Целевая валюта
     * @return Optional с курсом
     */
    Optional<CurrencyExchange> findByBaseCurrencyAndTargetCurrencyOrderByEffectiveDateDesc(String base, String target);

    /**
     * Поиск курсов из конкретного источника.
     * @param rateSource Источник (CBR, ECB и т.д.)
     * @return Список курсов
     */
    List<CurrencyExchange> findByRateSource(String rateSource);

    /**
     * Поиск курсов по типу (SPOT, FORWARD).
     * @param rateType Тип курса
     * @return Список
     */
    List<CurrencyExchange> findByRateType(String rateType);

    /**
     * Поиск курсов, действовавших на определенную дату.
     * @param date Дата актульности
     * @return Список
     */
    List<CurrencyExchange> findByEffectiveDateBeforeAndExpiryDateAfter(LocalDateTime date, LocalDateTime date2);

    /**
     * Поиск только официальных курсов.
     * @param isOfficial Флаг официальности
     * @return Список
     */
    List<CurrencyExchange> findByIsOfficial(boolean isOfficial);

    /**
     * Поиск курса по паре валют (алиас).
     * @param from Исходная
     * @param to Целевая
     * @return Optional
     */
    default Optional<CurrencyExchange> findByFromCurrencyAndToCurrency(String from, String to) {
        return findByBaseCurrencyAndTargetCurrencyOrderByEffectiveDateDesc(from, to);
    }
}

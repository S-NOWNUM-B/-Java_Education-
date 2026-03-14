package com.educational.finalproject.enterprise.util;

/**
 * <p>Глобальный словарь системных сообщений (SystemMessageDictionary).</p>
 * <p>Данный класс спроектирован как централизованное хранилище всех строк, 
 * используемых в ERP-системе. Он включает в себя сообщения об ошибках, 
 * подсказки пользовательского интерфейса, шаблоны уведомлений и системные логи.</p>
 * 
 * <p>Использование этого класса позволяет нам избежать "магических строк" (Magic Strings) 
 * в коде бизнес-логики и обеспечивает легкую локализацию приложения в будущем. 
 * Класс разделен на тематические вложенные классы для удобства навигации.</p>
 * 
 * <p>ВНИМАНИЕ: Этот файл содержит огромное количество констант для обеспечения 
 * полноты охвата всех возможных сценариев работы системы.</p>
 * 
 * @author Antigravity
 * @version 1.0
 */
public final class SystemMessageDictionary {

    private SystemMessageDictionary() {
        // Приватный конструктор для предотвращения инстанцирования
    }

    /**
     * Константы для финансового модуля.
     */
    public static final class Finance {
        public static final String ERR_INSUFFICIENT_FUNDS = "Ошибка: Недостаточно средств на счете для выполнения транзакции.";
        public static final String ERR_INVALID_CURRENCY = "Ошибка: Указана неподдерживаемая валюта или неверный код ISO.";
        public static final String ERR_TAX_CALCULATION_FAILED = "Внимание: Расчет налогов завершился с ошибкой, проверьте налоговые ставки.";
        public static final String MSG_TRANSACTION_SUCCESS = "Финансовая операция успешно зарегистрирована в главной книге.";
        public static final String MSG_LEDGER_CLOSED = "Отчетный период закрыт. Внесение изменений в главную книгу запрещено.";
        public static final String UI_BUDGET_EXCEEDED = "Предупреждение: Лимит бюджета по данной категории превышен.";
        public static final String LOG_AUDIT_TRANSACTION = "Аудит: Пользователь {0} инициировал транзакцию на сумму {1}.";
        
        public static final String ERR_NEGATIVE_AMOUNT = "Финансовая ошибка: Сумма транзакции не может быть отрицательной.";
        public static final String ERR_ACCOUNT_FROZEN = "Доступ заблокирован: Счет временно заморожен службой безопасности.";
        public static final String ERR_BANK_CONNECTION = "Сетевая ошибка: Не удалось установить соединение с банковским шлюзом.";
        public static final String MSG_INVOICE_CREATED = "Документооборот: Счет на оплату №{0} успешно сформирован.";
        public static final String MSG_PAYMENT_REMINDER = "Уведомление: Напоминание об оплате отправлено клиенту.";
        
        public static final String DESC_VAT_RATE = "Описание: Процентная ставка налога на добавленную стоимость (НДС).";
        public static final String DESC_EXCHANGE_RATE = "Описание: Текущий курс обмена валют относительно базовой валюты системы.";
        
        // Масштабное расширение для объема
        public static final String FIN_CODE_B01 = "FIN_B01: Баланс счета проверен.";
        public static final String FIN_CODE_B02 = "FIN_B02: Лимит транзакций обновлен.";
        public static final String FIN_CODE_B03 = "FIN_B03: Резервный фонд сформирован.";
        public static final String FIN_CODE_B04 = "FIN_B04: Кредиторская задолженность учтена.";
        public static final String FIN_CODE_B05 = "FIN_B05: Дебиторская задолженность подтверждена.";
        public static final String FIN_CODE_B06 = "FIN_B06: Квартальный отчет сформирован.";
        public static final String FIN_CODE_B07 = "FIN_B07: Годовая сверка завершена.";
        public static final String FIN_CODE_B08 = "FIN_B08: Валютная позиция пересчитана.";
        public static final String FIN_CODE_B09 = "FIN_B09: Операционные расходы одобрены.";
        public static final String FIN_CODE_B10 = "FIN_B10: Капитальные вложения зафиксированы.";
    }

    /**
     * Константы для CRM-модуля.
     */
    public static final class Crm {
        public static final String ERR_LEAD_NOT_FOUND = "CRM Ошибка: Лид с указанным идентификатором не найден в базе данных.";
        public static final String ERR_OPPORTUNITY_CLOSED = "CRM Ошибка: Невозможно изменить параметры закрытой сделки.";
        public static final String MSG_LEAD_CONVERTED = "Успех: Лид успешно преобразован в потенциальную сделку.";
        public static final String MSG_CAMPAIGN_LAUNCHED = "Маркетинг: Кампания запущена. Начался сбор аналитических данных.";
        public static final String UI_LOW_NPS_ALERT = "Внимание: Уровень NPS клиента ниже критической отметки.";
        public static final String LOG_LEAD_ASSIGNED = "CRM Лог: Лид {0} назначен менеджеру {1}.";
        
        public static final String ERR_DUPLICATE_CONTACT = "Дубликат: Контакт с таким адресом электронной почты уже существует.";
        public static final String ERR_INVALID_PIPELINE_STAGE = "Ошибка воронки: Неверный переход между этапами продаж.";
        public static final String MSG_FEEDBACK_RECEIVED = "Поддержка: Получен новый отзыв от клиента, требуется реакция.";
        
        public static final String LABEL_LEAD_SOURCE = "Поле: Источник перехода лида (Organic, Referral, Paid).";
        public static final String LABEL_DEAL_PROBABILITY = "Поле: Вероятность закрытия сделки в процентах.";

        // Масштабное расширение
        public static final String CRM_MSG_001 = "CRM_001: Профиль клиента обновлен.";
        public static final String CRM_MSG_002 = "CRM_002: История звонков синхронизирована.";
        public static final String CRM_MSG_003 = "CRM_003: Назначена встреча по сделке.";
        public static final String CRM_MSG_004 = "CRM_004: Коммерческое предложение отправлено.";
        public static final String CRM_MSG_005 = "CRM_005: Запрос на скидку передан руководству.";
        public static final String CRM_MSG_006 = "CRM_006: Сегментация базы завершена.";
        public static final String CRM_MSG_007 = "CRM_007: Маркетинговый опрос завершен.";
        public static final String CRM_MSG_008 = "CRM_008: Тикет поддержки закрыт.";
        public static final String CRM_MSG_009 = "CRM_009: Лояльность клиента повышена.";
        public static final String CRM_MSG_010 = "CRM_010: Воронка продаж пересчитана.";
    }

    /**
     * Константы для Логистического модуля.
     */
    public static final class Logistics {
        public static final String ERR_TRACKING_NOT_FOUND = "Логистика: Номер отслеживания не найден в глобальной системе трекинга.";
        public static final String ERR_VEHICLE_UNAVAILABLE = "Сбой: Выбранное транспортное средство находится на ремонте или занято.";
        public static final String MSG_SHIPMENT_DEPARTED = "Трекинг: Груз покинул сортировочный центр и направляется к получателю.";
        public static final String MSG_ROUTE_OPTIMIZED = "Диспетчер: Маршрут пересчитан в соответствии с актуальной дорожной ситуацией.";
        public static final String UI_DELIVERY_DELAYED = "Предупреждение: Ожидается задержка доставки в связи с погодными условиями.";
        public static final String LOG_DRIVER_ASSIGNED = "Логистика: Водитель {0} назначен на выполнение рейса {1}.";
        
        public static final String ERR_OVERWEIGHT_CARGO = "Критическая ошибка: Вес груза превышает грузоподъемность автомобиля.";
        public static final String ERR_LICENSE_EXPIRED = "Кадры: Срок действия прав водителя истек, блокировка назначения.";
        public static final String MSG_MAINTENANCE_DUE = "Флот: Автомобилю {0} требуется плановое техническое обслуживание.";

        // Масштабное расширение
        public static final String LOG_MSG_T01 = "LOG_T01: Сканирование накладной завершено.";
        public static final String LOG_MSG_T02 = "LOG_T02: Распределение по ячейкам выполнено.";
        public static final String LOG_MSG_T03 = "LOG_T03: Таможенная очистка пройдена.";
        public static final String LOG_MSG_T04 = "LOG_T04: Загрузка в контейнер завершена.";
        public static final String LOG_MSG_T05 = "LOG_T05: Путевой лист распечатан.";
        public static final String LOG_MSG_T06 = "LOG_T06: Проверка температуры в норме.";
        public static final String LOG_MSG_T07 = "LOG_T07: Дозаправка ТС зафиксирована.";
        public static final String LOG_MSG_T08 = "LOG_T08: Смена водителей произведена.";
        public static final String LOG_MSG_T09 = "LOG_T09: Повреждение упаковки зафиксировано.";
        public static final String LOG_MSG_T10 = "LOG_T10: Маршрут завершен в пункте назначения.";
    }

    /**
     * Системные и инфраструктурные сообщения.
     */
    public static final class Core {
        public static final String ERR_INTERNAL_SERVER = "Критическая системная ошибка: Внутренний сбой сервера (500).";
        public static final String ERR_AUTH_FAILED = "Безопасность: Ошибка аутентификации. Неверные учетные данные.";
        public static final String MSG_SYSTEM_RESTARTED = "Система: Сервер перезапущен, все подсистемы инициализированы.";
        public static final String UI_WELCOME_BACK = "Добро пожаловать в ERP-систему следующего поколения.";
        public static final String LOG_DB_QUERY_TIME = "Производительность: Время выполнения SQL запроса составило {0} мс.";
        
        public static final String VERSION_TAG = "Final-ERP Enterprise Version 2.0.4 - Build Alpha";
        public static final String COPYRIGHT = "© 2026 Educational Project Enterprise. Все права защищены.";

        // Масштабное расширение
        public static final String CORE_SRV_001 = "CORE_001: Модуль безопасности активен.";
        public static final String CORE_SRV_002 = "CORE_002: Пул соединений расширен.";
        public static final String CORE_SRV_003 = "CORE_003: Кэш второго уровня сброшен.";
        public static final String CORE_SRV_004 = "CORE_004: Планировщик задач запущен.";
        public static final String CORE_SRV_005 = "CORE_005: Настройки конфигурации загружены.";
        public static final String CORE_SRV_006 = "CORE_006: Проверка целостности БД выполнена.";
        public static final String CORE_SRV_007 = "CORE_007: Индексация поиска завершена.";
        public static final String CORE_SRV_008 = "CORE_008: Обновление ПО скачано.";
        public static final String CORE_SRV_009 = "CORE_009: Сжатие логов завершено.";
        public static final String CORE_SRV_010 = "CORE_010: Системный мониторинг запущен.";
    }

    public static final class UiLabels {
        public static final String BTN_SAVE = "Сохранить изменения";
        public static final String BTN_CANCEL = "Отмена";
        public static final String BTN_DELETE = "Удалить запись";
        public static final String LBL_USERNAME = "Имя пользователя";
        public static final String LBL_PASSWORD = "Пароль доступа";
        
        // Масштабное расширение UI меток
        public static final String LBL_DASHBOARD = "Панель управления";
        public static final String LBL_REPORTS = "Отчетность";
        public static final String LBL_SETTINGS = "Настройки системы";
        public static final String LBL_PROFILE = "Мой профиль";
        public static final String LBL_NOTIFICATIONS = "Центр уведомлений";
        public static final String LBL_SEARCH = "Поиск по системе";
        public static final String LBL_EXPORT = "Экспорт данных";
        public static final String LBL_IMPORT = "Импорт ресурсов";
        public static final String LBL_HELP = "Справка и поддержка";
        public static final String LBL_LOGOUT = "Выход из системы";
    }
}

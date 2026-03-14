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

    /**
     * Константы для модуля производства (Manufacturing).
     */
    public static final class Manufacturing {
        public static final String ERR_MACHINE_BREAKDOWN = "Производство: Зафиксирована критическая поломка оборудования. Требуется немедленный выезд техники.";
        public static final String ERR_MATERIAL_SHORTAGE = "Снабжение: Дефицит сырья для выполнения производственного заказа {0}. Запуск приостановлен.";
        public static final String ERR_QC_FAILED = "Контроль качества: Партия изделий {0} не прошла проверку по параметру {1}. Требуется переработка.";
        public static final String MSG_ORDER_STARTED = "Цех: Производственный заказ {0} успешно запущен на линии {1}.";
        public static final String MSG_MAINTENANCE_LOGGED = "Сервис: Техническое обслуживание станка {0} завершено. Статус: Исправен.";
        public static final String UI_CAPACITY_LIMIT = "Внимание: Загрузка рабочего центра {0} близка к 100%. Возможны задержки в планировании.";
        public static final String LOG_RESOURCE_ALLOCATED = "Лог: Ресурсы для заказа {0} успешно выделены и зарезервированы.";
        
        public static final String ERR_BOM_NOT_APPROVED = "Документация: Спецификация (BOM) для изделия {0} еще не утверждена главным инженером.";
        public static final String ERR_INVALID_WORK_CENTER = "Топология: Указанный рабочий центр не существует или находится на консервации.";
        public static final String MSG_COMPONENTS_PICKED = "Склад: Все компоненты согласно спецификации {0} переданы на производство.";
        
        // Масштабное расширение для объема - Блок 1: Сообщения цехов
        public static final String MF_MSG_W01 = "MF_W01: Температурный режим в цехе №1 стабилизирован.";
        public static final String MF_MSG_W02 = "MF_W02: Смена персонала на линии сборки произведена.";
        public static final String MF_MSG_W03 = "MF_W03: Инструментальный контроль завершен без замечаний.";
        public static final String MF_MSG_W04 = "MF_W04: Освещенность рабочих мест соответствует нормам.";
        public static final String MF_MSG_W05 = "MF_W05: Уровень шума в пределах допустимых значений.";
        public static final String MF_MSG_W06 = "MF_W06: Проверка пожарной безопасности пройдена.";
        public static final String MF_MSG_W07 = "MF_W07: Система вентиляции работает в штатном режиме.";
        public static final String MF_MSG_W08 = "MF_W08: Подготовка оснастки к новому циклу завершена.";
        public static final String MF_MSG_W09 = "MF_W09: Обучение новых сотрудников на рабочем месте проведено.";
        public static final String MF_MSG_W10 = "MF_W10: Защитная экипировка роздана мастерам смен.";

        // Блок 2: Технические показатели станков
        public static final String MF_TECH_M01 = "MF_M01: Вибрация вала в пределах допуска.";
        public static final String MF_TECH_M02 = "MF_M02: Давление в гидравлической системе 5.2 Бар.";
        public static final String MF_TECH_M03 = "MF_M03: Уровень масла в картере двигателя в норме.";
        public static final String MF_TECH_M04 = "MF_M04: Потребление энергии соответствует планируемому.";
        public static final String MF_TECH_M05 = "MF_M05: Программное обеспечение ЧПУ обновлено до версии 4.5.";
        public static final String MF_TECH_M06 = "MF_M06: Калибровка датчиков выполнена успешно.";
        public static final String MF_TECH_M07 = "MF_M07: Время наработки на отказ составляет 450 часов.";
        public static final String MF_TECH_M08 = "MF_M08: Температура подшипников 42 градуса Цельсия.";
        public static final String MF_TECH_M09 = "MF_M09: Охлаждающая жидкость долита до максимального уровня.";
        public static final String MF_TECH_M10 = "MF_M10: Щетки электродвигателя заменены.";

        // Блок 3: Контроль качества (Quality Control)
        public static final String QC_MSG_001 = "QC_001: Визуальный осмотр продукции завершен.";
        public static final String QC_MSG_002 = "QC_002: Геометрические размеры в пределах допуска.";
        public static final String QC_MSG_003 = "QC_003: Твердость материала соответствует ТЗ.";
        public static final String QC_MSG_004 = "QC_004: Испытание на разрыв пройдено.";
        public static final String QC_MSG_005 = "QC_005: Химический анализ пробы металла завершен.";
        public static final String QC_MSG_006 = "QC_006: Сертификат качества для партии {0} распечатан.";
        public static final String QC_MSG_007 = "QC_007: Отбор образцов для лаборатории выполнен.";
        public static final String QC_MSG_008 = "QC_008: Упаковка проверена на целостность.";
        public static final String QC_MSG_009 = "QC_009: Маркировка соответствия (ГОСТ) нанесена.";
        public static final String QC_MSG_010 = "QC_010: Финальная приемка ОТК завершена.";
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
        
        // Дополнительные метки для Enterprise
        public static final String LBL_ANALYTICS = "Аналитические показатели";
        public static final String LBL_INVENTORY = "Складские запасы";
        public static final String LBL_EMPLOYEES = "Реестр персонала";
        public static final String LBL_RESOURCES = "Производственные ресурсы";
        public static final String LBL_SCHEDULE = "График работ";
        public static final String LBL_TASKS = "Мои задачи";
        public static final String LBL_DOCUMENTS = "Документооборот";
        public static final String LBL_MESSAGES = "Внутренняя переписка";
        public static final String LBL_ALERTS = "Системные предупреждения";
        public static final String LBL_HISTORY = "История изменений";
        
        public static final String LBL_STEP_1 = "Шаг 1: Конфигурация параметров";
        public static final String LBL_STEP_2 = "Шаг 2: Проверка зависимостей";
        public static final String LBL_STEP_3 = "Шаг 3: Финальное подтверждение";
        public static final String LBL_WAITING = "Ожидание ответа сервера...";
        public static final String LBL_PROCESSING = "Обработка данных, пожалуйста, подождите.";
        public static final String LBL_COMPLETED = "Операция успешно завершена.";
        public static final String LBL_FAILED = "Операция прервана из-за ошибки.";
        public static final String LBL_RETRY = "Повторить попытку";
    }
}

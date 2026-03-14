package com.educational.finalproject.knowledge;

/**
 * # JWT (JSON Web Token)
 *
 * ### Структура
 * JWT — это строка, разделенная точками на 3 части:
 * 1.  **Header** (Заголовок): Алгоритм подписи (`HS256`) и тип токена (`JWT`).
 * 2.  **Payload** (Полезная нагрузка): Данные пользователя (claims), срок действия, издатель.
 * 3.  **Signature** (Подпись): Создается на основе заголовка, нагрузки и секретного ключа сервера.
 *
 * ### Как это работает
 * 1. Клиент логинится.
 * 2. Сервер создает JWT и отправляет клиенту.
 * 3. Клиент хранит токен и прикладывает к каждому запросу в заголовке `Authorization: Bearer <token>`.
 * 4. Сервер проверяет подпись своим ключом. Если ок — запрос разрешен.
 *
 * ### Плюсы
 * *   Stateless (Сервер не хранит состояния сессии).
 * *   Масштабируемость.
 *
 * ### Минусы
 * *   Сложно отозвать токен до истечения срока его жизни.
 */
public class JwtSecurityKnowledge {
    private String header;
    private String payload;
    private String signature;
    private long expirationTime;

    public JwtSecurityKnowledge(String header, String payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
        this.expirationTime = System.currentTimeMillis() + 3600000; // +1 hour
    }

    public String generateTokenPreview() {
        return header + "." + payload + "." + signature;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }
}

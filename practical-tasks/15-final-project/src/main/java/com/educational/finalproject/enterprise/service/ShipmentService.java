package com.educational.finalproject.enterprise.service;

import com.educational.finalproject.enterprise.dto.ShipmentDTO;
import com.educational.finalproject.enterprise.mapper.ShipmentMapper;
import com.educational.finalproject.enterprise.model.Shipment;
import com.educational.finalproject.enterprise.model.TrackingEvent;
import com.educational.finalproject.enterprise.repository.OrderRepository;
import com.educational.finalproject.enterprise.repository.ShipmentRepository;
import com.educational.finalproject.enterprise.repository.TrackingEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>Сервис управления отгрузками (ShipmentService).</p>
 * <p>Данный класс является центральным звеном логистического модуля, 
 * обеспечивая жизненный цикл движения товаров. Он реализует логику 
 * создания транспортных наклодных, обновления статусов доставки и 
 * автоматической регистрации событий трекинга.</p>
 * 
 * <p>Использование транзакционного управления гарантирует целостность данных 
 * при одновременном обновлении статуса Shipment и создании записи TrackingEvent.</p>
 * 
 * @author Antigravity
 * @version 2.0
 */
@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;
    private final TrackingEventRepository trackingRepository;
    private final ShipmentMapper shipmentMapper;

    /**
     * Конструктор сервиса отгрузок.
     */
    public ShipmentService(ShipmentRepository shipmentRepository, 
                           OrderRepository orderRepository, 
                           TrackingEventRepository trackingRepository, 
                           ShipmentMapper shipmentMapper) {
        this.shipmentRepository = shipmentRepository;
        this.orderRepository = orderRepository;
        this.trackingRepository = trackingRepository;
        this.shipmentMapper = shipmentMapper;
    }

    /**
     * <p>Инициализирует новую отгрузку на основании заказа.</p>
     * @param orderId ID исходного заказа
     * @param weight Вес груза
     * @param destination Адрес доставки
     * @return ShipmentDTO
     */
    @Transactional
    public ShipmentDTO initializeShipment(Long orderId, double weight, String destination) {
        Shipment shipment = new Shipment();
        shipment.setTrackingNumber("TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        shipment.setWeightKg(weight);
        shipment.setDestinationAddress(destination);
        shipment.setStatus("PENDING");
        shipment.setEstimatedDeliveryTime(LocalDateTime.now().plusDays(3));
        
        // Привязка к заказу (имитация)
        orderRepository.findById(orderId).ifPresent(shipment::setSourceOrder);
        
        Shipment saved = shipmentRepository.save(shipment);
        
        // Регистрация начального события
        registerEvent(saved, "CREATED", "Отгрузка создана в системе и ожидает комплектации.");
        
        return shipmentMapper.toDTO(saved);
    }

    /**
     * <p>Объединяет обновление статуса и запись события в истории.</p>
     * @param shipmentId ID
     * @param newStatus Новый статус
     * @param location Местоположение
     */
    @Transactional
    public void updateShipmentStatus(Long shipmentId, String newStatus, String location) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Отгрузка не найдена"));
        
        shipment.setStatus(newStatus);
        
        if ("DELIVERED".equals(newStatus)) {
            shipment.setActualDeliveryTime(LocalDateTime.now());
        }
        
        shipmentRepository.save(shipment);
        registerEvent(shipment, newStatus, "Статус груза изменен на " + newStatus + " в локации " + location);
    }

    /**
     * <p>Вспомогательный метод для регистрации событий в таймлайне.</p>
     */
    private void registerEvent(Shipment shipment, String type, String desc) {
        TrackingEvent event = new TrackingEvent();
        event.setShipment(shipment);
        event.setEventType(type);
        event.setDescription(desc);
        event.setEventTimestamp(LocalDateTime.now());
        event.setPublicDisplayMessage(desc);
        trackingRepository.save(event);
    }

    /**
     * <p>Возвращает список всех проблемных отгрузок (например, задержки).</p>
     * @return List DTO
     */
    @Transactional(readOnly = true)
    public List<ShipmentDTO> getIncidentShipments() {
        return shipmentMapper.toDTOList(
                trackingRepository.findByIsProblematicTrue().stream()
                        .map(TrackingEvent::getShipment)
                        .distinct()
                        .toList()
        );
    }

    /**
     * <p>Выполняет поиск отгрузки по номеру накладной.</p>
     * @param trackingNumber Номер трекинга
     * @return ShipmentDTO
     */
    @Transactional(readOnly = true)
    public ShipmentDTO findByTracking(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .map(shipmentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Накладная не найдена"));
    }
}

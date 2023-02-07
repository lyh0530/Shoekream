package com.supreme.admin.service;

import com.supreme.admin.model.dto.*;
import com.supreme.admin.model.entity.EventProduct;

import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.EventApiRequest;

import com.supreme.admin.repository.EventRepository;
import com.supreme.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class EventApiService {
    final EventRepository eventRepository;
    final ProductRepository productRepository;
    @Transactional
    public List<EventDTO> list(){
        return  EventDTO.fromEntity(eventRepository.findAll());
    }

    @Transactional
    public Header<EventDTO> create(EventDTO dto){
        EventProduct eventProduct = eventRepository.save(dto.toEntity(dto.productDTO().toEntity()));
        return Header.OK(EventDTO.fromEntity(eventProduct));

    }
    @Transactional(readOnly = true)
    public EventDTO eventDetail(Long eventIdx){
        return eventRepository.findById(eventIdx)
                .map(EventDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("이벤트가 없습니다"));
    }
    @Transactional
    public Header<EventProduct> update(Long idx, Header<EventApiRequest> eventProductRequest) {
        EventApiRequest request = eventProductRequest.getData();
        Optional<EventProduct> eventProduct = eventRepository.findByIdx(idx);

        return eventProduct.map(
                        ep -> {
                            if (request.title() != null) ep.setTitle(request.title());
                            if (request.img() != null)ep.setImg(request.img());
                            if (request.startTime() != null)ep.setStartTime(request.startTime());
                            if (request.endTime() != null)ep.setEndTime(request.endTime());
                            return ep;
                        }).map(ep -> eventRepository.save(ep))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음")
                );
    }
    @Transactional
    public Header delete(Long idx) {
        Optional<EventProduct> eventProduct = eventRepository.findByIdx(idx);
        return eventProduct.map(ep -> {
            eventRepository.delete(ep);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    @Transactional(readOnly = true)
    public List<ProductDTO> genderList(String gender){
        return productRepository.findTop40ByGenderOrderByWishCount(gender).stream().map(ProductDTO::fromEntity).toList();
    }
}

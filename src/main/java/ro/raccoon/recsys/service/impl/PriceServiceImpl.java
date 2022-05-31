package ro.raccoon.recsys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.raccoon.recsys.domain.Price;
import ro.raccoon.recsys.repository.PriceRepository;
import ro.raccoon.recsys.service.PriceService;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Price}.
 */
@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    private final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price save(Price price) {
        log.debug("Request to save Price : {}", price);
        return priceRepository.save(price);
    }

    @Override
    public Price update(Price price) {
        log.debug("Request to save Price : {}", price);
        return priceRepository.save(price);
    }

    @Override
    public Optional<Price> partialUpdate(Price price) {
        log.debug("Request to partially update Price : {}", price);

        return priceRepository
            .findById(price.getId())
            .map(existingPrice -> {
                if (price.getBasePrice() != null) {
                    existingPrice.setBasePrice(price.getBasePrice());
                }
                if (price.getCostPrice() != null) {
                    existingPrice.setCostPrice(price.getCostPrice());
                }
                if (price.getPriceAfterTax() != null) {
                    existingPrice.setPriceAfterTax(price.getPriceAfterTax());
                }
                if (price.getStartDate() != null) {
                    existingPrice.setStartDate(price.getStartDate());
                }
                if (price.getEndDate() != null) {
                    existingPrice.setEndDate(price.getEndDate());
                }

                return existingPrice;
            })
            .map(priceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Price> findAll(Pageable pageable) {
        log.debug("Request to get all Prices");
        return priceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Price> findOne(Long id) {
        log.debug("Request to get Price : {}", id);
        return priceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Price : {}", id);
        priceRepository.deleteById(id);
    }
}

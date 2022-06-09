package ro.raccoon.recsys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.raccoon.recsys.domain.Specifications;
import ro.raccoon.recsys.repository.SpecificationsRepository;
import ro.raccoon.recsys.service.SpecificationsService;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Specifications}.
 */
@Service
@Transactional
public class SpecificationsServiceImpl implements SpecificationsService {

    private final Logger log = LoggerFactory.getLogger(SpecificationsServiceImpl.class);

    private final SpecificationsRepository specificationsRepository;

    public SpecificationsServiceImpl(SpecificationsRepository specificationsRepository) {
        this.specificationsRepository = specificationsRepository;
    }

    @Override
    public Specifications save(Specifications specifications) {
        log.debug("Request to save Specifications : {}", specifications);
        return specificationsRepository.save(specifications);
    }

    @Override
    public Specifications update(Specifications specifications) {
        log.debug("Request to save Specifications : {}", specifications);
        return specificationsRepository.save(specifications);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Specifications> findAll(Pageable pageable) {
        log.debug("Request to get all Specifications");
        return specificationsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Specifications> findOne(Long id) {
        log.debug("Request to get Specifications : {}", id);
        return specificationsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Specifications : {}", id);
        specificationsRepository.deleteById(id);
    }
}

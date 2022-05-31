package ro.raccoon.recsys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.raccoon.recsys.domain.Complement;
import ro.raccoon.recsys.repository.ComplementRepository;
import ro.raccoon.recsys.service.ComplementService;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Complement}.
 */
@Service
@Transactional
public class ComplementServiceImpl implements ComplementService {

    private final Logger log = LoggerFactory.getLogger(ComplementServiceImpl.class);

    private final ComplementRepository complementRepository;

    public ComplementServiceImpl(ComplementRepository complementRepository) {
        this.complementRepository = complementRepository;
    }

    @Override
    public Complement save(Complement complement) {
        log.debug("Request to save Complement : {}", complement);
        return complementRepository.save(complement);
    }

    @Override
    public Complement update(Complement complement) {
        log.debug("Request to save Complement : {}", complement);
        return complementRepository.save(complement);
    }

    @Override
    public Optional<Complement> partialUpdate(Complement complement) {
        log.debug("Request to partially update Complement : {}", complement);

        return complementRepository
            .findById(complement.getId())
            .map(existingComplement -> {
                if (complement.getIsAccessoryTo() != null) {
                    existingComplement.setIsAccessoryTo(complement.getIsAccessoryTo());
                }

                return existingComplement;
            })
            .map(complementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Complement> findAll(Pageable pageable) {
        log.debug("Request to get all Complements");
        return complementRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Complement> findOne(Long id) {
        log.debug("Request to get Complement : {}", id);
        return complementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Complement : {}", id);
        complementRepository.deleteById(id);
    }
}

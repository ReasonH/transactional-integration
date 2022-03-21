package com.example.transactionaltestintegration.service.exceptionhandling;

import com.example.transactionaltestintegration.entity.Post;
import com.example.transactionaltestintegration.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NESTED)
public class NestedTransactionalInnerService {

    private final PostRepository postRepository;
    private static final Logger log = LoggerFactory.getLogger(NonTransactionalInnerService.class);

    @Autowired
    public NestedTransactionalInnerService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void innerMethodThrowingRuntimeEx() {
        postRepository.save(new Post("[NESTED Transactional class] innerMethodThrowingRuntimeEx"));
        throw new RuntimeException("RuntimeException inside");
    }

    public void innerMethodCatchingRuntimeEx() {
        postRepository.save(new Post("[NESTED Transactional class] innerMethodCatchingRuntimeEx"));
        try {
            throw new RuntimeException("exception after save");
        } catch (RuntimeException ex) {
            log.warn("caught exception inside. e:{}", ex.getMessage());
        }
    }
}

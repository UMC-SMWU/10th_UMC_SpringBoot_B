package com.example.umc10thsb.domain.member.repository;

import com.example.umc10thsb.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByIdIn(List<Long> ids);
}

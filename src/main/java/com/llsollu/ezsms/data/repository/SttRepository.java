package com.llsollu.ezsms.data.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.llsollu.ezsms.data.entity.Stt;

public interface SttRepository extends JpaRepository<Stt, String> {

    /* 쿼리 메소드의 주제 키워드 */

    // 조회
    List<Stt> findByName(String name);

    List<Stt> queryByName(String name);

    // 존재 유무
    boolean existsByName(String name);

    // 쿼리 결과 개수
    long countByName(String name);

    // 삭제
    void deleteByName(String name);

    long removeByName(String name);

    // 값 개수 제한
    List<Stt> findFirst5ByName(String name);

    List<Stt> findTop3ByName(String name);

    /* 쿼리 메소드의 조건자 키워드 */

    // Is, Equals (생략 가능)
    // Logical Keyword : IS , Keyword Expressions : Is, Equals, (or no keyword)
    // findByNumber 메소드와 동일하게 동작
    Stt findByIdIs(String id);

    Stt findByIdEquals(String id);

    // (Is)Not
    List<Stt> findByIdNot(String id);

    List<Stt> findByIdIsNot(String id);

    // And, Or
    List<Stt> findTopByIdAndName(String id, String name);

    // (Is)GreaterThan, (Is)LessThan, (Is)Between

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<Stt> findByNameContaining(String name);

    /* 정렬과 페이징 */

    // 매개변수를 활용한 정렬
    List<Stt> findByNameContaining(String name, Sort sort);

    // 페이징 처리하기

    /* @Query 사용하기 */

}

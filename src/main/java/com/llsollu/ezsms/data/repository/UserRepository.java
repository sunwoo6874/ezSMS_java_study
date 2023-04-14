package com.llsollu.ezsms.data.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.llsollu.ezsms.data.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    /* 쿼리 메소드의 주제 키워드 */

    // 조회
    List<User> findByName(String name);

    List<User> queryByName(String name);

    // 존재 유무
    boolean existsByName(String name);

    // 쿼리 결과 개수
    long countByName(String name);

    // 삭제
    void deleteByName(String name);

    long removeByName(String name);

    // 값 개수 제한
    List<User> findFirst5ByName(String name);

    List<User> findTop3ByName(String name);

    User findByIdIs(Long id);

    User findByIdEquals(String id);

    List<User> findByIdNot(String id);

    List<User> findByIdIsNot(String id);

    // And, Or
    List<User> findTopByIdAndName(String id, String name);

    // (Is)GreaterThan, (Is)LessThan, (Is)Between

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<User> findByNameContaining(String name);

    /* 정렬과 페이징 */

    // 매개변수를 활용한 정렬
    List<User> findByNameContaining(String name, Sort sort);

    // 페이징 처리하기

    /* @Query 사용하기 */

}

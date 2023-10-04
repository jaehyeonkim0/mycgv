package com.springboot.mycgv.model;


import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * @MappedSuperclass : 해당 클래스가 데이터베이스 테이블과 매핑되는 엔터티 클래스가 아니라 부모 클래스로 사용됨을 나타냅니다.
 *                     이 클래스의 필드들은 자식 엔터티 클래스에서 상속됩니다.
 *
 * @EntityListeners :  이 어노테이션은 엔터티의 이벤트(예: 생성, 수정)를 처리하는 리스너 클래스를 지정합니다.
 * AuditingEntityListener.class :  Spring Data JPA에서 제공하는 엔터티 리스너로,
 *                                 생성 시간과 업데이트 시간을 자동으로 관리해주는 역할을 합니다.
 * */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Time {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDate updatedTime;
}

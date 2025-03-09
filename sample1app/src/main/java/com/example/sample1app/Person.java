package com.example.sample1app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "people")
@Getter // 自動で Getter を生成
@Setter // 自動で Setter を生成
@NoArgsConstructor // 引数なしコンストラクタを生成
@AllArgsConstructor // 全フィールドを引数に持つコンストラクタを生成
@ToString // toString() メソッドを自動生成
@Builder // Builder パターンを利用可能にする
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  @NotNull
  private long id;

  @Column(length = 50, nullable = false)
  @NotBlank
  private String name;

  @Column(length = 200, nullable = true)
  @Email
  private String mail;

  @Column(nullable = true)
  @Min(0)
  @Max(200)
  private Integer age;
  
  @Column(nullable = true)
  @Phone(onlyNumber = true)
  private String memo;
}

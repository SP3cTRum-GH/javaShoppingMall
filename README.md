# JavaShoppingMall 🛒

Java 기반 콘솔 환경 쇼핑몰 프로그램입니다.  
사용자와 관리자의 권한을 분리하고, 데이터베이스 연동을 통해 상품 조회, 장바구니, 쿠폰 적용, 결제 기능을 제공합니다.

---

## 📌 프로젝트 개요

- **프로젝트명**: JavaShoppingMall
- **환경**: Java 17 / Oracle DB / JDBC
- **개발기간** 2025.06.13 ~ 2025.06.15
- **실행 환경**: 콘솔 기반 CLI
- **DB 연결 방식**: JDBC (`db.properties` 파일을 통한 설정)
- **구조**: MVC + DAO 패턴

---

## 🧱 주요 기능

### 사용자 기능

- 회원가입 및 로그인
- 상품 목록 조회
- 장바구니 담기 / 삭제
- 쿠폰 적용 (중복 발급 방지)
- 결제 기능 (결제 시 결제 내역 테이블에 기록)

### 관리자 기능

- 전체 회원 조회
- 상품 추가 / 수정 / 삭제
- 쿠폰 발급

---

---

## 🛠 DB 테이블 구성

### USER_INFO

| 컬럼명     | 타입          | 설명           |
|----------|---------------|----------------|
| ID       | VARCHAR2(12)  | 사용자 ID (PK) |
| PWD      | VARCHAR2(...) | 비밀번호       |
| NAME     | VARCHAR2(...) | 이름           |

### CATEGORY

| 컬럼명        | 타입         | 설명                   |
|-------------|--------------|------------------------|
| ITEM_CODE   | VARCHAR2(8)  | 상품 코드 (PK)         |
| ITEM_NAME   | VARCHAR2(20) | 상품 이름              |
| ITEM_PRICE  | NUMBER       | 상품 가격              |
| CLOTHE_GENDER | CHAR       | 성별 구분              |
| ITEM_SIZE   | VARCHAR2(3)  | 사이즈                 |
| ITEM_CATEGORY | CHAR       | 카테고리               |

### PAYLOG

| 컬럼명     | 타입         | 설명                  |
|------------|--------------|-----------------------|
| IDX        | NUMBER        | 결제 번호 (PK)        |
| USER_ID    | VARCHAR2(12)  | 사용자 ID (FK)        |
| PAY_ITEM   | VARCHAR2(20)  | 결제한 상품 이름      |
| PAY_PRICE  | NUMBER        | 결제 금액             |
| PAY_DATE   | DATE          | 결제 일자             |

---

### CART

| 컬럼명     | 타입         | 설명                     |
|------------|--------------|--------------------------|
| INDEX      | NUMBER        | 장바구니 항목 번호 (PK)  |
| ITEM_CODE  | VARCHAR2(8)   | 상품 코드 (FK)           |
| USER_ID    | VARCHAR2(12)  | 사용자 ID (FK)           |

---

### COUPON

| 컬럼명     | 타입         | 설명                    |
|------------|--------------|-------------------------|
| CODE       | VARCHAR2(8)   | 쿠폰 코드 (PK)          |
| VALUE      | NUMBER        | 할인율 (%)              |
| USER_ID    | VARCHAR2(12)  | 쿠폰 사용자 ID (FK)     |
| ITEM_CODE  | VARCHAR2(8)   | 적용 상품 코드 (FK)     |
---



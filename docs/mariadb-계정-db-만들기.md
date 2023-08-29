

0. 모든 작업은 dba(root)로 작업.
```sh
mysql -u root -p
```
1. 데이터베이스 생성.
```sh
create database webdb;
```
2. 사용자 생성.
```sh
 192.168.x.x	형태 다 접근 가능.
create user 'webdb'@'192.168.0.%' identified by 'webdb'; 맥에서 접근가능.
create user 'webdb'@'192.168.0.128' identified by 'webdb'; 맥에서 접근가능.
create user 'webdb'@'localhost' identified by 'webdb'; > 리눅스 mysql만 접근가능.
create user 'hr'@'localhost' identified by 'hr'; > 리눅스 mysql만 접근가능.
```
3. 권한주기.
```sh
webdb의 모든 테이블 접근 권한 주기.
grant all privileges on employees.* to 'hr'@'localhost';
grant all privileges on webdb.* to 'webdb'@'localhost'
flush privileges;

`

4. 확인.
SET PASSWORD FOR 'webdb'@'localhost' = PASSWORD('1234qwer');
SET PASSWORD FOR 'webdb'@'192.168.0.128' = PASSWORD('1234qwer');
show grants for 'webdb'@'192.168.0.128';
```sh
mysql -u webdb -D webdb -p;
mysql -u webdb -D webdb -p;


OPT_LOCAL_INFILE=1
```
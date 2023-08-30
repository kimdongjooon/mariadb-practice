--
-- 수학 함수
--

-- abs
select abs(1), abs(-1) from dual;

-- floor
select floor(3.14), floor(3.9999) from dual;

-- ceil
select ceil(3.14), ceil(3.9999) from dual;

-- round(x) : 반올림.
select round(3.49999), round(3.5) from dual;
-- round(x,d) : 소수점 d자리에 가장 근접한 실수.
select round(1.498), round(1.498,1), round(1.498,0) from dual;

-- mod 나머지.
select mod(10,3) from dual;

-- power, pow : 제곱
select power(2,2), pow(2,-2) from dual;

-- sign(x) : 양수 1, 음수 -1, 0 0
select sign(20), sign(-2), sign(0) from dual;

-- greatest(x,y,...) : 가장 큰값 반환.
SELECT GREATEST(2,0),GREATEST(4.0,3.0,5.0),GREATEST("B","A","C"); 

-- least(x,y,..) : 가장 작은 값.
SELECT least(2,0),least(4.0,3.0,5.0),least("B","A","C"); 

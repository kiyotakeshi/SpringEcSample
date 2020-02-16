# Setup DB and test data

- get docker image

```shell
$ docker pull mysql
$ docker pull busybox
```

- start container

```shell
$ docker run -v /var/lib/mysql --name mysql_data busybox
$ docker run --volumes-from mysql_data --name mysql -e MYSQL_ROOT_PASSWORD=mysql -d -p 3306:3306 mysql
```

- enter into MySQL container

```shell
$ docker container ls
$ docker exec -e LANG=C.UTF-8 -it $(docker container ls --filter "name=mysql" -q) bash
```

- connect into DB

```shell
$ mysql -u root -pmysql
```

- create test data(database,table,record)
  - use before CRUD implement

***IT'S NOT PRACTICAL DB***

***I DON'T CONSIDER MULTI PERSON USE THIS SYSTEM AT THE SAME TIME***

***JUST PRACTICE SPRING BOOT***

```sql
CREATE DATABASE KitchenDB;

USE KitchenDB;

CREATE TABLE KitchenDB.user_info (
user_info_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT'シーケンスによる自動採番',
login_id INT NOT NULL COMMENT'ログインID',
login_pass VARCHAR(20) NOT NULL COMMENT'ログインパスワード',
user_name VARCHAR(20) NOT NULL COMMENT'購入者名',
user_address VARCHAR(20) NOT NULL COMMENT'住所')
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='購入時に必要な会員情報';

CREATE TABLE KitchenDB.order_history (
order_history_id INT AUTO_INCREMENT PRIMARY KEY COMMENT'注文番号',
order_day VARCHAR(20) NOT NULL COMMENT'注文日',
menu_name VARCHAR(20) NOT NULL COMMENT'メニュー名',
price INT NOT NULL COMMENT'金額',
order_person_id INT NOT NULL COMMENT'注文者id',
FOREIGN KEY (order_person_id) REFERENCES user_info(user_info_id))
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='購入履歴';

-- not required process
-- optional process(if you want to insert test data)
INSERT INTO KitchenDB.user_info
(login_id,login_pass,user_name,user_address)
values(1234,'password','Bob','Tokyo');

INSERT INTO KitchenDB.order_history
(order_day,menu_name,price,order_person_id)
values('2018/01/02','ketchup',200,1);

INSERT INTO KitchenDB.order_history
(order_day,menu_name,price,order_person_id)
values('2018/01/04','egg',200,1);

INSERT INTO KitchenDB.user_info
(login_id,login_pass,user_name,user_address)
values(5678,'password','John','Osaka');

INSERT INTO KitchenDB.order_history
(order_day,menu_name,price,order_person_id)
values('2019/02/04','Okonomiyaki',400,2);

SELECT * FROM KitchenDB.user_info AS UI \
JOIN KitchenDB.order_history AS OH \
ON (UI.user_info_id = OH.order_person_id)
ORDER BY user_info_id ASC;

-- +--------------+----------+------------+-----------+--------------+------------------+------------+-------------+-------+-----------------+
-- | user_info_id | login_id | login_pass | user_name | user_address | order_history_id | order_day  | menu_name   | price | order_person_id |
-- +--------------+----------+------------+-----------+--------------+------------------+------------+-------------+-------+-----------------+
-- |            1 |     1234 | password   | Bob       | Tokyo        |                1 | 2018/01/02 | ketchup     |   200 |               1 |
-- |            1 |     1234 | password   | Bob       | Tokyo        |                2 | 2018/01/04 | egg         |   200 |               1 |
-- |            2 |     5678 | password   | John      | Osaka        |                3 | 2019/02/04 | Okonomiyaki |   400 |               2 |
-- +--------------+----------+------------+-----------+--------------+------------------+------------+-------------+-------+-----------------+
-- 3 rows in set (0.00 sec)

```

- delete test data(database,table,record)

```sql
delete from order_history where order_history_id in(1,2);

delete from user_info where user_info_id in(1,2);

-- if you want to reset table or db
DROP TABLE order_history;
DROP TABLE user_info;

DROP DATABASE KitchenDB;
```

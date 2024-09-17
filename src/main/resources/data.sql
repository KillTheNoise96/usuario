insert into users (name, email, password, active, created)
values('Marcus Phoenix','mark_pho@gmail.com','$2a$12$ADnOM0Q87BD2pgS1GY82P.gYig1rL1T.jhHL6kw1j6OGf76u5NlHG'/*'Gowrules123'*/, true, CURRENT_TIMESTAMP()),
      ('Dominic Santiago','dom_sant@hotmail.com','$2a$12$c7GaJ/6Z9HyaJ.4ZDPy1a.XQNjweVSGQG2hw5rXyTmU4rF4NPqxlq'/*'Gears0fw4r'*/, true,CURRENT_TIMESTAMP());

insert into phones (city_code, number, country_code, user_id)
values('1',123456789,'50',1),
      ('4',98746565,'51',1),
      ('0', 98468654,'505',2);

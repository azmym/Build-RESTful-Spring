/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

-- Create 4 manufacturers

insert into manufacturer (id, date_created, manufacturer) values (1, now(), 'Mercedes-Benz');
insert into manufacturer (id, date_created, manufacturer) values (2, now(), 'BMW');
insert into manufacturer (id, date_created, manufacturer) values (3, now(), 'Audi');
insert into manufacturer (id, date_created, manufacturer) values (4, now(), 'Volkswagen');

-- Create 3 open-air mode  cars

insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (1, now(), 'D Y-127-508', 4, true, 4, 'petrol', 4);
insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id)
values
 (2, now(), 'D WW GG 544', 4, true, 3.2, 'petrol', 3);
insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id)
values
 (3, now(), 'D K PC 1313', 4, true, 4.1, 'petrol', 1);
 
-- Create 3 enclosed cars

insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (4, now(),'D M RY 417', 5, false, 5, 'petrol', 1);
insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (5, now(),'D WOB ZK 295', 4, false, 3, 'petrol', 4);
insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (6, now(),'D EL 03944', 4, false, 4, 'petrol', 3);
 
 -- Create one open-air mode  car with electric engine
 
 insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (7, now(),'D KW 7165', 4, true, 4.5, 'electric', 1);
 
 --Create one enclosed car with gas engine
 
  insert into car (id, date_created, license_plate, seat_count, convertible, rating, engine_type, manufacturer_Id) 
values
 (8, now(),'D F OW 653', 4, false, 3.9, 'gas', 3);
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  natan
 * Created: Aug 22, 2018
 */

create table IF NOT EXISTS server (
    id serial,
    hello varchar(10) not null,
    primary key(id)
)
create database argosdb;

create table if not exists argosdb.users (
    id varchar(36) not null,
    username varchar(20) not null,
    password varchar(100) not null,
    name varchar(50) not null,
    email varchar(50) not null unique,
    cpf varchar(11) not null unique,
    birthdate date,
    role int not null,
    created_at datetime not null,
    updated_at datetime,
    primary key (id)
);

create table if not exists argosdb.phones (
    id varchar(36) not null,
    region_code varchar(2) not null,
    value varchar(15) not null,
    user_id varchar(36) not null,
    foreign key (user_id) references users(id),
    primary key (id)
);

create table if not exists argosdb.addresses (
    id varchar(36) not null,
    country_code varchar(2) not null,
    state_code varchar(2) not null,
    street_name varchar(50) not null,
    number int not null,
    zipcode varchar(15) not null,
    complement varchar(50),
    user_id varchar(36) not null,
    foreign key (user_id) references users(id),
    primary key (id)
);

create table if not exists argosdb.product_classifications (
    id varchar(36) not null,
    value varchar(50),
    primary key (id)
);

create table if not exists argosdb.products (
    id varchar(36) not null,
    name varchar(50) not null,
    price decimal(7,2) not null check (price > 0.00),
    created_at datetime not null,
    updated_at datetime,
    seller_id varchar(36) not null,
    product_classification_id varchar(36) not null,
    primary key (id),
    foreign key (seller_id) references users(id),
    foreign key (product_classification_id) references product_classifications(id)
);

create table if not exists argosdb.inventory_items (
    id varchar(36) not null,
    status int not null,
    amount int not null check (amount >= 0),
    updated_at datetime,
    product_id varchar(36) not null,
    foreign key (product_id) references products(id)
);

create table if not exists argosdb.sales (
    id varchar(36) not null,
    status int not null,
    created_at datetime not null,
    updated_at datetime not null,
    total_value decimal(7,2) not null check (total_value > 0.00),
    buyer_id varchar(36) not null,
    primary key (id),
    foreign key (buyer_id) references users(id)
);

create table if not exists argosdb.sale_items (
    id varchar(36) not null,
    product_id varchar(36) not null,
    sale_id varchar(36) not null,
    amount int not null check (amount > 0),
    foreign key (product_id) references products(id),
    foreign key (sale_id) references sales(id)
);
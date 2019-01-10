
--
-- Structure for table initializer_starter_component
--

DROP TABLE IF EXISTS initializer_starter_component;
CREATE TABLE initializer_starter_component (
id_starter_component int AUTO_INCREMENT,
artifact_id varchar(255) default '',
code_category long varchar,
label_key varchar(255) default '',
label_default long varchar,
description_key varchar(255) default '',
description_default long varchar,
PRIMARY KEY (id_starter_component)
);

--
-- Structure for table initializer_category_component
--

DROP TABLE IF EXISTS initializer_category_component;
CREATE TABLE initializer_category_component (
id_category_component int AUTO_INCREMENT,
code varchar(255) default '',
label_key varchar(255) default '',
label_default varchar(255) default '',
PRIMARY KEY (id_category_component)
);

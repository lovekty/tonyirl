<#noparse>CREATE TABLE `</#noparse>${tableName}<#noparse>` 
(`id` int(11) NOT NULL AUTO_INCREMENT,</#noparse>
<#list childClassFields as field>
`${field.columnName}` ${field.columnType} ${field.columnModifier},
</#list>
`insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`status` tinyint(4) DEFAULT '2',
`visible` tinyint(2) DEFAULT '1',
PRIMARY KEY (`id`))
ENGINE=InnoDB DEFAULT CHARSET=utf8

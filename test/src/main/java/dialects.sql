--clickhouse建表
CREATE TABLE IF NOT EXISTS ai_datalake_dev.bomp_nlg_dialect ON CLUSTER aick
(
    `id` UInt16 COMMENT '主键',
    `asr_voice` String DEFAULT '' COMMENT 'asr音色',
    `tts_voice` String DEFAULT '' COMMENT 'tts音色',
    `type_desc` String DEFAULT '' COMMENT '类型',
    `name` String DEFAULT '' COMMENT '名称',
    `create_time` DateTime DEFAULT now() COMMENT '创建时间',
    `update_time` DateTime DEFAULT now() COMMENT '更新时间'
)
ENGINE = MergeTree()
PARTITION BY id
PRIMARY KEY (id)
ORDER BY (id,asr_voice, tts_voice, type_desc, name);

--mysql建表
CREATE TABLE IF NOT EXISTS bomp_nlg_dialect (
	`id` SMALLINT(16) UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `asr_voice` VARCHAR(100) DEFAULT '' COMMENT 'asr音色',
    `tts_voice` VARCHAR(100) DEFAULT '' COMMENT 'tts音色',
    `type_desc` VARCHAR(100) DEFAULT '' COMMENT '类型',
    `name` VARCHAR(100) DEFAULT '' COMMENT '名称',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    UNIQUE(tts_voice)
)Engine=InnoDB DEFAULT CHARSET=utf8
COMMENT '音色信息表';
--clickhouse建表
CREATE TABLE IF NOT EXISTS ai_datalake.bomp_nlg_dialect ON CLUSTER aick
(
    `id` UInt16 COMMENT '主键',
    `asrVoice` String DEFAULT '' COMMENT 'asr音色',
    `ttsVoice` String DEFAULT '' COMMENT 'tts音色',
    `typeDesc` String DEFAULT '' COMMENT '类型',
    `name` String DEFAULT '' COMMENT '名称'
)
ENGINE = ReplicatedMergeTree('/clickhouse/tables/{shard}/ys/ai_statis_talking_month_local', '{replica}')
PRIMARY KEY (asrVoice, ttsVoice, typeDesc, name)
ORDER BY (asrVoice, ttsVoice, typeDesc, name);

--mysql建表
CREATE TABLE IF NOT EXISTS ai_datalake.bomp_nlg_dialect (
	`id` SMALLINT(16) UNSIGNED COMMENT '主键',
    `asrVoice` VARCHAR(20) DEFAULT '' COMMENT 'asr音色',
    `ttsVoice` VARCHAR(20) DEFAULT '' COMMENT 'tts音色',
    `typeDesc` VARCHAR(20) DEFAULT '' COMMENT '类型',
    `name` VARCHAR(20) DEFAULT '' COMMENT '名称',
PRIMARY KEY (asrVoice, ttsVoice, typeDesc, name),
)Engine=InnoDB DEFAULT CHARSET=utf8;
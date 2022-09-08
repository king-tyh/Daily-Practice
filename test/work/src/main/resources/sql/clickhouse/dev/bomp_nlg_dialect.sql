CREATE TABLE IF NOT EXISTS ai_datalake_dev.bomp_nlg_dialect_local ON CLUSTER aick
(
    `id` UInt16 COMMENT '主键',
    `asr_voice` String DEFAULT '' COMMENT 'asr音色',
    `tts_voice` String DEFAULT '' COMMENT 'tts音色',
    `type_desc` String DEFAULT '' COMMENT '类型',
    `name` String DEFAULT '' COMMENT '名称',
    `create_time` DateTime DEFAULT now() COMMENT '创建时间',
    `update_time` DateTime DEFAULT now() COMMENT '更新时间'
)
ENGINE = ReplicatedMergeTree('/clickhouse/tables/{shard}/dev/ai_net_device_local','{replica}')
PARTITION BY toYYYYMMDD(create_time)
PRIMARY KEY (id)
ORDER BY (id,asr_voice, tts_voice, type_desc, name);

CREATE TABLE IF NOT EXISTS bomp_nlg_dialect_all ON CLUSTER aick
(
    `id` UInt16 COMMENT '主键',
    `asr_voice` String DEFAULT '' COMMENT 'asr音色',
    `tts_voice` String DEFAULT '' COMMENT 'tts音色',
    `type_desc` String DEFAULT '' COMMENT '类型',
    `name` String DEFAULT '' COMMENT '名称',
    `create_time` DateTime DEFAULT now() COMMENT '创建时间',
    `update_time` DateTime DEFAULT now() COMMENT '更新时间'
)
ENGINE = Distributed('aick','ai_datalake','ai_net_device',rand());

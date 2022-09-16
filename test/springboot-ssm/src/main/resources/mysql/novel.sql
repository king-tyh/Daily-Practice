create table book
(
    `id`         int(11) primary key AUTO_INCREMENT comment '小说表主键',
    `title`      varchar(128) not null comment '小说名',
    `desc`       varchar(256) comment '简介',
    `author`     varchar(16)  not null comment '作者',
    `novel_type` varchar(16) comment '小说类型',
    `tags`       varchar(256) comment '小说标签'
) Engine = InnoDB
  DEFAULT CHARSET = utf8
    COMMENT '小说表';

create table chapter
(
    `id`          int(11) primary key AUTO_INCREMENT comment '主键',
    `title`       varchar(128) not null comment '章节标题',
    `book_id`     int(11) not null comment '对应小说id',
    `content_id`  int(11) not null comment '对应文章内容id',
    `is_delete`   tinyint(3) comment '删除表示',
    `words`       int(11) comment '字数',
    `create_time` datetime default now() comment '创建时间',
    `update_time` datetime default now() comment '更新时间'
) Engine = InnoDB
  DEFAULT CHARSET = utf8
    COMMENT '章节表';
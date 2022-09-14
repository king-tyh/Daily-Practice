create table book(
                      `id` int(11) primary key AUTO_INCREMENT comment '小说表主键',
                      `title` varchar(128) not null comment '小说名',
                      `desc` varchar(256) comment '简介',
                      `author` varchar(16) not null comment '作者',
                      `novel_type` varchar(16) comment '小说类型',
                      `tags` varchar(256) comment '小说标签'
)Engine=InnoDB DEFAULT CHARSET=utf8
COMMENT '小说表';
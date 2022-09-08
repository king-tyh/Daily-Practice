package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * @author tianyuhuan
 * @Description 音色信息表
 * @Date 2022/8/23 09:55
 */

@Data
public class BompNlgDialect extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "id", example = "1")
    private Long id;

    /**
     * asr音色
     */
    @ApiModelProperty(name = "asrVoice", value = "asr音色", example = "scene_default")
    private String asrVoice;

    /**
     * tts音色
     */
    @ApiModelProperty(name = "ttsVoice", value = "tts音色", example = "female25")
    private String ttsVoice;

    /**
     * 类型
     */
    @ApiModelProperty(name = "typeDesc", value = "类型", example = "普通话")
    private String typeDesc;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称", example = "普通话(乐乐)")
    private String name;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updateTime;
}


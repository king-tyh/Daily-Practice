package com.test.spring.work;

import lombok.Data;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
public class AiAppIdentify{

    /**
     * app唯一标识
     */
    @TableId("appId")
    private String appId;

    /**
     * app名称
     */
    @TableField
    private String appName;

    /**
     * appKey
     */
    @TableField
    private String appKey;

    /**
     * appSecret
     */
    @TableField
    private String appSecret;

    /**
     * deviceKey
     */
    @TableField
    private String deviceKey;

    /**
     * 是否调用tts,0:无,1:调用
     */
    @TableField
    private Byte hasTts;

    /**
     * 权限校验.0-全不,1-token和client有值,2-token和clientId匹配,3-全部,4-互联音箱deviceId和签名,5-只签名
     */
    @TableField
    private Byte oathCheck;

    /**
     * 统计asr时是否计算该appId,0:否,1-是
     */
    @TableField
    private Byte asrNeedStatis;

    /**
     * 统计nlp时是否计算appId,0:否,1-是
     */
    @TableField
    private Byte nlpNeedStatis;

    /**
     * 是否DOT音箱,0:否,1-是
     */
    @TableField
    private Byte isDot;

    /**
     * 状态,0:无效,1-虚拟(如"appId":"ALL-REPORT","appName":"全局",只是业务上需要，没有对应的真实渠道),2-真实
     */
    @TableField
    private Byte status;

    /**
     * 是否记录对话内容,0-否,1-是
     */
    @TableField
    private Byte needDialog;

    /**
     * 排序号
     */
    @TableField
    private Integer sort;

    /**
     * app所属类型,0-虚拟,1-音箱,2-app,3-设备,4-其它,5-语音模块,6-平台
     */
    @TableField
    private Byte appType;

    /**
     * 所属类型名称
     */
    @TableField(exist = false)
    private String appTypeName;

    /**
     * 别名
     */
    @TableField
    private String aliasName;

    /**
     * 渠道所使用的nlp模型
     */
    @TableField
    private String nlpMode;

    /**
     * 设备模型typeId
     */
    @TableField
    private String typeId;

    /**
     * 创建时间
     */
    @TableField
    private Date createTime;

    /**
     * 所属领域
     */
    @TableField
    private String domain;

    /**
     * 限制标志
     */
    @TableField
    private Integer limitFlag;

    /**
     * 产业
     */
    @TableField(value = "industry_code")
    private String industryCode;

    /**
     * 产业名称
     */
    @TableField(value = "industry_name")
    private String industryName;

    /**
     * 品类
     */
    @TableField(value = "product_category_code")
    private String productCategoryCode;

    /**
     * 品类名称
     */
    @TableField(value = "product_category_name")
    private String productCategoryName;

    /**
     * 品类修改时间
     */
    @TableField(value = "product_category_code_update_time")
    private Date productCategoryCodeUpdateTime;

    /**
     * 品类修改人
     */
    @TableField(value = "product_category_code_update_user")
    private String productCategoryCodeUpdateUser;

    /**
     * 产品修改时间
     */
    @TableField(value = "industry_code_update_time")
    private Date industryCodeUpdateTime;

    /**
     * 产品修改人
     */
    @TableField(value = "industry_code_update_user")
    private String industryCodeUpdateUser;

    public AiAppIdentify() {

    }

    public AiAppIdentify(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    public AiAppIdentify(String appId, String appKey, String appSecret) {
        this(appId, appKey);
        this.appSecret = appSecret;
    }

}


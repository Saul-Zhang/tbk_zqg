package com.orange.tbk.api.vo.open;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Data
public class CarouselVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 点击跳转地址
     * click_url
     */
    private String clickUrl;

    /**
     * 轮播主图
     * image
     */
    private String image;

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

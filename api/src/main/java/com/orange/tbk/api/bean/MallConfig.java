package com.orange.tbk.api.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_mall_config")
public class MallConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * title
     */
    @Column(name = "title")
    private String title;

    /**
     * image_url
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 点击后跳转的路由
     * url
     */
    @Column(name = "url")
    private String url;

    /**
     * create_at
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 1:首页导航2.首页推广3.精选商品
     * status
     */
    @Column(name = "state")
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
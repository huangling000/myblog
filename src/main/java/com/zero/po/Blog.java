package com.zero.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity//具备和数据库生成相应的能力
@Table(name = "t_blog")
public class Blog {

    @Id
    @GeneratedValue
    private long id;  //主键

    @NotBlank(message = "标题不能为空")//为空会抛出异常
    private String title;  //标题

    @NotBlank(message = "内容不能为空")//为空会抛出异常
    @Basic(fetch = FetchType.LAZY)
    @Lob    //@Lob注解声明大字段类型 第一次初始化时才有效，一般和@Basic懒加载一起使用，只有需要获取的时候才去查询；也可以直接去数据库内将该字段改为longtext类型
    private String content; //内容

    @NotBlank(message = "首图不能为空")//为空会抛出异常
    private String firstPicture; //首图
    private String flag;  //标记：原创或转载
    private Integer views; //浏览次数
    private boolean appreciation;  //赞赏是否开启
    private boolean shareStatement;  //转载声明是否开启
    private boolean Commentabled;  //评论是否开启
    private boolean published;  //是否发布
    private boolean recommend;  //是否推荐

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;  //创建日期

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;   //更新日期

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})//cascade设置级联关系,当新增一个博客的同时如果需要新增一个标签，则也将标签新增进数据库
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")//一对多，一的一方为被维护方，mappedBy指定被维护方通过某属性连接
    private List<Comment> comments = new ArrayList<>();


    @Transient//正常属性值，不和数据库一一映射
    private String tagIds;

    private String description;

    public String getDescription() {
        return description;
    }
    //初始化当前博客Ids
    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }



    public Blog() {
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return Commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        Commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", Commentabled=" + Commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                ", tagIds='" + tagIds + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

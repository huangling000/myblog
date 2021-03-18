package com.zero.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id; //主键
    @NotBlank(message = "分类名称不能为空")//为空会抛出异常
    private String name; //名字

    @OneToMany(mappedBy = "type")  //有多个blog，作为one的一端，为关系被维护端
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

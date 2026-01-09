package models;

public class BlogCategory {
    private int blogCategoryId;
    private String title;

    public BlogCategory() {}

    public int getBlogCategoryId() { return blogCategoryId; }
    public void setBlogCategoryId(int blogCategoryId) { this.blogCategoryId = blogCategoryId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}

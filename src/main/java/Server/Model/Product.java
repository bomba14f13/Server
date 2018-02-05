package Server.Model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Product() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
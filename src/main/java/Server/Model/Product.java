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
    @JoinColumn(name="folder_id")
    private Folder folder;

    public Product() {

    }

    public Product(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
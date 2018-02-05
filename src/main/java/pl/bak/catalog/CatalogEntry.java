package pl.bak.catalog;


public class CatalogEntry {

    private final Integer id;
    private final String name;
    private final String description;


    public CatalogEntry(String name, String description) {
        this(null, name, description);
    }

    CatalogEntry(Integer id, String name, String description) {
        this.id = id;
        this.name = validateName(name);
        this.description = validateDesc(description);
    }

    private String validateName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new InvalidCatalogEntryException("Invalid name");
        }
        return name;
    }

    private String validateDesc(String description) {
        if (description == null || description.trim().length() == 0) {
            throw new InvalidCatalogEntryException("Invalid description");
        }
        return description;
    }

    public String getName() {
        return name;
    }
    public int getId(){
        return id;
    }

    public String getDescription() {
        return description;
    }
}

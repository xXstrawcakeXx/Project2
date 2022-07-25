# **Project Outline**


# **``` MODEL (com.reavature.model)```**
### ```Itinerary ```

```java 
public class Itinerary {

private int id;
private String destination
private double price;
private int slots;
private double latitude;
private double longitude;
private String description;

// @ManyToMany (Join Itinerary id)
private List<Tag> tags;
}
```
    


### ```User```

```java 
public class User {

private int id;
private String lastName;
private String username;
private String password;
private String email;
private OrderHistory orderHistory;

// @ManyToMany
// temporary until the user checksout
private List<Itinerary> initeraryCart;
}
```
    


### ```OrderHistory```

```java
public class OrderHistory {
//Enter class fields here
}
```

<br>

# **```REPOSITORY (com.revature.data)```**

### ``` ItineraryRepository```

```java
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer>{

public Optional<Itinerary> findByid(int id);

@Query("FROM Itineraries WHERE slots > 0")
public List<Itinerary> getAvailableItineraries();
	
@Query(value = "SELECT * FROM itineraries WHERE itinerary_id IN (SELECT itin_id FROM itin_tags WHERE tag_id = ?1)", nativeQuery = true)
public List<Itinerary> findByTag(int tag_id);
}
```
<br>


### ```UserRepository ```

```java
public interface UserRepository extends JpaRepository<Itinerary, Integer>{

public Optional<Itinerary> findByid(int id);

@Query("FROM Itineraries WHERE slots > 0")
public List<Itinerary> getAvailableItineraries();
	
@Query(value = "SELECT * FROM itineraries WHERE itinerary_id IN (SELECT itin_id FROM itin_tags WHERE tag_id = ?1)", nativeQuery = true)
public List<Itinerary> findByTag(int tag_id);
}
```

# **```SERVICE (com.revature.service)```**

### ```ItineraryService ```

```java
public class ItineraryService{
    //fields
    private Logger log;
    ItineraryRepository itinRepo;

    //declared, concrete methods
    public List<Itinerary> findAll();
    public List<Itinerary> findAvailable();
    public List<Itinerary> findByTag(Tag tag);
    public Itinerary getById(Integer id);
    public Itinerary add(Itinerary itin);
    public Itinerary update(Itinerary itin);
    public boolean delete(int id);

}
```
### ```OrderHistoryService ```

```java
    //enter fields and methods here
```

### ```UserService```

```java
public class UserService{
    private Logger log;
    UserRepository userRepo;
    ItineraryRepository itinRepo;

    //declared, concrete methods
    public List<User> findAll();
    public User getByUsername(String username);
    public User getById(int id);
    public User add(User u);
    public User update(User u);
    public boolean delete(int id);
    public HashSet<Itinerary> addToCart(int user_id, int itin_id);
    



}

```




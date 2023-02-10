//package hellojpa;
//
//import hellojpa.embadedType.Address;
//import hellojpa.embadedType.Period;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//public class Member {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "member_id")
//    private Long id;
//
//    @Column
//    private String userName;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_id")
//    private Team team;
//
//    @Embedded
//    private Period period;
//    @Embedded
//    private Address address;
//
//    @ElementCollection
//    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
//    @JoinColumn(name = "member_id")
//    )
//    @Column(name = "food_name")
//    private Set<String> favoriteFoods = new HashSet<>();
//
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS" , joinColumns =
//    @JoinColumn(name = "member_id")
//    )
//    private List<Address> addressList = new ArrayList<>();
//
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }
//
//    public Set<String> getFavoriteFoods() {
//        return favoriteFoods;
//    }
//
//    public void setFavoriteFoods(Set<String> favoriteFoods) {
//        this.favoriteFoods = favoriteFoods;
//    }
//
//    public List<Address> getAddressList() {
//        return addressList;
//    }
//
//    public void setAddressList(List<Address> addressList) {
//        this.addressList = addressList;
//    }
//
//    public Period getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(Period period) {
//        this.period = period;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public Team getTeam() {
//        return team;
//    }
//
//    public void changTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }
//}
package enumeracije;

public enum TypeOfRoom {
	jednokrevetna("1"),
    dvokrevetna("1+1", "2"),
    trokrevetna("1+1+1", "1+2", "3"),
    cetvorokrevetna("1+1+1+1", "1+2+1", "2+2", "4"),
    apartman("1+1+1+1", "1+2+1", "2+2", "1+1+1", "1+2", "3", "4");

    private String[] bedLayouts;

    TypeOfRoom(String... bedLayouts) {
        this.bedLayouts = bedLayouts;
    }

    public String[] getBedLayouts() {
        return bedLayouts;
    }
}

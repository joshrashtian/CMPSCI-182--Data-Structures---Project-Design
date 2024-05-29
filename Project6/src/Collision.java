public class Collision {
    DataItem item;
    String at;
    String shouldBe;

    Collision(DataItem item, String at, String shouldBe) {
        this.at = at;
        this.item = item;
        this.shouldBe = shouldBe;
    }

    public String getAt() {
        return at;
    }

    public String getShouldBe() {
        return shouldBe;
    }

    public DataItem getItem() {
        return item;
    }
}
